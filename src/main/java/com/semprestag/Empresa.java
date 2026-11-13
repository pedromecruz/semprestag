package com.semprestag;

import java.util.ArrayList;

/**
 * Centraliza os funcionários da Semprestag e monta o grafo do organograma.
 * Os nomes ficam no cadastro de funcionários e também são usados como dados dos vértices.
 */
public class Empresa {
    /** Cadastro que associa o nome de cada pessoa ao seu cargo. */
    private final ArrayList<Funcionario> funcionarios = new ArrayList<>();

    /** Grafo direcionado em que cada vértice é um nome e cada aresta liga chefe a subordinado. */
    private final Grafo<String> grafo = new Grafo<>();

    /**
     * Adiciona uma pessoa ao cadastro e cria seu vértice no grafo.
     * Este método é privado porque o cadastro inicial é definido nesta classe.
     *
     * @param nome nome do funcionário
     * @param cargo cargo do funcionário
     */
    private void cadastrar(String nome, String cargo) {
        // Guarda nome e cargo juntos para exibir informações completas nas consultas.
        funcionarios.add(new Funcionario(nome, cargo));

        // Cria no grafo um vértice identificado pelo nome desta pessoa.
        grafo.adicionarVertice(nome);
    }

    /**
     * Prepara os dados iniciais da empresa.
     * Primeiro cadastra todos os funcionários; depois cria as ligações de chefia.
     */
    public Empresa() {
        // Cada chamada registra o nome, o cargo e o vértice correspondente.
        cadastrar("Rafael Mendes", "Rei Estagiário");
        cadastrar("Lucas Azevedo", "Estagiário diretor geral");
        cadastrar("Marina Costa", "Estagiário diretor comercial");
        cadastrar("Bruno Nogueira", "Estagiário Sênior - Financeiro");
        cadastrar("Camila Rocha", "Estagiário Sênior - Marketing");
        cadastrar("Diego Lima", "Estagiário Pleno - Pós Vendas");
        cadastrar("Cecília Alves", "Estagiário Pleno - Consultoria");
        cadastrar("Fernanda Souza", "Estagiário Pleno - Design");
        cadastrar("Gustavo Martins", "Estagiário Pleno - Cobrança");
        cadastrar("Helena Ribeiro", "Estagiário Pleno - Atendimento Ativo");
        cadastrar("Igor Carvalho", "Estagiário Junior - Faz tudo");

        // As arestas só são criadas depois que todos os vértices já existem.
        mapearArestas();
    }

    /**
     * Cria as ligações direcionadas que representam as relações de chefia.
     * Em cada chamada, o primeiro nome é o chefe e o segundo é o subordinado.
     */
    private void mapearArestas() {
        // Presidente -> Diretor Geral e Diretor Comercial.
        grafo.adicionarAresta(1.0, "Rafael Mendes", "Lucas Azevedo");
        grafo.adicionarAresta(1.0, "Rafael Mendes", "Marina Costa");

        // Diretor Geral -> Financeiro e Marketing.
        grafo.adicionarAresta(1.0, "Lucas Azevedo", "Bruno Nogueira");
        grafo.adicionarAresta(1.0, "Lucas Azevedo", "Camila Rocha");

        // Financeiro -> Cobrança; Marketing -> Design.
        grafo.adicionarAresta(1.0, "Bruno Nogueira", "Gustavo Martins");
        grafo.adicionarAresta(1.0, "Camila Rocha", "Fernanda Souza");

        // Diretor Comercial -> Pós Vendas e Consultoria.
        grafo.adicionarAresta(1.0, "Marina Costa", "Diego Lima");
        grafo.adicionarAresta(1.0, "Marina Costa", "Cecília Alves");

        // Pós Vendas -> Atendimento Ativo; Consultoria -> Assistente.
        grafo.adicionarAresta(1.0, "Diego Lima", "Helena Ribeiro");
        grafo.adicionarAresta(1.0, "Cecília Alves", "Igor Carvalho");
    }

    /**
     * Fornece a lista de funcionários cadastrados.
     *
     * @return lista com os funcionários e seus cargos
     */
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Mostra no console todos os nomes e cargos cadastrados.
     * Percorre a lista com um índice, do primeiro ao último funcionário.
     */
    public void listarFuncionarios() {
        System.out.println("Funcionários da Semprestag:");
        for (int i = 0; i < funcionarios.size(); i++) {
            // Obtém o funcionário da posição atual e mostra seus dados.
            Funcionario funcionario = funcionarios.get(i);
            System.out.println("- " + funcionario.getNome() + " — " + funcionario.getCargo());
        }
    }

    /**
     * Fornece o grafo que representa as relações do organograma.
     *
     * @return grafo de nomes ligados por relações de chefia
     */
    public Grafo<String> getGrafo() {
        return grafo;
    }

    /**
     * Procura um funcionário pelo nome, ignorando diferenças entre maiúsculas e minúsculas.
     * Percorre o cadastro em ordem e devolve a primeira correspondência encontrada.
     *
     * @param nome nome digitado para a pesquisa
     * @return funcionário encontrado ou {@code null} se não houver correspondência
     */
    public Funcionario buscarFuncionario(String nome) {
        for (int i = 0; i < funcionarios.size(); i++) {
            // Consulta a pessoa armazenada na posição atual.
            Funcionario funcionario = funcionarios.get(i);
            if (funcionario.getNome().equalsIgnoreCase(nome)) {
                // Interrompe a busca assim que encontra o nome.
                return funcionario;
            }
        }
        // Nenhum nome da lista corresponde ao texto pesquisado.
        return null;
    }
}
