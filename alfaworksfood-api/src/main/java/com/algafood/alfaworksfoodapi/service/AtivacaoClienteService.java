package com.algafood.alfaworksfoodapi.service;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import com.algafood.alfaworksfoodapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired
    private List<Notificador> notificadores;

//    @Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }

//    public AtivacaoClienteService(String qualquer) {
//    }


//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }

    public void ativar(Cliente cliente) {

        for (Notificador notificador: notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
        }

    }
}
