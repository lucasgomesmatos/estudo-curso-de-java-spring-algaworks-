package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @Transactional
    public Estado adicionar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public Estado buscar(Long id) {
        return estadoRepository.buscar(id);
    }

    @Transactional
    public void remover(Long id) {
        estadoRepository.remover(id);

    }


}
