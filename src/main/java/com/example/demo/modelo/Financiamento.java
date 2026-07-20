package com.example.demo.modelo;

import java.io.Serializable;

// Classe Abstrata - Financiamento
public abstract class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;

    // Atributos - Todos os atributos devem ser privados
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // Construtor
    public Financiamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Métodos
    // Para calcular o pagamento mensal
    public double calcularPagamentoMensal() {
        return (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 + (this.taxaJurosAnual / 12));
    }

    // Para calcular o total do pagamento
    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }

    // Getters para atributos
    public double getValorImovel() {
        return this.valorImovel;
    }

    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    // Mostrar os dados do financiamento (mantido por segurança caso precise imprimir no console depois)
    public void mostrarDadosFinanciamento(int numero) {
        System.out.printf("Financiamento %d – valor do imóvel: R$ %.2f, valor do financiamento: R$ %.2f.\n",
                numero, this.valorImovel, this.calcularTotalPagamento());
    }
}