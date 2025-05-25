package org.example.model;

import java.util.ArrayList;

/**
 * Classe abstrata que representa um array de sinais vitais
 * Esta classe serve como base para arrays especializados de diferentes tipos de sinais vitais
 */
public abstract class SinaisVitaisArray implements SinaisVitais {
    protected ArrayList<Data> dataColheita;
    protected ArrayList<TecnicoDeSaude> tecnicoResponsavel;

    /**
     * Construtor vazio que inicializa os atributos
     */
    public SinaisVitaisArray() {
        this.dataColheita = new ArrayList<>();
        this.tecnicoResponsavel = new ArrayList<>();
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
     * Método de acesso
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

    /**
     * Método tem como função filtrar medições com base num intervalo de datas fornecido
     * @param dataInicial data mais antiga
     * @param dataFinal data mais recente
     * @return devolve uma lista de índices das medições que estão dentro do intervalo de datas
     */
    protected ArrayList<Integer> getIndicesMedicoesPorData(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> indices = new ArrayList<>();
        
        if (dataInicial.getValue() == 0 || dataFinal.getValue() == 0) {
            // Se qualquer uma das datas for 0, considerar todas as medições
            for (int i = 0; i < dataColheita.size(); i++) {
                indices.add(i);
            }
            return indices;
        }
        
        for (int i = 0; i < dataColheita.size(); i++) {
            if (dataColheita.get(i).getValue() > dataInicial.getValue() && 
                dataColheita.get(i).getValue() < dataFinal.getValue()) {
                indices.add(i);
            }
        }
        return indices;
    }

    /**
     * Método responsável por obter a última medição registada junto com a sua data correspondente
     */
    @Override
    public String ultimaMedicao() {
        if (dataColheita.isEmpty()) {
            return "Nenhuma medição registrada";
        }
        return getUltimoValorMedicao() + " em " + dataColheita.getLast() + " por " + tecnicoResponsavel.getLast().getNome();
    }

    /**
     * Método abstrato para obter o último valor de medição
     * @return o último valor de medição como String
     */
    protected abstract String getUltimoValorMedicao();

    /**
     * Método abstrato para adicionar um sinal vital ao array
     * @param sinaisVitais o sinal vital a ser adicionado
     */
    @Override
    public abstract void adicionar(SinalVital sinaisVitais);
    
    /**
     * Método abstrato para calcular o score de gravidade
     * @return o score de gravidade
     */
    @Override
    public abstract double getScore();

    /**
     * Método abstrato para classificar o estado do paciente
     * @return a classificação do estado do paciente
     */
    @Override
    public abstract String classificacao();
}