package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável pela interação com o usuário através de menus
 */
public class MenuInterativo {
    private Scanner scanner;
    private MenuGerenciador gerenciador;

    /**
     * Construtor que inicializa o scanner e o gerenciador de menu
     */
    public MenuInterativo() {
        this.scanner = new Scanner(System.in);
        SistemaMonitorizacao sistema = new SistemaMonitorizacao();
        this.gerenciador = new MenuGerenciador(sistema);
    }

    /**
     * Menu principal que define as funcionalidades que o programa executa.
     * Consoante o número introduzido, são chamados os diferentes métodos presentes.
     */
    public void menu() {
        int escolha;
        do {
            exibirMenuPrincipal();
            escolha = lerOpcao(1, 10);
            
            switch (escolha) {
                case 1:
                    adicionarPaciente();
                    break;
                case 2:
                    adicionarMedicao();
                    break;
                case 3:
                    adicionarTecnico();
                    break;
                case 4:
                    calcularEstatisticas();
                    break;
                case 5:
                    mostrarPacientes();
                    break;
                case 6:
                    mostrarTecnicos();
                    break;
                case 7:
                    estadoPaciente();
                    break;
                case 8:
                    alteracaoSinaisVitais();
                    break; // JOANA
                case 9:
                    pacientesEmSituacaoCritica();
                    break; // JOANA
                case 10:
                    System.out.print("A sair!");
                    break;
            }
        } while (escolha != 10);
    }
    
    /**
     * Exibe o menu principal
     */
    private void exibirMenuPrincipal() {
        System.out.println("\n=== MENU ===");
        System.out.println("\n1. Adicionar Paciente");
        System.out.println("2. Adicionar Medicao");
        System.out.println("3. Adicionar Técnico");
        System.out.println("4. Calcular Estatísticas");
        System.out.println("5. Mostrar Pacientes");
        System.out.println("6. Mostrar Tecnicos de Saúde");
        System.out.println("7. Estado de paciente");
        System.out.println("8. Sair");
        System.out.print("Digite 1,2, 3, 4, 5, 6, 7 ou 8 consoante a funcionalidade que pretende usar: ");
    }
    
    /**
     * Lê uma opção do usuário dentro de um intervalo válido
     * @param min valor mínimo válido
     * @param max valor máximo válido
     * @return opção escolhida pelo usuário
     */
    private int lerOpcao(int min, int max) {
        int opcao = -1;
        boolean valido = false;

        while (!valido) {
            try {
                opcao = scanner.nextInt();
                if (opcao >= min && opcao <= max) {
                    valido = true;
                } else {
                    System.out.println("Número fora do intervalo. Tente novamente:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro:");
                scanner.next(); // limpa entrada inválida
            }
        }

        return opcao;
    }


    /**
     * Este método permite adicionar uma medição de frequência cardíaca, saturação de oxigénio e temperatura,
     * consoante o dígito introduzido.
     */
    private void adicionarMedicao() {
        System.out.println("Qual a medicao que deseja adicionar: ");
        System.out.println("1. Frequencia Cardiaca:");
        System.out.println("2. Saturação de Oxigénio: ");
        System.out.println("3. Temperatura:");
        int escolha = lerOpcao(1, 3);
        
        System.out.println("Introduza o nome do técnico:");
        String nomeTecnico = scanner.next();
        System.out.println("Introduza o nome do Paciente:");
        String nomePaciente = scanner.next();
        
        TecnicoDeSaude tecnicoDeSaude = gerenciador.encontrarTecnico(nomeTecnico);
        Paciente paciente = gerenciador.encontrarPaciente(nomePaciente);
        
        if (tecnicoDeSaude == null || paciente == null) {
            System.out.println("Técnico ou paciente não encontrado!");
            return;
        }
        
        System.out.print("Data de colheita (aaaa/mm/dd): ");
        String dataColheita = scanner.next();

        switch (escolha) {
            case 1:
                adicionarFrequenciaCardiaca(paciente, tecnicoDeSaude, dataColheita, nomePaciente, nomeTecnico);
                break;
            case 2:
                adicionarSaturacaoOxigenio(paciente, tecnicoDeSaude, dataColheita, nomePaciente, nomeTecnico);
                break;
            case 3:
                adicionarTemperatura(paciente, tecnicoDeSaude, dataColheita, nomePaciente, nomeTecnico);
                break;
        }
    }
    
    /**
     * Adiciona uma medição de frequência cardíaca
     */
    private void adicionarFrequenciaCardiaca(Paciente paciente, TecnicoDeSaude tecnico, String dataColheita, 
                                           String nomePaciente, String nomeTecnico) {
        System.out.print("Insira bpm: ");
        int bpm = -1;
        while (true) {
            try {
                bpm = scanner.nextInt();
                if (bpm >= 30 && bpm <= 180) break;
                System.out.println("Valor fora do intervalo (30-180). Tente novamente:");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro:");
                scanner.next();
            }
        }


        paciente.adicionarFrequenciaCardiaca(bpm, dataColheita, tecnico);
        System.out.print("Medição adicionada com sucesso!");
        System.out.println("\n Nome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "BPM: " + bpm);
    }
    
    /**
     * Adiciona uma medição de saturação de oxigênio
     */
    private void adicionarSaturacaoOxigenio(Paciente paciente, TecnicoDeSaude tecnico, String dataColheita, 
                                          String nomePaciente, String nomeTecnico) {
        System.out.print("Insira saturaçao de oxigenio: ");
        int saturacao = -1;
        while (true) {
            try {
                saturacao = scanner.nextInt();
                if (saturacao >= 80 && saturacao <= 100) break;
                System.out.println("Valor fora do intervalo (80-100). Tente novamente:");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro:");
                scanner.next();
            }
        }


        paciente.adicionarSaturacaoDeOxigenio(saturacao, dataColheita, tecnico);
        System.out.print("Medição adicionada com sucesso!");
        System.out.println(" \nNome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "S02: " + saturacao);
    }
    
    /**
     * Adiciona uma medição de temperatura
     */
    private void adicionarTemperatura(Paciente paciente, TecnicoDeSaude tecnico, String dataColheita, 
                                    String nomePaciente, String nomeTecnico) {
        System.out.println("Insira temperatura: ");
        double temperatura = scanner.nextDouble();
        while (temperatura < 25 || temperatura > 43) {
            System.out.println("\nValor de temperatura inválido! Digite um valor de temperatura válido:");
            temperatura = scanner.nextDouble();
        }

        paciente.adicionarTemperatura(temperatura, dataColheita, tecnico);
        System.out.print("Medição adicionada com sucesso!");
        System.out.println(" \nNome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "Temperatura: " + temperatura);
    }

    /**
     * O método solicita inputs do utilizador, que são armazenados em diferentes variáveis.
     * De seguida é criado um objeto do tipo paciente, passando por parâmetro os dados presentes nas variáveis para o construtor da classe paciente.
     * Este objeto é adicionado ao arrayList pessoas através do método adicionarPessoas que se encontra na classe SistemaMonitorizacao.
     * Por fim, é exibida uma mensagem de confirmação e é chamado o método menu para que o utilizador possa realizar outras operações.
     */
    private void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Data de Nascimento (aaaa/mm/dd): ");
        String dataNascimento = scanner.next();

        System.out.print("Altura (cm): ");
        int altura = scanner.nextInt();

        System.out.print("Peso (kg): ");
        int peso = scanner.nextInt();

        gerenciador.adicionarPaciente(nome, dataNascimento, altura, peso);
        System.out.println("Paciente adicionado com sucesso!");
    }

    /**
     * Este método funciona da mesma forma que o método adicionarPaciente
     */
    private void adicionarTecnico() {
        System.out.print("Nome: ");
        String nomeTecnico = scanner.next();

        System.out.print("Data de Nascimento (aaaa/mm/dd): ");
        String dataNascimentoTecnico = scanner.next();

        System.out.print("Categoria Profissional: ");
        String categoriaProfissional = scanner.next();

        gerenciador.adicionarTecnico(nomeTecnico, dataNascimentoTecnico, categoriaProfissional);
        System.out.println("Técnico adicionado com sucesso!");
    }
    /**
     * Este método permite calcular medidas estatísticas para pacientes com base em suas informações vitais,
     * como frequência cardíaca, saturação de oxigênio e temperatura.
     */
    private void calcularEstatisticas() {
        MenuInterativoEstatisticas.calcularEstatisticas(scanner, gerenciador);
    }



    /**
     * Este método mostra informações sobre os técnicos armazenados na lista que é devolvida pelo método
     * getTecnicosDeSaude presente na classe SistemaMonitorização
     * Se não houver nenhum técnico registrado (size() == 0), o programa exibe a mensagem:"Não há técnicos registados."
     * Caso contrário, o método percorre todos os técnicos presentes na lista e exibe o seu nome e categoria profissional
     */
    private void mostrarTecnicos() {
        ArrayList<TecnicoDeSaude> tecnicos = gerenciador.getTecnicosDeSaude();
        if (tecnicos.isEmpty()) {
            System.out.println("\nNão há técnicos registados.");
        } else {
            System.out.println("\n=== Técnicos de Saúde ===");
            for (TecnicoDeSaude tecnico : tecnicos) {
                System.out.println(" Nome: " + tecnico.getNome() + " - Categoria Profissional: " + tecnico.getCategoriaProfissional());
            }
        }
    }

    /**
     * Este método funciona de igual forma ao método mostrarTecnicos
     */
    private void mostrarPacientes() {
        ArrayList<Paciente> pacientes = gerenciador.getPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("\nNão há pacientes registados.");
        } else {
            System.out.println("\n=== Pacientes ===");
            for (Paciente paciente : pacientes) {
                System.out.println(" Nome: " + paciente.getNome() + " - Data de Nascimento: " + paciente.getDataNascimento().toString());
            }
        }
    }

    /**
     * O método tem como objetivo exibir informações pessoais e o estado atual de um paciente específico.
     * Através do nome introduzido pelo utilizador, o método encontrarPaciente da classe SistemaMonitorizacao devolve o paciente.
     * Por fim, são exibidas as informações do paciente através da chamada dos respetivos métodos presentes na classe paciente.
     */
    private void estadoPaciente() {
        System.out.println("Selecione a opção: ");
        System.out.println("1 - Um ou mais pacientes");
        System.out.println("2 - Todos os pacientes");
        System.out.println("3 - Voltar para o menu");
        int escolha = lerOpcao(1, 3);
        
        if (escolha == 3) {
            System.out.println("A voltar para o menu...");
            return;
        }

        switch (escolha) {
            case 1:
                mostrarEstadoPacientesEspecificos();
                break;
            case 2:
                mostrarEstadoTodosPacientes();
                break;
        }
    }
    
    /**
     * Mostra o estado de pacientes específicos selecionados pelo usuário
     */
    private void mostrarEstadoPacientesEspecificos() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
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
        
        for (Paciente paciente : pacientes) {
            mostrarEstadoPaciente(paciente);
        }
        
        if (!pacientes.isEmpty()) {
            Paciente pacienteMaisGrave = Classificador.pacienteEmMaiorRisco(pacientes);
            System.out.println("Paciente mais grave é: " + pacienteMaisGrave.getNome());
        }
    }
    
    /**
     * Mostra o estado de todos os pacientes
     */
    private void mostrarEstadoTodosPacientes() {
        ArrayList<Paciente> pacientes = gerenciador.getPacientes();
        
        if (pacientes.isEmpty()) {
            System.out.println("Não há pacientes registrados!");
            return;
        }
        
        for (Paciente paciente : pacientes) {
            mostrarEstadoPaciente(paciente);
        }
    }
    
    /**
     * Mostra o estado de um paciente específico
     * @param paciente paciente a ser analisado
     */
    private void mostrarEstadoPaciente(Paciente paciente) {
        System.out.println("\n=== Estado do Paciente ===");
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("Data de Nascimento: " + paciente.getDataNascimento().toString());
        System.out.println("Estado do Paciente: " + paciente.classificarPaciente());
        System.out.println("Score de gravidade: " + Classificador.ScorePaciente(paciente));
        System.out.println("Classificação de score: " + Classificador.classificarGravidade(paciente));
        System.out.println("==============================");
    }
    
    /**
     * Este método tem como objetivo a criação de diferentes objetos para realizar o teste do programa
     */
    public void createTestObjects() {
        gerenciador.createTestObjects();
    }

    private static void alteracaoSinaisVitais(){
        double percentagem = 0;
        boolean inputValido = false;

        while(!inputValido){
            System.out.println("Insira a percentagem que pretende criar a alteração súbita");
            String input = scanner.next();
            try{
                percentagem = Double.parseDouble(input);
                this.gerenciador.alterarSinaisVitais(percentagem);
                inputValido = true;
            }catch(NumberFormatException e){
                System.out.println("A percentagem que introduziu não é valida!(introduza um número)");
            }
        }

    }

    private static void pacientesEmSituacaoCritica(){
        int pacientesSituacaoCritica = this.gerenciador.pacientesEmSituacaoCritica();
        System.out.println("Pacientes em Situação Crítica(%): " + pacientesSituacaoCritica + "%");
    }

}

