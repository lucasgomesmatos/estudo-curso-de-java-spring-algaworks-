package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    public Cidade buscar(Long id) {
        return cidadeRepository.buscar(id);
    }

    @Transactional
    public void remover(Long id) {
        cidadeRepository.remover(id);

    }


}
