package com.algaworks.algalog.common;

public class NegocioExpection extends RuntimeException{

    public NegocioExpection(String mensagem) {
        super(mensagem);
    }
}
