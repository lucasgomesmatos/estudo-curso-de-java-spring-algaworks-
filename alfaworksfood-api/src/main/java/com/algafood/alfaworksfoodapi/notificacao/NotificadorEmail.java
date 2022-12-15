package com.algafood.alfaworksfoodapi.notificacao;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Notificador real");
    }

    @Override
    public void notificar( Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do email %s : %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem
        );

    }
}
