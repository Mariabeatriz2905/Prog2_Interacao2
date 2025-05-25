package org.example.model;

//Class TecnicoDeSaude que herda e Pessoa e contém a categoria profissinal
public class TecnicoDeSaude extends Pessoa {
    private String categoriaProfissional;

    /**
     * Construtor da classe TecnicoDeSaude
     *
     * @param nome
     * @param dataNascimento
     * @param categoriaProfissional
     */
    public TecnicoDeSaude(String nome, String dataNascimento, String categoriaProfissional) {
        super(nome, dataNascimento);
        this.categoriaProfissional = categoriaProfissional;
    }

    /**
     * Métodos para obter e definir a categoria profissional
     */
    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(String categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }


}

