package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class SaturacaoDeOxigenio implements SinaisVitais {
    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância medicao, que é do tipo ArrayList
     */
    public ArrayList<Integer> getMedicao() {
        return medicao;
    }

    /**
     *Método tem como função filtrar medições com base num intervalo de datas fornecido (dataInicial e dataFinal)
     * O if verificar se o valor de dataInicial ou dataFinal é igual a 0. Caso qualquer uma das datas seja 0, o método assume que todas as medições disponíveis devem ser consideradas. Assim, é chamado o método getMedicao que devolve todas as medições.
     * O ciclo for, percorre todas as medições armazenadas na lista medicao e caso existam medições entre a dataInicial e a dataFinal, esta é adicionada à lista finalList.
     * @param dataInicial data mais antiga
     * @param dataFinal data mais recente
     * @return devolve uma lista dinâmica (ArrayList) de valores do tipo Integer
     */
    public ArrayList<Integer> getMedicao(Data dataInicial, Data dataFinal) {
        if (dataInicial.getValue() == 0 || dataFinal.getValue() == 0) {
            return getMedicao();
        }
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i=0; i<medicao.size(); i++) {
            if (dataColheita.get(i).getValue()>dataInicial.getValue() && dataColheita.get(i).getValue()<dataFinal.getValue()) {
                finalList.add(medicao.get(i));
            }
        }
        return finalList;
    }

    /**
     * Método de modificação, usado para atribuir um valor ao atributo medicao.
     * @param medicao valor atribuído ao atributo
     */
    public void setMedicao(ArrayList<Integer> medicao) {
        this.medicao = medicao;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância dataColheita, que é do tipo ArrayList
     */
    public ArrayList<Data> getDataColheita() {
        return dataColheita;
    }

    /**
     * Método de modificação
     * @param dataColheita
     */
    public void setDataColheita(ArrayList<Data> dataColheita) {
        this.dataColheita = dataColheita;
    }

    /**
     *Método de acesso
     * @return devolve o conteúdo da variável de instância tecnicoResponsavel, que é do tipo ArrayList
     */
    public ArrayList<TecnicoDeSaude> getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    /**
     * Método de modificação
     * @param tecnicoResponsavel
     */
    public void setTecnicoResponsavel(ArrayList<TecnicoDeSaude> tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    private ArrayList <Integer> medicao;
    private ArrayList <Data> dataColheita;
    private ArrayList <TecnicoDeSaude> tecnicoResponsavel;

    /**
     * Construtor vazio que inicializa três atributos
     */
    public SaturacaoDeOxigenio() {
        this.medicao = new ArrayList<>();
        this.dataColheita = new ArrayList<>();
        this.tecnicoResponsavel = new ArrayList<>();
    }

    /**
     * Construtor que cria um novo objeto de FrequenciaCardiaca, inicializando os seus atributos com os valores fornecidos como parâmetros.
     * @param medicao
     * @param dataColheita
     * @param tecnicoResponsavel
     */
    public SaturacaoDeOxigenio(int medicao, String dataColheita, TecnicoDeSaude tecnicoResponsavel) {
        this.medicao = new ArrayList<>();
        this.dataColheita = new ArrayList<>();
        this.tecnicoResponsavel = new ArrayList<>();
        this.medicao.add(medicao);
        this.dataColheita.add(new Data( dataColheita));
        this.tecnicoResponsavel.add(tecnicoResponsavel);
    }

    @Override
    /**
     * Método responsável por obter a última medição registada junto com a sua data correspondente
     */
    public String ultimaMedicao() {
        return medicao.getLast() + " em " + dataColheita.getLast() + " por " + tecnicoResponsavel.getLast().getNome();
    }

    @Override
    /**
     *Método usado para determinar o maior valor de medição dentro de um intervalo de datas especificado.
     */
    public double calcularMaximo(Data dataInicial, Data dataFinal) {
        return Collections.max(this.getMedicao(dataInicial,dataFinal));
    }

    @Override
    /**
     * Método usado para determinar o menor valor de medição dentro de um intervalo de datas especificado
     */
    public double calcularMinimo(Data dataInicial, Data dataFinal) {
        return Collections.min(this.getMedicao(dataInicial,dataFinal));
    }

    @Override
    /**
     * Método usado para determinar a média dentro de um intervalo de datas especificado
     */
    public double calcularMedia(Data dataInicial, Data dataFinal) {
        double soma = 0;
        for (double v : this.getMedicao(dataInicial,dataFinal)){
            soma += v;
        }
        return soma / this.getMedicao(dataInicial,dataFinal).size();
    }

    @Override
    /**
     * Método usado para determinar o desvio padrão dentro de um intervalo de datas especificado
     */
    public double calcularDesvioPadrao(Data dataInicial, Data dataFinal) {
        double soma = 0;
        double media = calcularMedia(dataInicial,dataFinal);
        for (double v : this.getMedicao(dataInicial,dataFinal)){
            soma += Math.pow(v - media, 2);
        }
        return Math.sqrt(soma / this.getMedicao(dataInicial,dataFinal).size());
    }

    @Override
    /**
     * Método usado para determinar a classificação de um estado com base no valor da última medição
     */
    public String classificacao() {
        int ultimaMedicao = medicao.getLast();
        if(ultimaMedicao >= 95){
            return "Normal";
        } else if (ultimaMedicao > 90 && ultimaMedicao < 95) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    /**
     * Este método tem como objetivo adicionar informações relacionadas à medição como os dados vitais,
     a data de colheita e o técnico que efetuou a medição.
     *No primeiro ciclo for, o programa percorre o arrayList devolvido pelo método getMedicao, e cada um
     desses valores m é armazenado na lista this.medicao
     *O mesmo raciocínio é aplicado para os restantes 2 ciclos for.
     * @param sinaisVitais é convertido para o tipo específico saturacao de oxigenio
     */
    @Override
    public void adicionar(SinaisVitais sinaisVitais) {
        SaturacaoDeOxigenio saturacao = ((SaturacaoDeOxigenio) sinaisVitais);
        for (int m:saturacao.getMedicao()){
            this.medicao.add(m);
        }
        for (Data m:saturacao.getDataColheita()){
            this.dataColheita.add(m);
        }
        for (TecnicoDeSaude m:saturacao.getTecnicoResponsavel()){
            this.tecnicoResponsavel.add(m);
        }
    }

    @Override
    public double getScore() {
        int spo2=medicao.getLast();
        if (spo2 >= 95) return 1;
        else if (spo2 >= 93) return 2;
        else if (spo2 >= 90) return 3;
        else if (spo2 >= 85) return 4;
        else return 5;
    }
}
