package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um array de medições de temperatura
 */
public class TemperaturaArray extends SinaisVitaisArray implements Serializable{
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
     *
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
    /**
     * Para cada intervalo de temperatura é atribuida uma pontuação de 1 a 5, sendo 1 correspondente a valores normais de temperatura e 5 valores de maior gravidade.
     */
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

    /**
     * Método guarda os dados da temperatura num ficheiro binário chamado "dadosTemperatura.ser" utilizando a serialização.
     */
    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dadosTemperatura.ser"))) {
            out.writeObject(this.medicao);
            out.writeObject(this.dataColheita);
            out.writeObject(this.tecnicoResponsavel);
            System.out.println("Dados guardados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao guardar os dados: " + e.getMessage());
        }
    }

    /**
     * Método carrega os dados presentes no ficheiro "dadosTemperatura.ser utilizando a deserialização
     */
    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("dadosTemperatura.ser"))) {
            this.medicao = (ArrayList<Double>) in.readObject();
            this.dataColheita = (ArrayList<Data>) in.readObject();
            this.tecnicoResponsavel = (ArrayList<TecnicoDeSaude>) in.readObject();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}