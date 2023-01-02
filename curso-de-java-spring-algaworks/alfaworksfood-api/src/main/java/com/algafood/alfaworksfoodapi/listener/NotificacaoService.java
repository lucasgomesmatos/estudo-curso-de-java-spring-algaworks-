package com.algafood.alfaworksfoodapi.listener;

import com.algafood.alfaworksfoodapi.notificacao.NivelUrgencia;
import com.algafood.alfaworksfoodapi.notificacao.Notificador;
import com.algafood.alfaworksfoodapi.notificacao.TipoDoNotificador;
import com.algafood.alfaworksfoodapi.service.ClientAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClientAtivadoEvent event){
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo.");
    }
}
