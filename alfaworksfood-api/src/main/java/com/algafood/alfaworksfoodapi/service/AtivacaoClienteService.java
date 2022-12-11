package com.algafood.alfaworksfoodapi.service;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import com.algafood.alfaworksfoodapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Qualifier("sms")
    @Autowired
    private Notificador notificador;

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
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo");

    }
}
