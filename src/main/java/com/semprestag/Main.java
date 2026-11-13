package com.semprestag;

/**
 * Ponto de entrada do programa.
 * Mantém a sequência principal curta e delega cada responsabilidade às classes próprias.
 */
public class Main {
    /**
     * Inicializa os dados e inicia a interação com o usuário.
     * Passos: cria a empresa, mostra seus funcionários e abre o menu de consultas.
     *
     * @param args argumentos opcionais recebidos ao iniciar o programa
     */
    public static void main(String[] args) {
        // Construtor cadastra os funcionários e mapeia as arestas do organograma.
        Empresa empresa = new Empresa();

        // Exibe os nomes e cargos disponíveis para pesquisa.
        empresa.listarFuncionarios();

        // Entrega o controle da interação para a classe de consultas.
        new ConsultaFuncionarios(empresa).iniciar();
    }
}
