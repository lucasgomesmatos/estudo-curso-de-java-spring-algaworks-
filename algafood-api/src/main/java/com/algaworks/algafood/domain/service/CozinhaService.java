package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    @Transactional
    public Cozinha buscar(Long id) {
        return cozinhaRepository.buscar(id);
    }

    @Transactional
    public List<Cozinha> listar( ) {
        return cozinhaRepository.listar();
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeEmUsoException(String.format("Não existe um cadastro de cozinha com o código %d ", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }

    }
}
