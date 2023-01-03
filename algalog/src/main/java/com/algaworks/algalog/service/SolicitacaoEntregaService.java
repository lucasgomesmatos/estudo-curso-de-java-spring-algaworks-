package com.algaworks.algalog.service;

import com.algaworks.algalog.common.AssemblerMapper;
import com.algaworks.algalog.common.NegocioExpection;
import com.algaworks.algalog.dto.DestinarioDto;
import com.algaworks.algalog.dto.EntregaDto;
import com.algaworks.algalog.model.Cliente;
import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.StatusEntrega;
import com.algaworks.algalog.repository.ClienteRepository;
import com.algaworks.algalog.repository.EntregaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AssemblerMapper assemblerMapper;

    @Transactional
    public Entrega solicitar(Entrega entrega){

        Cliente cliente = clienteService.buscarClienteExistente(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public EntregaDto busca(Long entregaId) {
        return entregaRepository
                .findById(entregaId)
                .map(entrega -> assemblerMapper.toModel(entrega))
                .orElseThrow(() -> new NegocioExpection("Entrega não existe."));
    }
}
