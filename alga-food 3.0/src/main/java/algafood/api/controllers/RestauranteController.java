package algafood.api.controllers;

import algafood.api.dtos.RestauranteDTO;
import algafood.domain.models.Restaurante;
import algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/restaurantes") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurante>> listar1() {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());
    }

    @GetMapping("{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable(value = "restauranteId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody RestauranteDTO restauranteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.adicionar(restauranteDTO));
    }

    @PutMapping("{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable(value = "restauranteId") Long id, @RequestBody RestauranteDTO restauranteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.atualizar(id, restauranteDTO));
    }

    @DeleteMapping("{restauranteId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "restauranteId") Long id) {
        restauranteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
