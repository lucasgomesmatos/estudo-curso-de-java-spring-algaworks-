package com.algawords.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = false;


    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void ativar() {
        this.ativo = true;
    }

}
