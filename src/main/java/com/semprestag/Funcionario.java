package com.semprestag;

/**
 * Armazena os dados de identificação de um funcionário da Semprestag.
 * O nome identifica a pessoa e o cargo descreve sua função no organograma.
 */
public class Funcionario {
    /** Nome usado pelo usuário para localizar o funcionário. */
    private final String nome;

    /** Cargo do funcionário no organograma. */
    private final String cargo;

    /**
     * Cria um funcionário com nome e cargo.
     *
     * @param nome nome da pessoa
     * @param cargo cargo ocupado pela pessoa
     */
    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    /** @return nome da pessoa */
    public String getNome() {
        return nome;
    }

    /** @return cargo ocupado pela pessoa */
    public String getCargo() {
        return cargo;
    }
}
