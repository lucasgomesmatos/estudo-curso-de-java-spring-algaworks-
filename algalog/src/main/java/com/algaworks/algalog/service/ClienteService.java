package com.algaworks.algalog.service;

import com.algaworks.algalog.common.ExceptionError;
import com.algaworks.algalog.common.NegocioExpection;
import com.algaworks.algalog.model.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(!cliente.isPresent()) {
            throw new NegocioExpection("Cliente não existe");
        }

        return cliente.get();
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {

        boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailExistente) {
            throw new NegocioExpection("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long clienteId, Cliente cliente) {

        if(!clienteRepository.existsById(clienteId)) {
            throw new NegocioExpection("Cliente não cadastrado");
        }

        boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailExistente) {
            throw new NegocioExpection("Já existe outro cliente cadastrado com este e-mail.");
        }

        cliente.setId(clienteId);

        return clienteRepository.save(cliente);
    }
    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }


}
