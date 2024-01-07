package algafood.api.controllers;

import algafood.api.dtos.output.UsuarioDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurantes{restauranteId}/responsaveis")
public class RestauranteResponsavelController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(@PathVariable Long restauranteId) {
        var restaurantes = restauranteService.buscarRestaurante(restauranteId);
        var usuarios = mapper.mapCollection(restaurantes.getUsuarios(), UsuarioDTO.class);

        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<Void> associarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<Void> desassociarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}
