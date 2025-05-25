package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um array de medições de frequência cardíaca
 */
public class FrequenciaCardiacaArray extends SinaisVitaisArray implements Serializable{
    private ArrayList<Integer> medicao;

    /**
     * Construtor vazio que inicializa os atributos
     */
    public FrequenciaCardiacaArray() {
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
     * Adiciona uma medição de frequência cardíaca
     * @param valor valor da frequência cardíaca
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
        if (ultimaMedicao >= 60 && ultimaMedicao <= 100) {
            return "Normal";
        } else if (ultimaMedicao > 100 && ultimaMedicao <= 120) {
            return "Atenção";
        } else {
            return "Critico";
        }
    }

    @Override
    public void adicionar(SinalVital sinaisVitais) {
        if (sinaisVitais instanceof FrequenciaCardiaca) {
            FrequenciaCardiaca frequenciaCardiaca = (FrequenciaCardiaca) sinaisVitais;
            this.medicao.add(frequenciaCardiaca.getMedicao());
            this.dataColheita.add(frequenciaCardiaca.getDataColheita());
            this.tecnicoResponsavel.add(frequenciaCardiaca.getTecnicoResponsavel());
        } else if (sinaisVitais instanceof FrequenciaCardiacaArray) {
            FrequenciaCardiacaArray frequenciaCardiacaArray = (FrequenciaCardiacaArray) sinaisVitais;
            this.medicao.addAll(frequenciaCardiacaArray.getMedicao());
            this.dataColheita.addAll(frequenciaCardiacaArray.getDataColheita());
            this.tecnicoResponsavel.addAll(frequenciaCardiacaArray.getTecnicoResponsavel());
        }
    }

    @Override
    public double getScore() {
        if (medicao.isEmpty()) {
            return 0;
        }
        
        int fc = medicao.getLast();
        if (fc >= 60 && fc <= 100) return 1;
        else if ((fc >= 101 && fc <= 110) || (fc >= 50 && fc <= 59)) return 2;
        else if ((fc >= 111 && fc <= 130) || (fc >= 40 && fc <= 49)) return 3;
        else if ((fc >= 131 && fc <= 150) || (fc >= 30 && fc <= 39)) return 4;
        else return 5;
    }

    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dadosFrequenciaCardiaca.ser"))) {
            out.writeObject(this.medicao);
            out.writeObject(this.dataColheita);
            out.writeObject(this.tecnicoResponsavel);
            System.out.println("Dados guardados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao guardar os dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("dadosFrequenciaCardiaca.ser"))) {
            this.medicao = (ArrayList<Integer>) in.readObject();
            this.dataColheita = (ArrayList<Data>) in.readObject();
            this.tecnicoResponsavel = (ArrayList<TecnicoDeSaude>) in.readObject();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}