package com.semprestag;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controla o menu de interação e apresenta as informações encontradas no organograma.
 * Recebe os dados prontos da classe Empresa e usa as buscas da classe Grafo.
 */
public class ConsultaFuncionarios {
    /** Empresa que contém o cadastro dos funcionários e o grafo do organograma. */
    private final Empresa empresa;

    /**
     * Cria o controlador de consultas associado a uma empresa.
     *
     * @param empresa cadastro e grafo que serão consultados
     */
    public ConsultaFuncionarios(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Inicia o menu e continua lendo opções até o usuário escolher sair.
     * Opção 1 pesquisa uma pessoa; opção 2 lista o organograma com busca em largura.
     */
    public void iniciar() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Apresenta as ações disponíveis a cada volta do menu.
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Pesquisar funcionário");
                System.out.println("2 - Listar todos (busca em largura)");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                String opcao = scanner.nextLine().trim();

                // Opção 0 encerra o laço e, ao sair do try, fecha o Scanner.
                if (opcao.equals("0")) {
                    break;
                } else if (opcao.equals("1")) {
                    // Lê um nome e consulta chefe e subordinados do funcionário.
                    pesquisarFuncionario(scanner);
                } else if (opcao.equals("2")) {
                    // Percorre o grafo em largura para exibir todos os funcionários.
                    listarOrganograma();
                } else {
                    // Qualquer outro texto não corresponde a uma opção válida.
                    System.out.println("Opção inválida. Escolha 1, 2 ou 0.");
                }
            }
        }
    }

    /**
     * Lê o nome de um funcionário, mostra seu chefe e percorre todos os subordinados
     * abaixo dele usando busca em profundidade.
     *
     * @param scanner leitor de teclado já aberto pelo método iniciar
     */
    private void pesquisarFuncionario(Scanner scanner) {
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine().trim();

        // Busca o cadastro completo para obter tanto o nome quanto o cargo.
        Funcionario funcionario = empresa.buscarFuncionario(nome);

        if (funcionario == null) {
            // Sem cadastro, não há vértice ou cargo para exibir.
            System.out.println("Funcionário não encontrado. Confira o nome na lista e tente novamente.");
            return;
        }

        // Localiza o vértice correspondente e consulta a aresta que chega nele (chefe).
        System.out.println("\n" + funcionario.getNome() + " — " + funcionario.getCargo());
        Vertice<String> vertice = empresa.getGrafo().getVertice(funcionario.getNome());
        mostrarChefe(vertice);

        // A busca em profundidade retorna os subordinados diretos e indiretos.
        ArrayList<String> subordinados = empresa.getGrafo().BuscaEmProfundidade(funcionario.getNome());
        if (subordinados.isEmpty()) {
            // Uma lista vazia indica que este funcionário não lidera ninguém.
            System.out.println("Funcionários abaixo dele: nenhum");
        } else {
            System.out.println("Funcionários abaixo dele (busca em profundidade):");
            for (int i = 0; i < subordinados.size(); i++) {
                // Converte cada nome retornado pelo grafo em cadastro para exibir nome e cargo.
                Funcionario subordinado = empresa.buscarFuncionario(subordinados.get(i));
                System.out.println("- " + subordinado.getNome() + " — " + subordinado.getCargo());
            }
        }
    }

    /**
     * Mostra o chefe imediato do vértice informado.
     * Arestas de entrada apontam de um chefe para o funcionário consultado.
     *
     * @param vertice vértice do funcionário pesquisado
     */
    private void mostrarChefe(Vertice<String> vertice) {
        if (vertice.getArestasEntrada().isEmpty()) {
            // Sem arestas de entrada, o funcionário está no topo do organograma.
            System.out.println("Chefe: nenhum (topo do organograma)");
        } else {
            // A primeira aresta de entrada identifica o chefe imediato.
            Aresta<String> aresta = vertice.getArestasEntrada().get(0);
            Funcionario chefe = empresa.buscarFuncionario(aresta.getInicio().getDado());
            System.out.println("Chefe: " + chefe.getNome() + " — " + chefe.getCargo());
        }
    }

    /**
     * Lista todos os funcionários em ordem de busca em largura e mostra a relação
     * de cada um com seu chefe imediato.
     */
    private void listarOrganograma() {
        System.out.println("\nOrganograma completo (busca em largura):");

        // A versão sem parâmetro começa no primeiro vértice e inclui esse vértice no resultado.
        ArrayList<String> funcionarios = empresa.getGrafo().BuscaEmLargura();

        for (int i = 0; i < funcionarios.size(); i++) {
            // Recupera o nome atual, o cadastro completo e o vértice correspondente.
            String nome = funcionarios.get(i);
            Funcionario funcionario = empresa.buscarFuncionario(nome);
            Vertice<String> vertice = empresa.getGrafo().getVertice(nome);

            if (vertice.getArestasEntrada().isEmpty()) {
                // O vértice sem arestas de entrada é o topo do organograma.
                System.out.println("- " + funcionario.getNome() + " — " + funcionario.getCargo() + " (topo)");
            } else {
                // Mostra o início da primeira aresta de entrada como chefe imediato.
                String nomeChefe = vertice.getArestasEntrada().get(0).getInicio().getDado();
                Funcionario chefe = empresa.buscarFuncionario(nomeChefe);
                System.out.println("- " + funcionario.getNome() + " — " + funcionario.getCargo()
                        + " (reporta-se a " + chefe.getNome() + ")");
            }
        }
    }
}
