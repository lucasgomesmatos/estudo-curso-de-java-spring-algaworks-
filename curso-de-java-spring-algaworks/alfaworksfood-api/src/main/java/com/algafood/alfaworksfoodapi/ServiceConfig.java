package com.algafood.alfaworksfoodapi;

import com.algafood.alfaworksfoodapi.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ServiceConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AtivacaoClienteService ativacaoClienteService() {

        return new AtivacaoClienteService();
    }
}
