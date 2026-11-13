package com.semprestag;

/**
 * Representa uma ligação direcionada entre dois vértices do grafo.
 * No organograma, a ligação parte do chefe e chega ao subordinado.
 * O parâmetro genérico {@code TIPO} indica o tipo de dado guardado nos vértices.
 */
public class Aresta<TIPO> {
    /** Peso associado à ligação; no organograma usamos 1.0. */
    private Double peso;

    /** Vértice de onde a ligação parte. */
    private Vertice<TIPO> inicio;

    /** Vértice ao qual a ligação chega. */
    private Vertice<TIPO> fim;

    /**
     * Cria uma ligação direcionada entre dois vértices.
     *
     * @param peso valor associado à ligação
     * @param inicio vértice de origem da ligação
     * @param fim vértice de destino da ligação
     */
    public Aresta(Double peso, Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    /** @return o peso atual da ligação */
    public Double getPeso() {
        return peso;
    }

    /** @param peso novo peso da ligação */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /** @return o vértice de origem da ligação */
    public Vertice<TIPO> getInicio() {
        return inicio;
    }

    /** @param inicio novo vértice de origem */
    public void setInicio(Vertice<TIPO> inicio) {
        this.inicio = inicio;
    }

    /** @return o vértice de destino da ligação */
    public Vertice<TIPO> getFim() {
        return fim;
    }

    /** @param fim novo vértice de destino */
    public void setFim(Vertice<TIPO> fim) {
        this.fim = fim;
    }
}
