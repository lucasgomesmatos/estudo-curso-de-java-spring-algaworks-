package com.algafood.alfaworksfoodapi.service;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

//    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//    @Autowired
//    private Notificador notificador;

//    @Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }

//    public AtivacaoClienteService(String qualquer) {
//    }


//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }

////    @PostConstruct
//    public void init() {
//        System.out.println("INIT " + notificador);
//    }
////    @PreDestroy
//    public void destroy() {
//        System.out.println("DESTROY " + notificador);
//    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
//        notificador.notificar(cliente, "Seu cadastro no sistema está ativo");
        eventPublisher.publishEvent(new ClientAtivadoEvent(cliente));

    }
}
