package com.example.demo.modelo;

public class Terreno extends Financiamento {
    // Atributo
    private String tipoZona;

    // Construtor
    public Terreno(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String tipoZona) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    // Método para calcular o pagamento mensal
    @Override
    public double calcularPagamentoMensal() {
        double pagamentoPadrao = super.calcularPagamentoMensal();
        double pagamentoComAcrescimo = pagamentoPadrao * 1.02;
        return pagamentoComAcrescimo;
    }

    @Override
    public void mostrarDadosFinanciamento(int numero) {
        super.mostrarDadosFinanciamento(numero);
        System.out.printf("   -> Tipo de Zona: %s\n", this.tipoZona);
    }

    // Getter
    public String getTipoZona() {
        return tipoZona;
    }
}