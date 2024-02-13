package algafood.api.controllers;

import algafood.api.dtos.output.FormaPagamentoDTO;
import algafood.common.mapper.Mapper;
import algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    Mapper mapper;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listar(@PathVariable Long restauranteId) {

        var restaurante = restauranteService.buscarRestaurante(restauranteId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.mapCollection(restaurante.getFormaPagamentos(), FormaPagamentoDTO.class));
    }

    @PutMapping("{formaPagamentoId}")
    public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{formaPagamentoId}")
    public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

}
