package org.example;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um array de medições de temperatura
 */
public class TemperaturaArray extends SinaisVitaisArray {
    private ArrayList<Double> medicao;

    /**
     * Construtor vazio que inicializa os atributos
     */
    public TemperaturaArray() {
        super();
        this.medicao = new ArrayList<>();
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância medicao, que é do tipo ArrayList
     */
    public ArrayList<Double> getMedicao() {
        return medicao;
    }

    /**
     * Método de modificação, usado para atribuir um valor ao atributo medicao.
     * @param medicao valor atribuído ao atributo
     */
    public void setMedicao(ArrayList<Double> medicao) {
        this.medicao = medicao;
    }

    /**
     * Método para obter medições dentro de um intervalo de datas
     * @param dataInicial data mais antiga
     * @param dataFinal data mais recente
     * @return lista de medições dentro do intervalo de datas
     */
    public ArrayList<Double> getMedicao(Data dataInicial, Data dataFinal) {
        ArrayList<Double> resultado = new ArrayList<>();
        ArrayList<Integer> indices = getIndicesMedicoesPorData(dataInicial, dataFinal);
        
        for (int i : indices) {
            resultado.add(medicao.get(i));
        }
        
        return resultado;
    }

    /**
     * Adiciona uma medição de temperatura
     * @param valor valor da temperatura
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarMedicao(double valor, String dataColheita, TecnicoDeSaude tecnico) {
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
        ArrayList<Double> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        return medicoesFiltradas.isEmpty() ? 0 : Collections.max(medicoesFiltradas);
    }

    @Override
    public double calcularMinimo(Data dataInicial, Data dataFinal) {
        ArrayList<Double> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        return medicoesFiltradas.isEmpty() ? 0 : Collections.min(medicoesFiltradas);
    }

    @Override
    public double calcularMedia(Data dataInicial, Data dataFinal) {
        ArrayList<Double> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        if (medicoesFiltradas.isEmpty()) {
            return 0;
        }
        
        double soma = 0;
        for (double v : medicoesFiltradas) {
            soma += v;
        }
        return soma / medicoesFiltradas.size();
    }

    @Override
    public double calcularDesvioPadrao(Data dataInicial, Data dataFinal) {
        ArrayList<Double> medicoesFiltradas = getMedicao(dataInicial, dataFinal);
        if (medicoesFiltradas.isEmpty()) {
            return 0;
        }
        
        double media = calcularMedia(dataInicial, dataFinal);
        double soma = 0;
        for (double v : medicoesFiltradas) {
            soma += Math.pow(v - media, 2);
        }
        return Math.sqrt(soma / medicoesFiltradas.size());
    }

    @Override
    public String classificacao() {
        if (medicao.isEmpty()) {
            return "Sem dados";
        }
        
        Double ultimaMedicao = medicao.getLast();
        if (ultimaMedicao >= 36 && ultimaMedicao <= 37.5) {
            return "Normal";
        } else if (ultimaMedicao > 37.5 && ultimaMedicao <= 38.5) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    public void adicionar(SinalVital sinaisVitais) {
        if (sinaisVitais instanceof Temperatura) {
            Temperatura temperatura = (Temperatura) sinaisVitais;
            this.medicao.add(temperatura.getMedicao());
            this.dataColheita.add(temperatura.getDataColheita());
            this.tecnicoResponsavel.add(temperatura.getTecnicoResponsavel());
        } else if (sinaisVitais instanceof TemperaturaArray) {
            TemperaturaArray temperaturaArray = (TemperaturaArray) sinaisVitais;
            this.medicao.addAll(temperaturaArray.getMedicao());
            this.dataColheita.addAll(temperaturaArray.getDataColheita());
            this.tecnicoResponsavel.addAll(temperaturaArray.getTecnicoResponsavel());
        }
    }

    @Override
    public double getScore() {
        if (medicao.isEmpty()) {
            return 0;
        }
        
        double temp = medicao.getLast();
        if (temp >= 36.0 && temp <= 37.5) return 1;
        else if ((temp >= 37.6 && temp <= 38.0) || (temp >= 35.5 && temp <= 35.9)) return 2;
        else if ((temp >= 38.1 && temp <= 39.0) || (temp >= 35.0 && temp <= 35.4)) return 3;
        else if ((temp >= 39.1 && temp <= 40.0) || (temp >= 34.0 && temp <= 34.9)) return 4;
        else return 5;
    }
}