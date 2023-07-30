package algafood.api.controllers;

import algafood.api.dtos.input.RestauranteInputDTO;
import algafood.api.dtos.output.RestauranteOutputDTO;
import algafood.core.ValidacaoException;
import algafood.domain.exception.EntidadeNaoEncontradaException;
import algafood.domain.exception.NegocioException;
import algafood.domain.models.Restaurante;
import algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestauranteOutputDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());
    }

    @GetMapping("{restauranteId}")
    public ResponseEntity<RestauranteOutputDTO> buscar(@PathVariable(value = "restauranteId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteOutputDTO> salvar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.adicionar(restauranteInputDTO));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("{restauranteId}")
    public ResponseEntity<RestauranteOutputDTO> atualizar(@PathVariable(value = "restauranteId") Long id, @RequestBody RestauranteInputDTO restauranteInputDTO) {

        var restaurante = restauranteService.buscarRestaurante(id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(restauranteService.atualizar(restauranteInputDTO, restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("{restauranteId}")
    public ResponseEntity<Void> remover(@PathVariable(value = "restauranteId") Long id) {

        restauranteService.remover(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("{restauranteId}")
    public ResponseEntity<RestauranteOutputDTO> atualizarParcial(@PathVariable(value = "restauranteId") Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {

        var restauranteAtual = restauranteService.buscarRestaurante(id);

        if (restauranteAtual == null) {
            ResponseEntity.notFound().build();
        }

        mergeRestaurante(campos, restauranteAtual, request);
        validateRestaurante(restauranteAtual, "restaurante");
        
        assert restauranteAtual != null;
        var restauranteDto = new RestauranteInputDTO(restauranteAtual);
        return atualizar(id, restauranteDto);
    }

    private void validateRestaurante(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if(bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private static void mergeRestaurante(Map<String, Object> campos, Restaurante restauranteAtual, HttpServletRequest request) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            // Transforma os campos em propriedades serializadas do restaurante
            Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);

            campos.forEach((nomePropriedade, valorPropriedade) -> {

                // Busca cada campo alterado referente a cada propriedade de restaurante
                Field campo = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

                // acessa as propriedades privadas
                assert campo != null;
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
        } catch (IllegalArgumentException exception) {
            var rootCause = ExceptionUtils.getRootCause(exception);
            throw new HttpMessageNotReadableException(exception.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
