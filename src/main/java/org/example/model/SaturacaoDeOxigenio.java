package org.example.model;

public class SaturacaoDeOxigenio implements SinalVital {
    private Integer medicao;
    private Data dataColheita;
    private TecnicoDeSaude tecnicoResponsavel;

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância medicao
     */
    public Integer getMedicao() {
        return medicao;
    }

    /**
     * Método de modificação, usado para atribuir um valor ao atributo medicao.
     * @param medicao valor atribuído ao atributo
     */
    public void setMedicao(Integer medicao) {
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
    public SaturacaoDeOxigenio() {
        this.medicao = null;
        this.dataColheita = null;
        this.tecnicoResponsavel = null;
    }

    /**
     * Construtor que cria um novo objeto de SaturacaoDeOxigenio, inicializando os seus atributos com os valores fornecidos como parâmetros.
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public SaturacaoDeOxigenio(int medicao, String dataColheita, TecnicoDeSaude tecnicoResponsavel) {
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
    public SaturacaoDeOxigenio(Integer medicao, Data dataColheita, TecnicoDeSaude tecnicoResponsavel) {
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
        if(medicao >= 95){
            return "Normal";
        } else if (medicao > 90 && medicao < 95) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    public double getScore() {
        int spo2 = medicao;
        if (spo2 >= 95) return 1;
        else if (spo2 >= 93) return 2;
        else if (spo2 >= 90) return 3;
        else if (spo2 >= 85) return 4;
        else return 5;
    }
}