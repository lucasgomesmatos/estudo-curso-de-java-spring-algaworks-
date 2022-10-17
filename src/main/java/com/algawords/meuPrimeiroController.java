package com.algawords;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class meuPrimeiroController {

    @GetMapping("api/hello")
    @ResponseBody
    public String hello() {
        return "Olá mundo!";
    }
}
