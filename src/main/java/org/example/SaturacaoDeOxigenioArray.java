package org.example;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um array de medições de saturação de oxigênio
 */
public class SaturacaoDeOxigenioArray extends SinaisVitaisArray {
    private ArrayList<Integer> medicao;

    /**
     * Construtor vazio que inicializa os atributos
     */
    public SaturacaoDeOxigenioArray() {
        super();
        this.medicao = new ArrayList<>();
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância medicao, que é do tipo ArrayList
     */
    public ArrayList<Integer> getMedicao() {
        return medicao;
    }

    /**
     * Método de modificação, usado para atribuir um valor ao atributo medicao.
     * @param medicao valor atribuído ao atributo
     */
    public void setMedicao(ArrayList<Integer> medicao) {
        this.medicao = medicao;
    }

    /**
     * Método para obter medições dentro de um intervalo de datas
     * @param dataInicial data mais antiga
     * @param dataFinal data mais recente
     * @return lista de medições dentro do intervalo de datas
     */
    public ArrayList<Integer> getMedicao(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> resultado = new ArrayList<>();
        ArrayList<Integer> indices = getIndicesMedicoesPorData(dataInicial, dataFinal);
        
        for (int i : indices) {
            resultado.add(medicao.get(i));
        }
        
        return resultado;
    }

    /**
     * Adiciona uma medição de saturação de oxigênio
     * @param valor valor da saturação de oxigênio
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarMedicao(int valor, String dataColheita, TecnicoDeSaude tecnico) {
        this.medicao.add(valor);
        this.dataColheita.add(new Data(dataColheita));
        this.tecnicoResponsavel.add(tecnico);
    }

    @Override
    protected String getUltimoValorMedicao() {
        return medicao.isEmpty() ? "N/A" : String.valueOf(medicao.getLast());
    }

    @Override
    public double calcularMaximo(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        return medicoesFiltradas.isEmpty() ? 0 : Collections.max(medicoesFiltradas);
    }

    @Override
    public double calcularMinimo(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        return medicoesFiltradas.isEmpty() ? 0 : Collections.min(medicoesFiltradas);
    }

    @Override
    public double calcularMedia(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        if (medicoesFiltradas.isEmpty()) {
            return 0;
        }
        
        double soma = 0;
        for (int v : medicoesFiltradas) {
            soma += v;
        }
        return soma / medicoesFiltradas.size();
    }

    @Override
    public double calcularDesvioPadrao(Data dataInicial, Data dataFinal) {
        ArrayList<Integer> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        if (medicoesFiltradas.isEmpty()) {
            return 0;
        }
        
        double media = calcularMedia(dataInicial, dataFinal);
        double soma = 0;
        for (int v : medicoesFiltradas) {
            soma += Math.pow(v - media, 2);
        }
        return Math.sqrt(soma / medicoesFiltradas.size());
    }

    @Override
    public String classificacao() {
        if (medicao.isEmpty()) {
            return "Sem dados";
        }
        
        int ultimaMedicao = medicao.getLast();
        if (ultimaMedicao >= 95) {
            return "Normal";
        } else if (ultimaMedicao > 90 && ultimaMedicao < 95) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    public void adicionar(SinalVital sinaisVitais) {
        if (sinaisVitais instanceof SaturacaoDeOxigenio) {
            SaturacaoDeOxigenio saturacao = (SaturacaoDeOxigenio) sinaisVitais;
            this.medicao.add(saturacao.getMedicao());
            this.dataColheita.add(saturacao.getDataColheita());
            this.tecnicoResponsavel.add(saturacao.getTecnicoResponsavel());
        } else if (sinaisVitais instanceof SaturacaoDeOxigenioArray) {
            SaturacaoDeOxigenioArray saturacaoArray = (SaturacaoDeOxigenioArray) sinaisVitais;
            this.medicao.addAll(saturacaoArray.getMedicao());
            this.dataColheita.addAll(saturacaoArray.getDataColheita());
            this.tecnicoResponsavel.addAll(saturacaoArray.getTecnicoResponsavel());
        }
    }

    @Override
    public double getScore() {
        if (medicao.isEmpty()) {
            return 0;
        }
        
        int spo2 = medicao.getLast();
        if (spo2 >= 95) return 1;
        else if (spo2 >= 93) return 2;
        else if (spo2 >= 90) return 3;
        else if (spo2 >= 85) return 4;
        else return 5;
    }
}