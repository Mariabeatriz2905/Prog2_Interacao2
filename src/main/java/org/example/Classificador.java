package org.example;

import java.util.ArrayList;

public class Classificador {
    public static double ScorePaciente(Paciente paciente) {
        return 0.3*paciente.getFrequenciaCardiaca().getScore()+
                0.4*paciente.getTemperatura().getScore()+
                0.4*paciente.getSaturacaoDeOxigenio().getScore();
    }
    public static String classificarGravidade(Paciente paciente) {
        double score = ScorePaciente(paciente);

        if (score <= 2.0) return "Gravidade Baixa";
        else if (score <= 3.5) return "Gravidade Moderada";
        else return "Gravidade Alta";
    }

    public static Paciente pacienteEmMaiorRisco(ArrayList<Paciente> pacientes) {
        if (pacientes.isEmpty()) {
            return null;
        }
        Paciente paciente = pacientes.getFirst();
        double maxScore = 0;
        for (Paciente p : pacientes) {
            if (ScorePaciente(paciente)>maxScore){
                maxScore = ScorePaciente(paciente);
                paciente = p;
            }
        }
        return paciente;
    }

}
