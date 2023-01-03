package com.algaworks.algalog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class Destinario {

    @NotBlank
    @Column(name = "destinario_nome")
    private String nome;

    @NotBlank
    @Column(name = "destinario_logradouro")
    private String logradouro;

    @NotBlank
    @Column(name = "destinario_numero")
    private String numero;

    @NotBlank
    @Column(name = "destinario_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "destinario_bairro")
    private String bairro;
}
