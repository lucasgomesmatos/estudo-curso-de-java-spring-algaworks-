package algafood.api.controllers;

import algafood.api.dtos.RestauranteDTO;
import algafood.domain.models.Restaurante;
import algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/restaurantes")
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

    @PatchMapping("{restauranteId}")
    public ResponseEntity<Restaurante> atualizarParcial(@PathVariable(value = "restauranteId") Long id, @RequestBody Map<String, Object> campos) {

        var restauranteAtual = restauranteService.buscar(id);

        if(restauranteAtual == null) {
            ResponseEntity.notFound().build();
        }

        mergeRestaurante(campos, restauranteAtual);
        var restauranteDto = new RestauranteDTO(restauranteAtual);
        return atualizar(id, restauranteDto);
    }

    private static void mergeRestaurante(Map<String, Object> campos, Restaurante restauranteAtual) {

        ObjectMapper objectMapper = new ObjectMapper();
        // Transforma os campos em propriedades serializadas do restaurante
        Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {

            // Busca cada campo alterado referente a cada propriedade de restaurante
            Field campo = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

            // acessa as propriedades privadas
            campo.setAccessible(true);

            // Busca as propridades da serialização de restauranteOrigem
            Object novoValor = ReflectionUtils.getField(campo, restauranteOrigem);


            /**
             * Seta os valores alterados nas propridades do restauranteAtual
             *  (nome == nome) = novoValor
             */
            ReflectionUtils.setField(campo, restauranteAtual, novoValor);


            System.out.println(nomePropriedade + " = " + novoValor);
            System.out.println(novoValor.getClass());
        });
    }

}
