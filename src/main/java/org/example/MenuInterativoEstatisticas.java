package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Métodos para cálculo de estatísticas do MenuInterativo
 */
public class MenuInterativoEstatisticas {
    /**
     * Este método permite calcular medidas estatísticas para pacientes com base em suas informações vitais,
     * como frequência cardíaca, saturação de oxigênio e temperatura.
     * O utilizador escolhe a medida que deseja calcular, além de escolher um paciente ou todos os pacientes
     * e filtrar por datas.
     */
    public static void calcularEstatisticas(Scanner scanner, MenuGerenciador gerenciador) {
        System.out.println("Escolha a medida estatística: ");
        System.out.println("1 - Máximo");
        System.out.println("2 - Mínimo");
        System.out.println("3 - Desvio Padrão");
        System.out.println("4 - Média");
        System.out.println("5 - Voltar para o menu");
        int escolha = lerOpcao(scanner, 1, 5);
        
        if (escolha == 5) {
            System.out.println("A voltar para o menu...");
            return;
        }

        // Selecionar pacientes
        ArrayList<Paciente> pacientes = selecionarPacientes(scanner, gerenciador);
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente selecionado ou disponível.");
            return;
        }

        // Filtrar por período
        Data[] periodo = filtrarPorPeriodo(scanner);
        Data dataInicial = periodo[0];
        Data dataFinal = periodo[1];

        // Calcular estatísticas
        calcularEMostrarEstatisticas(pacientes, escolha, dataInicial, dataFinal);
    }
    
    /**
     * Lê uma opção do usuário dentro de um intervalo válido
     * @param min valor mínimo válido
     * @param max valor máximo válido
     * @return opção escolhida pelo usuário
     */
    private static int lerOpcao(Scanner scanner, int min, int max) {
        int opcao = scanner.nextInt();
        while (opcao < min || opcao > max) {
            System.out.println("\nNúmero inválido! Digite novamente o número da funcionalidade em questão:");
            opcao = scanner.nextInt();
        }
        return opcao;
    }
    
    /**
     * Permite ao usuário selecionar pacientes específicos ou todos os pacientes
     * @return lista de pacientes selecionados
     */
    private static ArrayList<Paciente> selecionarPacientes(Scanner scanner, MenuGerenciador gerenciador) {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        
        System.out.println("Deseja selecionar pacientes específicos?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não (todos os pacientes)");
        int escolha = lerOpcao(scanner, 1, 2);
        
        if (escolha == 1) {
            String nomePaciente = "-1";
            while (!nomePaciente.equals("0")) {
                System.out.println("Insira o nome do paciente (0 para continuar): ");
                nomePaciente = scanner.next();
                
                if (nomePaciente.equals("0")) {
                    break;
                }
                
                Paciente paciente = gerenciador.encontrarPaciente(nomePaciente);
                if (paciente != null) {
                    pacientes.add(paciente);
                } else {
                    System.out.println("Paciente não encontrado!");
                }
            }
        } else {
            pacientes = gerenciador.getPacientes();
        }
        
        return pacientes;
    }
    
    /**
     * Permite ao usuário filtrar as estatísticas por período
     * @return array com data inicial e data final
     */
    private static Data[] filtrarPorPeriodo(Scanner scanner) {
        System.out.println("Filtrar por periodo: ");
        System.out.println("Digite a data inicial (mais antiga) no formato aaaa/mm/dd ou digite 0 para serem consideradas todas as datas disponíveis: ");
        String input = scanner.next();
        Data dataInicial;
        if (input.equals("0")) {
            dataInicial = new Data(0);
        } else {
            dataInicial = new Data(input);
        }
        
        System.out.println("Digite a data final (mais recente) no formato aaaa/mm/dd ou digite 0 para serem consideradas todas as datas disponíveis: ");
        input = scanner.next();
        Data dataFinal;
        if (input.equals("0")) {
            dataFinal = new Data(0);
        } else {
            dataFinal = new Data(input);
        }
        
        return new Data[] { dataInicial, dataFinal };
    }
    
    /**
     * Calcula e mostra as estatísticas para os pacientes selecionados
     * @param pacientes lista de pacientes
     * @param tipoEstatistica tipo de estatística (1 - Máximo, 2 - Mínimo, 3 - Desvio Padrão, 4 - Média)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     */
    private static void calcularEMostrarEstatisticas(ArrayList<Paciente> pacientes, int tipoEstatistica, 
                                             Data dataInicial, Data dataFinal) {
        // Paciente acumulativo para estatísticas combinadas
        Paciente pacienteAcumulativo = EstatisticasCalculador.calcularEstatisticasPacientes(
            pacientes, tipoEstatistica, dataInicial, dataFinal);
        
        // Mostrar estatísticas para cada paciente
        for (Paciente p : pacientes) {
            System.out.println("\n=== Estatísticas para " + p.getNome() + " ===");
            mostrarEstatisticaPorTipo(p, tipoEstatistica, dataInicial, dataFinal);
        }
        
        // Mostrar estatísticas combinadas
        System.out.println("\n=== Estatísticas combinadas de todos os pacientes ===");
        mostrarEstatisticaPorTipo(pacienteAcumulativo, tipoEstatistica, dataInicial, dataFinal);
    }
    
    /**
     * Mostra estatísticas de um tipo específico para um paciente
     * @param paciente paciente a ser analisado
     * @param tipoEstatistica tipo de estatística (1 - Máximo, 2 - Mínimo, 3 - Desvio Padrão, 4 - Média)
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     */
    private static void mostrarEstatisticaPorTipo(Paciente paciente, int tipoEstatistica, Data dataInicial, Data dataFinal) {
        String nomeEstatistica;
        switch (tipoEstatistica) {
            case 1:
                nomeEstatistica = "Máximo";
                break;
            case 2:
                nomeEstatistica = "Mínimo";
                break;
            case 3:
                nomeEstatistica = "Desvio Padrão";
                break;
            case 4:
                nomeEstatistica = "Média";
                break;
            default:
                nomeEstatistica = "Desconhecido";
        }
        
        // Frequência Cardíaca
        double valorFC = 0;
        switch (tipoEstatistica) {
            case 1:
                valorFC = EstatisticasCalculador.calcularMaximo(paciente, 1, dataInicial, dataFinal);
                break;
            case 2:
                valorFC = EstatisticasCalculador.calcularMinimo(paciente, 1, dataInicial, dataFinal);
                break;
            case 3:
                valorFC = EstatisticasCalculador.calcularDesvioPadrao(paciente, 1, dataInicial, dataFinal);
                break;
            case 4:
                valorFC = EstatisticasCalculador.calcularMedia(paciente, 1, dataInicial, dataFinal);
                break;
        }
        System.out.println(nomeEstatistica + " da Frequência Cardíaca: " + valorFC);
        
        // Saturação de Oxigênio
        double valorO2 = 0;
        switch (tipoEstatistica) {
            case 1:
                valorO2 = EstatisticasCalculador.calcularMaximo(paciente, 2, dataInicial, dataFinal);
                break;
            case 2:
                valorO2 = EstatisticasCalculador.calcularMinimo(paciente, 2, dataInicial, dataFinal);
                break;
            case 3:
                valorO2 = EstatisticasCalculador.calcularDesvioPadrao(paciente, 2, dataInicial, dataFinal);
                break;
            case 4:
                valorO2 = EstatisticasCalculador.calcularMedia(paciente, 2, dataInicial, dataFinal);
                break;
        }
        System.out.println(nomeEstatistica + " da Saturação de Oxigênio: " + valorO2);
        
        // Temperatura
        double valorTemp = 0;
        switch (tipoEstatistica) {
            case 1:
                valorTemp = EstatisticasCalculador.calcularMaximo(paciente, 3, dataInicial, dataFinal);
                break;
            case 2:
                valorTemp = EstatisticasCalculador.calcularMinimo(paciente, 3, dataInicial, dataFinal);
                break;
            case 3:
                valorTemp = EstatisticasCalculador.calcularDesvioPadrao(paciente, 3, dataInicial, dataFinal);
                break;
            case 4:
                valorTemp = EstatisticasCalculador.calcularMedia(paciente, 3, dataInicial, dataFinal);
                break;
        }
        System.out.println(nomeEstatistica + " da Temperatura: " + valorTemp);
    }
}