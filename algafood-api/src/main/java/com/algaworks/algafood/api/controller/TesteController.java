package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/testes")
public class TesteController {

    @Autowired
    private RestauranteService restauranteService;



    @GetMapping("por-nome-e-frete")
    public ResponseEntity<List<Restaurante>> buscarPorTaxa(
             String nome,  BigDecimal taxaInicial,
             BigDecimal taxaFinal) {

        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.buscarPorTaxa(nome, taxaInicial, taxaFinal));
    }

    @GetMapping("com-frete-gratis")
    public ResponseEntity<List<Restaurante>> buscarPorTaxa(String nome) {

        var comFretegratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec();

        return ResponseEntity.status(HttpStatus.OK).body();
    }

    // 05-16



}
