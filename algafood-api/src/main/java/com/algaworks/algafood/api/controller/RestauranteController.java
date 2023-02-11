package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
}
