package br.com.fatec.api_links_uteis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para mensagens de boas-vindas.
 *
 * Esta classe demonstra um endpoint simples de saudação
 * para fins educacionais.
 *
 * Endpoint disponível:
 * - GET /api/hello - Mensagem de boas-vindas
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "Olá Fatec";
    }

    // Exemplo 1: Variável não utilizada (Code Smell)
    @GetMapping("unused-variable")
    public String unusedVariable() {
        String mensagem = "Olá";
        String naoUsada = "Essa variável não é usada"; // SonarQube vai detectar
        int numero = 42; // Também não usada
        return mensagem;
    }

    // Exemplo 2: Método com complexidade ciclomática alta (Code Smell)
    @GetMapping("complex-method")
    public String complexMethod(int valor) {
        String resultado = "";
        if (valor > 0) {
            if (valor < 10) {
                if (valor % 2 == 0) {
                    resultado = "Par pequeno";
                } else {
                    resultado = "Ímpar pequeno";
                }
            } else if (valor < 100) {
                if (valor % 2 == 0) {
                    resultado = "Par médio";
                } else {
                    resultado = "Ímpar médio";
                }
            } else {
                resultado = "Grande";
            }
        } else if (valor < 0) {
            resultado = "Negativo";
        } else {
            resultado = "Zero";
        }
        return resultado;
    }

    // Exemplo 3: Senha hardcoded (Security Hotspot/Vulnerability)
    @GetMapping("hardcoded-credentials")
    public String hardcodedCredentials() {
        String password = "senha123"; // SonarQube detecta credenciais hardcoded
        String apiKey = "AIzaSyD1234567890"; // API key hardcoded
        return "Credenciais configuradas";
    }

    // Exemplo 4: Possível NullPointerException (Bug)
    @GetMapping("null-pointer")
    public String nullPointer(String parametro) {
        // Não verifica se parametro é null antes de usar
        return parametro.toUpperCase(); // Potencial NullPointerException
    }

    // Exemplo 5: Resource não fechado (Bug)
    @GetMapping("resource-leak")
    public String resourceLeak() {
        try {
            java.io.FileReader reader = new java.io.FileReader("arquivo.txt");
            // Resource não é fechado - memory leak
            return "Arquivo lido";
        } catch (Exception e) {
            return "Erro";
        }
    }

    // Exemplo 6: Comparação de strings com == ao invés de equals (Bug)
    @GetMapping("string-comparison")
    public String stringComparison(String texto) {
        String referencia = "teste";
        if (texto == referencia) { // Deveria usar .equals()
            return "Igual";
        }
        return "Diferente";
    }

    // Exemplo 7: Catch block vazio (Code Smell)
    @GetMapping("empty-catch")
    public String emptyCatch() {
        try {
            int resultado = 10 / 0;
            return String.valueOf(resultado);
        } catch (Exception e) {
            // Catch vazio - má prática
        }
        return "Erro silencioso";
    }

    // Exemplo 8: Magic numbers (Code Smell)
    @GetMapping("magic-numbers")
    public String magicNumbers(int valor) {
        if (valor > 100) { // 100 é um magic number
            return "Grande";
        } else if (valor > 50) { // 50 também
            return "Médio";
        }
        return "Pequeno";
    }

    // Exemplo 9: Método muito longo (Code Smell)
    @GetMapping("long-method")
    public String longMethod() {
        String line1 = "Linha 1";
        String line2 = "Linha 2";
        String line3 = "Linha 3";
        String line4 = "Linha 4";
        String line5 = "Linha 5";
        String line6 = "Linha 6";
        String line7 = "Linha 7";
        String line8 = "Linha 8";
        String line9 = "Linha 9";
        String line10 = "Linha 10";
        // ... método muito longo com muitas linhas
        return line1 + line2 + line3 + line4 + line5 + line6 + line7 + line8 + line9 + line10;
    }

    // Exemplo 10: SQL Injection potencial (Security Vulnerability)
    @GetMapping("sql-injection")
    public String sqlInjection(String userId) {
        String query = "SELECT * FROM users WHERE id = " + userId; // Concatenação direta - SQL Injection
        return "Query: " + query;
    }

}
