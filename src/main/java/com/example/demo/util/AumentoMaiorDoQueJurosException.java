package com.example.demo.util;

// Exceção para validar o acréscimo do financiamento da Casa
public class AumentoMaiorDoQueJurosException extends RuntimeException {

    // Construtor - Publico
    public AumentoMaiorDoQueJurosException(String mensagem) {
        super(mensagem);
    }
}