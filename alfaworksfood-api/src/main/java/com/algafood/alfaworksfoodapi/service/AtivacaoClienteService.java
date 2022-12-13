package com.algafood.alfaworksfoodapi.service;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import com.algafood.alfaworksfoodapi.notificacao.NivelUrgencia;
import com.algafood.alfaworksfoodapi.notificacao.Notificador;
import com.algafood.alfaworksfoodapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;


//@Component
public class AtivacaoClienteService {


    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
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

//    @PostConstruct
    public void init() {
        System.out.println("INIT " + notificador);
    }
//    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY " + notificador);
    }

    public void ativar(Cliente cliente) {
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo");

    }
}
