package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());

    }

    @GetMapping("{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        Cidade cidade = cidadeService.buscar(id);

        if (cidade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidade);
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.adicionar(cidade));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService.buscar(id);

        if (cidadeAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.adicionar(cidadeAtual));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            cidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
