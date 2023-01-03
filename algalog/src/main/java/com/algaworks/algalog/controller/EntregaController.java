package com.algaworks.algalog.controller;

import com.algaworks.algalog.dto.EntregaDto;
import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/entregas")
public class EntregaController {

    @Autowired
    private SolicitacaoEntregaService entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return entregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listar();
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaDto> buscar(@PathVariable Long entregaId) {


        return ResponseEntity.ok(entregaService.busca(entregaId));
    }
}
