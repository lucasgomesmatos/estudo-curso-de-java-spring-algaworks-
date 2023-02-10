package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

//    public Cozinha buscar( Long id) {
//        Cozinha cozinha = cozinhaRepository.buscar(id);
//
//        if (cozinha == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return cozinha;
//    }
}
