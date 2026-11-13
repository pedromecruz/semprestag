package com.semprestag;

import java.util.ArrayList;

/**
 * Representa um nó do grafo e guarda o dado associado a ele.
 * No sistema, cada vértice representa o nome de um funcionário.
 */
public class Vertice<TIPO> {
    /** Informação guardada neste vértice, como o nome de um funcionário. */
    private TIPO dado;

    /** Ligações que chegam a este vértice; no organograma, seus chefes. */
    private ArrayList<Aresta<TIPO>> arestasEntrada = new ArrayList<>();

    /** Ligações que saem deste vértice; no organograma, seus subordinados. */
    private ArrayList<Aresta<TIPO>> arestasSaida = new ArrayList<>();

    /**
     * Cria um vértice com o dado informado.
     *
     * @param dado informação que será guardada no vértice
     */
    public Vertice(TIPO dado) {
        this.dado = dado;
    }

    /** @return o dado guardado neste vértice */
    public TIPO getDado() {
        return dado;
    }

    /** @param dado novo dado a ser guardado neste vértice */
    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    /** @return a lista de arestas que chegam a este vértice */
    public ArrayList<Aresta<TIPO>> getArestasEntrada() {
        return arestasEntrada;
    }

    /** @param arestasEntrada nova lista de arestas de entrada */
    public void setArestasEntrada(ArrayList<Aresta<TIPO>> arestasEntrada) {
        this.arestasEntrada = arestasEntrada;
    }

    /** @return a lista de arestas que saem deste vértice */
    public ArrayList<Aresta<TIPO>> getArestasSaida() {
        return arestasSaida;
    }

    /** @param arestasSaida nova lista de arestas de saída */
    public void setArestasSaida(ArrayList<Aresta<TIPO>> arestasSaida) {
        this.arestasSaida = arestasSaida;
    }

    /**
     * Registra neste vértice uma ligação que chega a ele.
     *
     * @param aresta ligação que termina neste vértice
     */
    public void adicionarArestaEntrada(Aresta<TIPO> aresta) {
        arestasEntrada.add(aresta);
    }

    /**
     * Registra neste vértice uma ligação que parte dele.
     *
     * @param aresta ligação que começa neste vértice
     */
    public void adicionarArestaSaida(Aresta<TIPO> aresta) {
        arestasSaida.add(aresta);
    }
}
