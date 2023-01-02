package com.algafood.alfaworksfoodapi.notificacao;

import com.algafood.alfaworksfoodapi.modelo.Cliente;

//@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("NoficadorMock");
    }

    @Override
    public void notificar( Cliente cliente, String mensagem) {
        System.out.printf("MOCK: notificação seria enviada %s através do email %s : %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem
        );
    }
}
