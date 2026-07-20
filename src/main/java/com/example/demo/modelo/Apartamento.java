package com.example.demo.modelo;

public class Apartamento extends Financiamento {
    // Atributos
    private int vagasGaragem;
    private int andarNumero;

    // Construtor
    public Apartamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, int vagasGaragem, int andarNumero) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.andarNumero = andarNumero;
    }

    // Método para calcular o pagamento mensal
    @Override
    public double calcularPagamentoMensal() {
        double totalPagamento = getValorImovel() * (1 + (getTaxaJurosAnual() * getPrazoFinanciamento()));
        double pagamentoMensal = totalPagamento / (getPrazoFinanciamento() * 12);
        return pagamentoMensal;
    }

    @Override
    public void mostrarDadosFinanciamento(int numero) {
        super.mostrarDadosFinanciamento(numero);
        System.out.printf("   -> Andar: %d, Vagas de garagem: %d\n", this.andarNumero, this.vagasGaragem);
    }

    // Getters
    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public int getAndarNumero() {
        return andarNumero;
    }
}