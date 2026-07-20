package com.example.demo.controller;

// Importa todas as suas classes de modelo (Casa, Apartamento, Terreno, Financiamento)
import com.example.demo.modelo.*;
// Importa a sua exceção customizada que está no pacote util
import com.example.demo.util.AumentoMaiorDoQueJurosException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
public class SimuladorController {

    // Rota inicial: Quando o usuário acessar http://localhost:8080/
    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o arquivo index.html da pasta templates
    }

    // Rota para a página de Taxas (Rates)
    @GetMapping("/rates")
    public String rates() {
        return "rates"; // Vai procurar o arquivo rates.html
    }

    // Rota para a página de Login
    @GetMapping("/login")
    public String login() {
        return "login"; // Vai procurar o arquivo login.html
    }

    // Rota de cálculo: Quando o usuário clicar em "Calcular Simulação" no HTML
    @PostMapping("/simular")
    public String simular(
            @RequestParam String tipoImovel,
            @RequestParam double valorImovel,
            @RequestParam double valorEntrada,
            @RequestParam int prazo,
            Model model) {

        // O valor que vai para a sua classe Financiamento é o valor descontando a entrada
        double valorFinanciado = valorImovel - valorEntrada;

        // A interface tem a taxa travada em 8.5% ao ano
        double taxaJuros = 0.085;

        Financiamento financiamento = null;

        try {
            // Usa as suas classes e o seu polimorfismo!
            // Como a tela não pergunta área ou vagas, preenchemos com valores padrão exigidos pelo seu construtor
            if (tipoImovel.equals("Casa")) {
                financiamento = new Casa(valorFinanciado, prazo, taxaJuros, 150.0, 300.0);
            } else if (tipoImovel.equals("Apartamento")) {
                financiamento = new Apartamento(valorFinanciado, prazo, taxaJuros, 2, 5);
            } else if (tipoImovel.equals("Terreno")) {
                financiamento = new Terreno(valorFinanciado, prazo, taxaJuros, "Residencial");
            }

            // Se o financiamento foi criado com sucesso, faz o cálculo
            if (financiamento != null) {
                // Chama o SEU método de cálculo (Polimorfismo em ação)
                double parcelaCalculada = financiamento.calcularPagamentoMensal();

                // Formatador de moeda para transformar "6124.3" em "6.124,30" (Padrão Brasil)
                NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
                formatador.setMinimumFractionDigits(2);
                formatador.setMaximumFractionDigits(2);

                // Envia os resultados para a tela HTML usando os nomes das variáveis definidas no th:text
                model.addAttribute("parcelaInicial", formatador.format(parcelaCalculada));
                model.addAttribute("valorFinanciado", formatador.format(valorFinanciado));
                model.addAttribute("ultimaParcela", formatador.format(parcelaCalculada));
            }

        } catch (AumentoMaiorDoQueJurosException e) {
            // Se a exceção da Casa for disparada (Acréscimo maior que o juros),
            // manda a mensagem de erro para o HTML exibir a caixa vermelha
            model.addAttribute("erro", e.getMessage());
        }

        return "index"; // Devolve a mesma tela index.html, mas agora atualizada com os valores
    }
}