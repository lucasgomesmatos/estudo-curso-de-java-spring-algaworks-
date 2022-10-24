package com.algawords.di.service;

import com.algawords.di.notificacao.Notificador;
import com.algawords.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired(required = false)
    private List<Notificador> notificadores;


    public void ativar(Cliente cliente) {
        cliente.ativar();

        for(Notificador notificador: notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        }


    }


}
