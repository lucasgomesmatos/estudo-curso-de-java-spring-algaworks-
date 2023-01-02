package com.algafood.alfaworksfoodapi.notificacao;

import com.algafood.alfaworksfoodapi.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
