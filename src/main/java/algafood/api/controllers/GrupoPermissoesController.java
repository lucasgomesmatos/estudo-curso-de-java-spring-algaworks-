package algafood.api.controllers;

import algafood.api.dtos.output.PermissaoDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<PermissaoDTO>> listar(@PathVariable Long grupoId) {

        var grupo = grupoService.buscarGrupo(grupoId);

        var permissoes = mapper.mapCollection(grupo.getPermissoes(), PermissaoDTO.class);

        return ResponseEntity.ok(permissoes);
    }

    @PutMapping("{permissaoId}")
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{permissaoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
