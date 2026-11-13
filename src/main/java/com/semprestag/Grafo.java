package com.semprestag;

import java.util.ArrayList;

/**
 * Estrutura genérica que guarda vértices e arestas direcionadas.
 * Neste projeto, cada dado de vértice é o nome de um funcionário e cada aresta
 * representa uma relação de chefia.
 *
 * @param <TIPO> tipo de dado guardado em cada vértice
 */
public class Grafo<TIPO> {
    /** Todos os vértices cadastrados no grafo. */
    private ArrayList<Vertice<TIPO>> vertices = new ArrayList<>();

    /** Todas as arestas criadas no grafo. */
    private ArrayList<Aresta<TIPO>> arestas = new ArrayList<>();

    /**
     * Cria e adiciona um vértice ao grafo.
     *
     * @param dado informação que identificará o vértice
     */
    public void adicionarVertice(TIPO dado) {
        vertices.add(new Vertice<>(dado));
    }

    /**
     * Cria uma ligação direcionada entre dois vértices já existentes.
     * Passos: procura a origem e o destino, cria a aresta, registra-a como saída
     * na origem, como entrada no destino e também na lista geral do grafo.
     *
     * @param peso valor associado à aresta
     * @param dadoInicio dado que identifica o vértice de origem
     * @param dadoFim dado que identifica o vértice de destino
     * @throws IllegalArgumentException se a origem ou o destino não existir
     */
    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        // Localiza os dois vértices usando os dados recebidos.
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);

        // Não permite criar uma ligação apontando para vértices inexistentes.
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Os dois vértices precisam existir antes da aresta.");
        }

        // Cria a aresta e registra sua direção nas listas dos vértices envolvidos.
        Aresta<TIPO> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        arestas.add(aresta);
    }

    /**
     * Procura um vértice pelo dado que ele guarda.
     *
     * @param dado dado que será comparado com os vértices cadastrados
     * @return vértice correspondente ou {@code null} se não for encontrado
     */
    public Vertice<TIPO> getVertice(TIPO dado) {
        for (int i = 0; i < vertices.size(); i++) {
            // Examina o vértice na posição atual.
            Vertice<TIPO> vertice = vertices.get(i);
            if (vertice.getDado().equals(dado)) {
                // Para assim que encontra um vértice com o dado procurado.
                return vertice;
            }
        }
        // A lista inteira foi percorrida sem encontrar correspondência.
        return null;
    }

    /**
     * Percorre o grafo em largura a partir do dado informado.
     * A busca visita primeiro os vizinhos mais próximos e usa uma fila para
     * manter a ordem de visita. O vértice inicial não é incluído no resultado.
     *
     * @param dadoInicio dado que identifica o vértice inicial
     * @return dados dos vértices alcançados, em ordem de busca em largura
     */
    public ArrayList<TIPO> BuscaEmLargura(TIPO dadoInicio) {
        ArrayList<TIPO> visitados = new ArrayList<>();

        // Confere se o vértice inicial existe antes de iniciar a busca.
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        if (inicio == null) {
            return visitados;
        }

        // A fila controla a ordem; marcados impede visitar o mesmo vértice duas vezes.
        ArrayList<Vertice<TIPO>> fila = new ArrayList<>();
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
        fila.add(inicio);
        marcados.add(inicio);

        // Continua enquanto houver vértices aguardando na fila.
        while (!fila.isEmpty()) {
            // Remove o primeiro da fila: este é o próximo a ser visitado.
            Vertice<TIPO> atual = fila.remove(0);
            if (atual != inicio) {
                visitados.add(atual.getDado());
            }

            // Examina as arestas que saem do vértice atual.
            for (int i = 0; i < atual.getArestasSaida().size(); i++) {
                Aresta<TIPO> aresta = atual.getArestasSaida().get(i);
                Vertice<TIPO> proximo = aresta.getFim();
                if (!marcados.contains(proximo)) {
                    // Marca e coloca o novo vértice no fim da fila para visita posterior.
                    marcados.add(proximo);
                    fila.add(proximo);
                }
            }
        }
        return visitados;
    }

    /**
     * Percorre em largura começando pelo primeiro vértice do grafo.
     * Esta versão inclui o vértice inicial no resultado.
     *
     * @return todos os dados alcançáveis a partir do primeiro vértice, em ordem de largura
     */
    public ArrayList<TIPO> BuscaEmLargura() {
        // Um grafo vazio não tem vértice inicial para percorrer.
        if (vertices.isEmpty()) {
            return new ArrayList<>();
        }

        // Guarda o primeiro dado e acrescenta a busca em largura a partir dele.
        ArrayList<TIPO> resultado = new ArrayList<>();
        resultado.add(vertices.get(0).getDado());
        resultado.addAll(BuscaEmLargura(vertices.get(0).getDado()));
        return resultado;
    }

    /**
     * Percorre o grafo em profundidade a partir do dado informado.
     * A busca segue cada caminho até onde for possível antes de voltar para
     * visitar outro caminho. O vértice inicial não é incluído no resultado.
     *
     * @param dadoInicio dado que identifica o vértice inicial
     * @return dados dos vértices alcançados, em ordem de busca em profundidade
     */
    public ArrayList<TIPO> BuscaEmProfundidade(TIPO dadoInicio) {
        ArrayList<TIPO> visitados = new ArrayList<>();
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();

        // Localiza o início e só chama o método recursivo se ele existir.
        Vertice<TIPO> inicio = getVertice(dadoInicio);

        if (inicio != null) {
            buscaEmProfundidade(inicio, marcados, visitados);
        }
        return visitados;
    }

    /**
     * Método auxiliar recursivo que executa a busca em profundidade.
     * Marca o vértice atual e visita cada vizinho ainda não marcado antes de
     * continuar com o próximo vizinho.
     *
     * @param atual vértice que está sendo examinado
     * @param marcados vértices já visitados, para evitar repetições e ciclos
     * @param visitados dados dos subordinados encontrados, na ordem da busca
     */
    private void buscaEmProfundidade(Vertice<TIPO> atual,
                                     ArrayList<Vertice<TIPO>> marcados,
                                     ArrayList<TIPO> visitados) {
        // Marca o vértice atual antes de explorar suas ligações.
        marcados.add(atual);

        // Percorre as arestas de saída uma a uma.
        for (int i = 0; i < atual.getArestasSaida().size(); i++) {
            Vertice<TIPO> proximo = atual.getArestasSaida().get(i).getFim();
            if (!marcados.contains(proximo)) {
                // Registra o vizinho e aprofunda a busca a partir dele imediatamente.
                visitados.add(proximo.getDado());
                buscaEmProfundidade(proximo, marcados, visitados);
            }
        }
    }
}
