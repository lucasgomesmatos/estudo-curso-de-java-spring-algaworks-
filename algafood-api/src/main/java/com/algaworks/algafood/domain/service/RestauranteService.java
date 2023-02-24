package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

       cozinhaRepository.findById(cozinhaId);

        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listar() {

        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {

        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.get();

    }

    public List<Restaurante> buscarPorTaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        return restauranteRepository.consultar(nome, taxaInicial, taxaFinal);

    }
}
