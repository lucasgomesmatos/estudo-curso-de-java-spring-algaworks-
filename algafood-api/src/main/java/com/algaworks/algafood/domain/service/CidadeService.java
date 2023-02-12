package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade buscar(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.get();
    }

    @Transactional
    public void remover(Long id) {
        cidadeRepository.deleteById(id);

    }


}
