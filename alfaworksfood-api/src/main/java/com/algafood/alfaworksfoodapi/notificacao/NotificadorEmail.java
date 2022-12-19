package com.algafood.alfaworksfoodapi.notificacao;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

//    @Value("${notificador.email.host-servidor}")
//    private String host;
//    @Value("${notificador.email.port-servidor}")
//    private Integer porta;

    @Autowired
    private NotificadorProperties notificadorProperties;

    public NotificadorEmail() {
        System.out.println("Notificador real");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + notificadorProperties.getHostServidor());
        System.out.println("Porta: " + notificadorProperties.getPortServidor());

        System.out.printf("Notificando %s através do email %s : %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem
        );

    }
}
