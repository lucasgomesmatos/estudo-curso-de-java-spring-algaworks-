package com.algawords.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = false;

    public void ativar() {
        this.ativo = true;
    }

}
