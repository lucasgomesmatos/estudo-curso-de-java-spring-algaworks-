package com.algafood.alfaworksfoodapi.controller;

import com.algafood.alfaworksfoodapi.modelo.Cliente;
import com.algafood.alfaworksfoodapi.service.AtivacaoClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    private AtivacaoClienteService ativacaoClienteService;

    public ControllerUser(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
    }

    @GetMapping
    @ResponseBody
    public String hello() {

        Cliente joao = new Cliente("Joao", "joao@xcv.com", "34999687474");
        ativacaoClienteService.ativar(joao);

        return "Hello word! Welcome users!!!";
    }
}
