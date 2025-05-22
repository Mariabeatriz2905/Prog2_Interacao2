package org.example;

public class FrequenciaCardiaca implements SinalVital {
    private Integer medicao;
    private Data dataColheita;
    private TecnicoDeSaude tecnicoResponsavel;

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
     * Construtor vazio que inicializa os atributos da classe FrequenciaCardiaca
     */
    public FrequenciaCardiaca() {
        this.medicao = null;
        this.dataColheita = null;
        this.tecnicoResponsavel = null;
    }

    /**
     * Construtor completo
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public FrequenciaCardiaca(Integer medicao, Data dataColheita, TecnicoDeSaude tecnicoResponsavel) {
        this.medicao = medicao;
        this.dataColheita = dataColheita;
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    /**
     * Construtor que cria um novo objeto de FrequenciaCardiaca, inicializando os seus atributos com os valores fornecidos como parâmetros.
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public FrequenciaCardiaca(int medicao, String dataColheita, TecnicoDeSaude tecnicoResponsavel) {
        this.medicao = medicao;
        this.dataColheita = new Data(dataColheita);
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    @Override
    /**
     * Método responsável por obter a medição registada junto com a sua data correspondente
     */
    public String ultimaMedicao() {
        return medicao + " em " + dataColheita;
    }

    @Override
    /**
     * Método usado para determinar a classificação de um estado com base no valor da medição
     */
    public String classificacao() {
        if(medicao >= 60 && medicao <= 100){
            return "Normal";
        } else if (medicao > 100 && medicao <= 120) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    public double getScore() {
        int fc = medicao;
        if (fc >= 60 && fc <= 100) return 1;
        else if ((fc >= 101 && fc <= 110) || (fc >= 50 && fc <= 59)) return 2;
        else if ((fc >= 111 && fc <= 130) || (fc >= 40 && fc <= 49)) return 3;
        else if ((fc >= 131 && fc <= 150) || (fc >= 30 && fc <= 39)) return 4;
        else return 5;
    }
}