package com.algawords.di.notificacao;

import com.algawords.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
