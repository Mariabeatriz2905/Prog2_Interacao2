package org.example.service;

import org.example.model.Data;
import org.example.model.Paciente;

import java.util.ArrayList;

/**
 * Classe responsável por calcular estatísticas de sinais vitais
 */
public class EstatisticasCalculador {
    
    /**
     * Calcula o máximo de um sinal vital para um paciente
     * @param paciente paciente a ser analisado
     * @param tipoSinal tipo de sinal vital (1 - FC, 2 - SpO2, 3 - Temperatura)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return valor máximo do sinal vital
     */
    public static double calcularMaximo(Paciente paciente, int tipoSinal, Data dataInicial, Data dataFinal) {
        switch (tipoSinal) {
            case 1:
                return paciente.getFrequenciaCardiaca().calcularMaximo(dataInicial, dataFinal);
            case 2:
                return paciente.getSaturacaoDeOxigenio().calcularMaximo(dataInicial, dataFinal);
            case 3:
                return paciente.getTemperatura().calcularMaximo(dataInicial, dataFinal);
            default:
                return 0;
        }
    }
    
    /**
     * Calcula o mínimo de um sinal vital para um paciente
     * @param paciente paciente a ser analisado
     * @param tipoSinal tipo de sinal vital (1 - FC, 2 - SpO2, 3 - Temperatura)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return valor mínimo do sinal vital
     */
    public static double calcularMinimo(Paciente paciente, int tipoSinal, Data dataInicial, Data dataFinal) {
        switch (tipoSinal) {
            case 1:
                return paciente.getFrequenciaCardiaca().calcularMinimo(dataInicial, dataFinal);
            case 2:
                return paciente.getSaturacaoDeOxigenio().calcularMinimo(dataInicial, dataFinal);
            case 3:
                return paciente.getTemperatura().calcularMinimo(dataInicial, dataFinal);
            default:
                return 0;
        }
    }
    
    /**
     * Calcula a média de um sinal vital para um paciente
     * @param paciente paciente a ser analisado
     * @param tipoSinal tipo de sinal vital (1 - FC, 2 - SpO2, 3 - Temperatura)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return valor médio do sinal vital
     */
    public static double calcularMedia(Paciente paciente, int tipoSinal, Data dataInicial, Data dataFinal) {
        switch (tipoSinal) {
            case 1:
                return paciente.getFrequenciaCardiaca().calcularMedia(dataInicial, dataFinal);
            case 2:
                return paciente.getSaturacaoDeOxigenio().calcularMedia(dataInicial, dataFinal);
            case 3:
                return paciente.getTemperatura().calcularMedia(dataInicial, dataFinal);
            default:
                return 0;
        }
    }
    
    /**
     * Calcula o desvio padrão de um sinal vital para um paciente
     * @param paciente paciente a ser analisado
     * @param tipoSinal tipo de sinal vital (1 - FC, 2 - SpO2, 3 - Temperatura)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return valor do desvio padrão do sinal vital
     */
    public static double calcularDesvioPadrao(Paciente paciente, int tipoSinal, Data dataInicial, Data dataFinal) {
        switch (tipoSinal) {
            case 1:
                return paciente.getFrequenciaCardiaca().calcularDesvioPadrao(dataInicial, dataFinal);
            case 2:
                return paciente.getSaturacaoDeOxigenio().calcularDesvioPadrao(dataInicial, dataFinal);
            case 3:
                return paciente.getTemperatura().calcularDesvioPadrao(dataInicial, dataFinal);
            default:
                return 0;
        }
    }
    
    /**
     * Calcula estatísticas para um conjunto de pacientes
     * @param pacientes lista de pacientes
     * @param tipoEstatistica tipo de estatística (1 - Máximo, 2 - Mínimo, 3 - Desvio Padrão, 4 - Média)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return paciente acumulativo com todas as medições
     */
    public static Paciente calcularEstatisticasPacientes(ArrayList<Paciente> pacientes, int tipoEstatistica, 
                                                        Data dataInicial, Data dataFinal) {
        Paciente pacienteAcumulativo = new Paciente("acumulativo", "1111/11/11", 0, 0);
        
        for (Paciente p : pacientes) {
            pacienteAcumulativo.adicionarMedicao(p.getFrequenciaCardiaca());
            pacienteAcumulativo.adicionarMedicao(p.getSaturacaoDeOxigenio());
            pacienteAcumulativo.adicionarMedicao(p.getTemperatura());
        }
        
        return pacienteAcumulativo;
    }
    
    /**
     * Obtém o nome do tipo de sinal vital
     * @param tipoSinal tipo de sinal vital (1 - FC, 2 - SpO2, 3 - Temperatura)
     * @return nome do tipo de sinal vital
     */
    public static String getNomeSinalVital(int tipoSinal) {
        switch (tipoSinal) {
            case 1:
                return "Frequência Cardíaca";
            case 2:
                return "Saturação de Oxigênio";
            case 3:
                return "Temperatura";
            default:
                return "Desconhecido";
        }
    }
}