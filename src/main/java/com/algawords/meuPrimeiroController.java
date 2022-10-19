package com.algawords;

import com.algawords.di.service.AtivacaoClienteService;
import com.algawords.modelo.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class meuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public meuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;

        System.out.println("Meu primeiro construtor " + ativacaoClienteService);
    }

    @GetMapping("api/hello")
    @ResponseBody
    public String hello() {

        Cliente maria = new Cliente("maria", "maria@xyz.com", "33999639999");

        ativacaoClienteService.ativar(maria);

        return "Olá mundo!";
    }
}

/* Aula 02.12*/