package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurante> buscar(@Valid @PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscar(id);

        if (restaurante == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurante);
    }

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurante> atualizar(@Valid @PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteService.buscar(id);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(restauranteService.salvar(restauranteAtual));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PatchMapping("{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteService.buscar(id);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(id, restauranteAtual);
    }

    private static void merge(Map<String, Object> campos, Restaurante restaurante) {
        ObjectMapper objectMapper = new ObjectMapper();

        Restaurante novoRestaurante = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            assert field != null;
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, novoRestaurante);

            ReflectionUtils.setField(field, restaurante, novoValor);
        });
    }

}
