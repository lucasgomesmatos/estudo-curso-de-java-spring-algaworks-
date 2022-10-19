package com.algawords.di.notificacao;

import com.algawords.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail implements Notificador {

    public  NotificadorEmail() {
        System.out.println("NotificadorEmail");
    }


    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Notificando " + cliente.getNome() +
                " através do e-mail " + cliente.getEmail() +
                " " + mensagem);
    }
}
