package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cozinhas")
public class CozinhaController {


    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {

        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscar(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.salvar(cozinha));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Cozinha> atualizar(@Valid @PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(id);

        if (cozinhaAtual == null) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        try {
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.salvar(cozinhaAtual));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@Valid @PathVariable Long id) {

        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
