package org.example.utils;

import org.example.model.Paciente;

import java.util.ArrayList;

/**
 * Classe utilitária responsável por calcular o score de gravidade de um paciente,
 * classificar o seu estado clínico e identificar o paciente em maior risco.
 */
public class Classificador {
    /**
     * Calcula o score de gravidade de um paciente com base nos seus sinais vitais:
     * Frequência Cardíaca, Temperatura e Saturação de Oxigénio.
     * <p>
     * Os pesos aplicados são:
     * - Frequência Cardíaca: 0.3
     * - Temperatura: 0.4
     * - Saturação de Oxigénio: 0.4
     *
     * @param paciente o paciente a ser avaliado
     * @return o score de gravidade (valor entre 1 e 5)
     */
    public static double ScorePaciente(Paciente paciente) {
        return 0.3*paciente.getFrequenciaCardiaca().getScore()+
                0.4*paciente.getTemperatura().getScore()+
                0.4*paciente.getSaturacaoDeOxigenio().getScore();
    }
    /**
      * Classifica o estado de gravidade de um paciente com base no seu score.
      *
      * @param paciente o paciente a ser classificado
     * @return uma string com a classificação: "Gravidade Baixa", "Gravidade Moderada" ou "Gravidade Alta"
     */
    public static String classificarGravidade(Paciente paciente) {
        double score = ScorePaciente(paciente);

        if (score <= 2.0) return "Gravidade Baixa";
        else if (score <= 3.5) return "Gravidade Moderada";
        else return "Gravidade Alta";
    }
    /**
     * Identifica o paciente com maior risco (score mais elevado) numa lista de pacientes.
     * @param pacientes lista de pacientes a ser analisada
     * @return o paciente com o score mais elevado ou null se a lista estiver vazia
      */
    public static Paciente pacienteEmMaiorRisco(ArrayList<Paciente> pacientes) {
        if (pacientes.isEmpty()) {
            return null;
        }
        Paciente paciente = pacientes.getFirst();
        double maxScore = 0;
        for (Paciente p : pacientes) {
            if (ScorePaciente(p)>maxScore){
                maxScore = ScorePaciente(paciente);
                paciente = p;
            }
        }
        return paciente;
    }

}
