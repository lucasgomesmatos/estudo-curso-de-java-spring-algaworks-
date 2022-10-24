package com.algawords.di.notificacao;

import com.algawords.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorSMS implements Notificador {


    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.println("Notificando " + cliente.getNome() +
                " por SMS através do telefone " + cliente.getTelefone() +
                " " + mensagem);
    }
}
