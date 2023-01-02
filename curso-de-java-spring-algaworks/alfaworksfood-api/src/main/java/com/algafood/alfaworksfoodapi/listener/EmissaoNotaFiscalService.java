package com.algafood.alfaworksfoodapi.listener;

import com.algafood.alfaworksfoodapi.service.ClientAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {
    @EventListener
    public void clienteAtivadoListener(ClientAtivadoEvent event){
        System.out.println("Emitindo nota fiscal para o cliente "+ event.getCliente().getNome());
    }
}
