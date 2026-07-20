package com.example.demo.modelo;

import com.example.demo.util.AumentoMaiorDoQueJurosException;

public class Casa extends Financiamento {
    // Atributos - Privados
    private double areaConstruida;
    private double tamanhoTerreno;

    // Construtor - Publico
    public Casa(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    // Metodo para calcular o pagamento mensal - Publico
    @Override
    public double calcularPagamentoMensal() {
        double amortizacao = getValorImovel() / (getPrazoFinanciamento() * 12);
        double jurosMensalidade = amortizacao * (getTaxaJurosAnual() / 12);
        double acrescimo = 80.0;

        if (acrescimo > (jurosMensalidade / 2)) {
            throw new AumentoMaiorDoQueJurosException("Erro: O acréscimo de R$ 80 é maior do que a metade do valor dos juros da mensalidade.");
        }

        return super.calcularPagamentoMensal() + acrescimo;
    }

    // Getters
    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }
}