package org.example;

/**
 * Classe abstrata pessoa
 */
public abstract class Pessoa {
    private String nome;
    private Data dataNascimento;


    /**
     * Construtor completo da classe pessoa
     *
     * @param nome
     * @param dataNascimento
     */
    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = new Data(dataNascimento);
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método de acesso
     *
     * @return devolve o conteúdo da variável de instância dataNascimento
     */
    public Data getDataNascimento() {
        return dataNascimento;
    }
}



