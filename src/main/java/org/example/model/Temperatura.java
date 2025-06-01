package org.example.model;

public class Temperatura implements SinalVital {
    private Double medicao;
    private Data dataColheita;
    private TecnicoDeSaude tecnicoResponsavel;

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância medicao
     */
    public Double getMedicao() {
        return medicao;
    }

    /**
     * Método de modificação, usado para atribuir um valor ao atributo medicao.
     * @param medicao valor atribuído ao atributo
     */
    public void setMedicao(Double medicao) {
        this.medicao = medicao;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância dataColheita
     */
    public Data getDataColheita() {
        return dataColheita;
    }

    /**
     * Método de modificação
     * @param dataColheita
     */
    public void setDataColheita(Data dataColheita) {
        this.dataColheita = dataColheita;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância tecnicoResponsavel
     */
    public TecnicoDeSaude getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    /**
     * Método de modificação
     * @param tecnicoResponsavel
     */
    public void setTecnicoResponsavel(TecnicoDeSaude tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    /**
     * Construtor vazio que inicializa os atributos
     */
    public Temperatura() {
        this.medicao = null;
        this.dataColheita = null;
        this.tecnicoResponsavel = null;
    }

    /**
     * Construtor que cria um novo objeto de Temperatura, inicializando os seus atributos com os valores fornecidos como parâmetros.
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public Temperatura(double medicao, String dataColheita, TecnicoDeSaude tecnicoResponsavel) {
        this.medicao = medicao;
        this.dataColheita = new Data(dataColheita);
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    /**
     * Construtor completo
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public Temperatura(Double medicao, Data dataColheita, TecnicoDeSaude tecnicoResponsavel) {
        this.medicao = medicao;
        this.dataColheita = dataColheita;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    @Override
    /**
     * Método responsável por obter a medição registada junto com a sua data correspondente
     */
    public String ultimaMedicao() {
        return medicao + " em " + dataColheita + " por " + tecnicoResponsavel.getNome();
    }

    @Override
    /**
     * Método usado para determinar a classificação de um estado com base no valor da medição
     */
    public String classificacao() {
        if(medicao >= 36 && medicao <= 37.5){
            return "Normal";
        } else if (medicao > 37.5 && medicao <= 38.5) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    /**
     * Para cada intervalo de temperatura é atribuida uma pontuação de 1 a 5, sendo 1 correspondente a valores normais de temperatura e 5 valores de maior gravidade.
     */
    public double getScore() {
        double temp = medicao;
        if (temp >= 36.0 && temp <= 37.5) return 1;
        else if ((temp >= 37.6 && temp <= 38.0) || (temp >= 35.5 && temp <= 35.9)) return 2;
        else if ((temp >= 38.1 && temp <= 39.0) || (temp >= 35.0 && temp <= 35.4)) return 3;
        else if ((temp >= 39.1 && temp <= 40.0) || (temp >= 34.0 && temp <= 34.9)) return 4;
        else return 5;
    }
}