package algafood.api.controllers;

import algafood.api.dtos.output.GrupoDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> listar(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarUsuario(usuarioId);

        return ResponseEntity.ok(mapper.mapCollection(usuario.getGrupos(), GrupoDTO.class));

    }

    @PutMapping("{grupoId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarPermissao(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{grupoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarPermissao(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }
}
