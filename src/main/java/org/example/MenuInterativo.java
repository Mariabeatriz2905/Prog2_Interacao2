package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuInterativo {
    static Scanner scanner = new Scanner(System.in);
    static SistemaMonitorizacao sistema = new SistemaMonitorizacao();

    /**
     * Menu principal que define as funcionalidades que o programa executa.
     * Connsoante o número introduzido, são chamados os diferentes métodos presentes.
     */
    public static void menu() {
        int escolha;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("\n1. Adicionar Paciente");
            System.out.println("2. Adicionar Medicao");
            System.out.println("3. Adicionar Técnico");
            System.out.println("4. Calcular Estatísticas");
            System.out.println("5. Mostrar Pacientes");
            System.out.println("6. Mostrar Tecnicos de Saúde");
            System.out.println("7. Estado de paciente");
            System.out.println("8. Sair");
            System.out.print("Digite 1,2, 3, 4, 5, 6, 7 ou 8 consoante a funcionalidade que pretende usar:");
            escolha = scanner.nextInt();
            while (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5 && escolha != 6 && escolha != 7 && escolha != 8) {
                System.out.println("\nNúmero inválido! Digite novamente o número da funcionalidade em questão:");
                escolha = scanner.nextInt();
            }
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
                case 8:
                    System.out.print("A sair!");
                    break;
            }
        }while (escolha != 8);
    }

    /**
     * Este método permite adicionar uma medição de frequência cardíaca, saturação de oxigénio e temperatura,
     consoante o dígito introduzido.
     * Após a introdução do nome do paciente e do nome do técnico, são devolvidos os dois objetos do tipo
     pessoa através do método encontrarPessoa presente na classe SistemaMonitorizacao.
     * Depois da introdução do valor da medida pretendida, através do método adicionarMedicao presente na
     classe Paciente, o programa cria uma instância do tipo da medida selecionada com os parâmetros: valor
     numérico que representa a medida, a data da colheita e um objeto da classe TecnicoDeSaude. Para garantir
     que os objetos são do tipo correto, é feito um casting.
     */
    private static void adicionarMedicao() {
        System.out.println("Qual a medicao que deseja adicionar: ");
        System.out.println("1. Frequencia Cardiaca:");
        System.out.println("2. Saturação de Oxigénio: ");
        System.out.println("3. Temperatura:");
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2 && escolha != 3) {
            System.out.println("\nNúmero inválido! Digite novamente o número da medição em questão:");
            escolha = scanner.nextInt();
        }
        System.out.println("Introduza o nome do técnico:");
        String nomeTecnico = scanner.next();
        System.out.println("Introduza o nome do Paciente:");
        String nomePaciente = scanner.next();
        Pessoa tecnicoDeSaude = sistema.encontrarPessoa(nomeTecnico,"Tecnico");
        Pessoa paciente = sistema.encontrarPessoa(nomePaciente,"paciente");
        System.out.print("Data de colheita (aaaa/mm/dd): ");
        String dataColheita = scanner.next();

        if (escolha==1){
            System.out.print("Insira bpm: ");
            int bpm = scanner.nextInt();
            while (bpm <30 || bpm > 180) {
                System.out.println("\nValor de frequência cardíaca inválido! Digite um valor de frequência cardíaca válido:");
                bpm = scanner.nextInt();
            }
            ((Paciente) paciente).adicionarMedicao(new FrequenciaCardiaca(bpm,dataColheita, (TecnicoDeSaude) tecnicoDeSaude));
            System.out.print("Medição adicionada com sucesso!");
            System.out.println("\n Nome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "BPM: " + bpm);

        }else if (escolha==2){
            System.out.print("Insira saturaçao de oxigenio: ");
            int saturacao = scanner.nextInt();
            while (saturacao <80 || saturacao > 100) {
                System.out.println("\nValor de saturação de oxigénio inválido! Digite um valor de saturação de oxigénio válido:");
                saturacao = scanner.nextInt();
            }
            ((Paciente) paciente).adicionarMedicao(new SaturacaoDeOxigenio(saturacao,dataColheita, (TecnicoDeSaude) tecnicoDeSaude));
            System.out.print("Medição adicionada com sucesso!");
            System.out.println(" \nNome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "S02: " + saturacao);

        }else{
            System.out.println("Insira temperatura: ");
            double temperatura = scanner.nextDouble();
            while (temperatura <25 || temperatura > 43) {
                System.out.println("\nValor de temperatura inválido! Digite um valor de temperatura válido:");
                temperatura = scanner.nextDouble();
            }
            ((Paciente) paciente).adicionarMedicao(new Temperatura(temperatura,dataColheita, (TecnicoDeSaude) tecnicoDeSaude));
            System.out.print("Medição adicionada com sucesso!");
            System.out.println(" \nNome: " + nomePaciente + " - " + "Tecnico Responsável: " + nomeTecnico + " - " + "Temperatura: " + temperatura);
        }
        menu();
    }

    /**
     * O método solicita inputs do utilizador, que são armazenados em diferentes variáveis.
     * De seguida é criado um objeto do tipo paciente, passando por parâmetro os dados presentes nas variáveis para o construtor da classe paciente.
     * Este objeto é adicionado ao arrayList pessoas através do método adicionarPessoas que se encontra na classe SistemaMonitorizacao.
     * Por fim, é exibida uma mensagem de confirmação e é chamado o método menu para que o utilizador possa realizar outras operações.
     */
    public static void adicionarPaciente(){
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Data de Nascimento (aaaa/mm/dd): ");
        String dataNascimento = scanner.next();

        System.out.print("Altura (cm): ");
        int altura = scanner.nextInt();

        System.out.print("Peso (kg): ");
        int peso = scanner.nextInt();

        Paciente p = new Paciente(nome, dataNascimento, altura, peso);
        sistema.adicionarPessoas(p);
        System.out.println("Paciente adicionado com sucesso!");
        menu();
    }

    /**
     * Este método funciona da mesma forma que o método adicionarPaciente
     */
    public static void adicionarTecnico() {
        System.out.print("Nome: ");
        String nomeTecnico = scanner.next();

        System.out.print("Data de Nascimento (aaaa/mm/dd): ");
        String dataNascimentoTecnico = scanner.next();

        System.out.print("Categoria Profissional: ");
        String categoriaProfissional = scanner.next();

        TecnicoDeSaude t = new TecnicoDeSaude(nomeTecnico, dataNascimentoTecnico, categoriaProfissional);
        sistema.adicionarPessoas(t);
        System.out.println("Técnico adicionado com sucesso!");
        menu();
    }

    /**
     *Este método permite calcular medidas estatísticas para pacientes com base em suas informações vitais, como frequência cardíaca, saturação de oxigênio e temperatura.
     * O utilizador escolhe a medida que deseja calcular, além de escolher um paciente ou todos os pacientes (caso introduza o valor 0) e filtrar por datas.
     * O método usa um switch-case para processar a escolha do utilizador.
     * No cálculo do máximo e do mínimo, se o usuário escolher "0" (todos os pacientes), são percorridos todos os pacientes e faz-se o cálculo de cada sinal vital dentro do período fornecido.
     * Se especificar um paciente, faz o cálculo do cada sinal vital para esse paciente.
     *No caso do desvio padrao, para todos os pacientes, o método adiciona os dados de cada paciente a objetos correspondentes e calcula o desvio padrão.
     * Para um paciente específico, é calculado diretamente o desvio padrão.
     * No caso da média, para todos os pacientes, são adicionadas as medições de cada paciente para calcular a média.
     * Para um paciente específico, é calculada diretamente a média.
     * Por fim, depois do cálculo da medida desejada, é mostrado novamente o menu de calcularEstatisticas, para o utilizador poder selecionar outra ou, volta para o menu principal, caso o utilizador digite o número 5.
     */
    public static void calcularEstatisticas(){
        System.out.println("Escolha a medida estatística: ");
        System.out.println("1 - Máximo");
        System.out.println("2 - Mínimo");
        System.out.println("3 - Desvio Padrão");
        System.out.println("4 - Média");
        System.out.println("5 - Voltar para o menu");
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5) {
            System.out.println("\nNúmero inválido! Digite novamente o número da medida em questão:");
            escolha = scanner.nextInt();
        }
        if (escolha == 5){
            System.out.println("A voltar para o menu...");
            menu();
        }

        ArrayList<Paciente> pacientes = new ArrayList<>();
        String nomePaciente = "-1";
        while (!nomePaciente.equals("0")){
            nomePaciente = "";
            System.out.println("Insira o nome do paciente (0 para continuar): ");
            nomePaciente = scanner.next();
            if (nomePaciente.equals("0")){
                break;
            }
            Pessoa paciente = sistema.encontrarPessoa(nomePaciente, "paciente");
            Paciente paciente1 = ((Paciente) paciente);
            pacientes.add(paciente1);
        }
        if (pacientes.isEmpty()){
            pacientes=sistema.getPacientes();
        }

        System.out.println("Filtrar por periodo: ");
        System.out.println("Digite a data inicial (mais antiga) no formato aaaa/mm/dd ou digite 0 para serem consideradas todas as datas disponíveis: ");
        String input=scanner.next();
        Data dataInicial;
        if (input.equals("0")){
            dataInicial=new Data(0);
        }else {
            dataInicial=new Data(input);
        }
        System.out.println("Digite a data final (mais recente) no formato aaaa/mm/dd ou digite 0 para serem consideradas todas as datas disponíveis: ");
        input=scanner.next();
        Data dataFim;
        if (input.equals("0")){
            dataFim=new Data(0);
        }else {
            dataFim=new Data(input);
        }

        switch (escolha){
            case 1:
                int maxFQ=0;
                int maxO2=0;
                int maxC=0;
                for (Paciente p : pacientes){
                    maxFQ= (int) Math.max(maxFQ,p.getFrequenciaCardiaca().calcularMaximo(dataInicial,dataFim));
                    maxO2= (int) Math.max(maxO2,p.getSaturacaoDeOxigenio().calcularMaximo(dataInicial,dataFim));
                    maxC = (int) Math.max(maxC,p.getTemperatura().calcularMaximo(dataInicial,dataFim));
                }
                System.out.print("Máximo da fc: " + maxFQ);
                System.out.print("\nMáximo da O2: " + maxO2);
                System.out.print("\nMáximo da Temperatura: " + maxC);

                calcularEstatisticas();
                break;
            case 2:

                int minFQ=181;
                int minO2=101;
                int minC=44;
                for (Paciente p : sistema.getPacientes()){
                    minFQ= (int) Math.min(minFQ,p.getFrequenciaCardiaca().calcularMinimo(dataInicial,dataFim));
                    minO2= (int) Math.min(minO2,p.getSaturacaoDeOxigenio().calcularMinimo(dataInicial,dataFim));
                    minC = (int) Math.min(minC,p.getTemperatura().calcularMinimo(dataInicial,dataFim));
                }
                System.out.print("Mínimo da fc: " + minFQ);
                System.out.print("\nMínimo da O2: " + minO2);
                System.out.print("\nMínimo da Temperatura: " + minC);

                calcularEstatisticas();
                break;
            case 3:

                FrequenciaCardiaca fcs = new FrequenciaCardiaca();
                SaturacaoDeOxigenio o2s = new SaturacaoDeOxigenio();
                Temperatura ts = new Temperatura();

                for (Paciente p : sistema.getPacientes()){
                    fcs.adicionar(p.getFrequenciaCardiaca());
                    o2s.adicionar(p.getSaturacaoDeOxigenio());
                    ts.adicionar(p.getTemperatura());
                }
                System.out.println("Desvio padrão da fc: " + fcs.calcularDesvioPadrao(dataInicial,dataFim));
                System.out.println("Desvio padrão de O2: " + o2s.calcularDesvioPadrao(dataInicial,dataFim));
                System.out.println("Desvio padrão da temperatura: " + ts.calcularDesvioPadrao(dataInicial,dataFim));

                calcularEstatisticas();
                break;
            case 4:
                FrequenciaCardiaca fcs2 = new FrequenciaCardiaca();
                SaturacaoDeOxigenio o2s2 = new SaturacaoDeOxigenio();
                Temperatura ts2 = new Temperatura();

                for (Paciente p : sistema.getPacientes()){
                    fcs2.adicionar(p.getFrequenciaCardiaca());
                    o2s2.adicionar(p.getSaturacaoDeOxigenio());
                    ts2.adicionar(p.getTemperatura());
                }
                System.out.println("Média da fc: " + fcs2.calcularMedia(dataInicial,dataFim));
                System.out.println("Média de O2: " + o2s2.calcularMedia(dataInicial,dataFim));
                System.out.println("Média da Temperatura: " + ts2.calcularMedia(dataInicial,dataFim));

                calcularEstatisticas();
                break;
            case 5:
                System.out.println("A voltar para o menu...");
                menu();
                break;
        }
    }

    /**
     *Este método mostra informações sobre os técnicos armazenados na lista que é devoldida pelo método
     getTecnicosDeSaude presente na classe SistemaMonitorização
     *Se não houver nenhum técnico registrado (size() == 0), o programa exibe a mensagem:"Não há técnicos registados."
     * Caso contrário, o método percorre todos os técnicos presentes na lista e exibe o seu nome e categoria profissional
     * Por fim, volta ao menu
     */
    public static void mostrarTecnicos() {
        if (sistema.getTecnicosDeSaude().size() == 0) {
            System.out.println("\nNão há técnicos registados.");
        } else {
            System.out.println("\n=== Técnicos de Saúde ===");
            for (TecnicoDeSaude tecnico : sistema.getTecnicosDeSaude()) {
                System.out.println(" Nome: " + tecnico.getNome() + " - Categoria Profissional: " + tecnico.getCategoriaProfissional());
            }
        }
        menu();
    }

    /**
     * Este método funciona de igual forma ao método mostrarTecnicos
     */
    public static void mostrarPacientes() {
        if (sistema.getPacientes().size() == 0) {
            System.out.println("\nNão há pacientes registados.");
        } else {
            System.out.println("\n=== Pacientes ===");
            for (Paciente paciente : sistema.getPacientes()) {
                System.out.println(" Nome: " + paciente.getNome() + " - Data de Nascimento: " + paciente.getDataNascimento().toString());
            }
        }
        menu();
    }

    /**
     * O método tem como objetivo exibir informações pessoais e o estado atual de um paciente específico.
     * Através do nome introduzido pelo utilizador, o método encontrarPessoa da classe SistemadeMonitorização devolve o nome da pessoa, cuja classificação é "paciente".
     * O resultado é armazenado na variável paciente, que é do tipo Pessoa.
     * De seguida ´erealizado um casting para transformar o objeto paciente (que é do tipo Pessoa) num objeto do tipo específico Paciente.
     * Por fim, são exibidas as informações do paciente através da chamada dos respetivos métodos presentes na classe paciente e é exibido novamente o menu.
     */
    public static void estadoPaciente() {
        System.out.println("Selecione a opção: ");
        System.out.println("1 - um ou mais pacientes");
        System.out.println("2 - todos os pacietes");
        System.out.println("3 - Voltar para o menu");
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2 && escolha != 3) {
            System.out.println("\nNúmero inválido! Digite novamente o número da medida em questão:");
            escolha = scanner.nextInt();
        }
        if (escolha == 3){
            System.out.println("A voltar para o menu...");
            menu();
        }
        /// ////////

        switch (escolha){
            case 2:
                for (Paciente paciente : sistema.getPacientes()) {
                    System.out.println("Nome: " + paciente.getNome());
                    System.out.println("Data de Nascimento: " + paciente.getDataNascimento().toString());
                    System.out.println("Estado do Paciente: " + paciente.classificarPaciente());
                    System.out.println("Score de gravidade: "+ Classificador.ScorePaciente(paciente));
                    System.out.println("Classificação de score: "+Classificador.classificarGravidade(paciente));
                }
                break;
            case 1:
                ArrayList<Paciente> pacientes = new ArrayList<>();
                String nomePaciente = "-1";
                while (!nomePaciente.equals("0")){
                    nomePaciente = "";
                    System.out.println("Insira o nome do paciente (0 para continuar): ");
                    nomePaciente = scanner.next();
                    if (nomePaciente.equals("0")){
                        break;
                    }
                    Pessoa paciente = sistema.encontrarPessoa(nomePaciente, "paciente");
                    Paciente paciente1 = ((Paciente) paciente);
                    pacientes.add(paciente1);
                }
                for (Paciente paciente : pacientes) {
                    System.out.println("Nome: " + paciente.getNome());
                    System.out.println("Data de Nascimento: " + paciente.getDataNascimento().toString());
                    System.out.println("Estado do Paciente: " + paciente.classificarPaciente());
                    System.out.println("Score de gravidade: "+ Classificador.ScorePaciente(paciente));
                    System.out.println("Classificação de score: "+Classificador.classificarGravidade(paciente));
                }
                System.out.println("Paciente mais grave é: "+Classificador.pacienteEmMaiorRisco(pacientes));
                break;

        }

        return;
    }

    /**
     * Este método tem como objetivo a criação de diferentes objetos para realizar o teste do programa
     */
    public void createTestObjects() {
        TecnicoDeSaude tecnicoDeSaude1 = new TecnicoDeSaude("Maria", "1995/05/19", "Enfermeira");
        sistema.adicionarPessoas(tecnicoDeSaude1);
        TecnicoDeSaude tecnicoDeSaude = new TecnicoDeSaude("José", "1989/06/23", "Médico");
        sistema.adicionarPessoas(tecnicoDeSaude);

        Paciente paciente1 = new Paciente("Beatriz", "2006/05/29", 168, 68);
        paciente1.adicionarMedicao(new FrequenciaCardiaca(190, "2024/10/11",tecnicoDeSaude));
        paciente1.adicionarMedicao(new SaturacaoDeOxigenio(90, "2024/10/12",tecnicoDeSaude));
        paciente1.adicionarMedicao(new Temperatura(38.0,"2024/10/13",tecnicoDeSaude));
        sistema.adicionarPessoas(paciente1);

        Paciente paciente = new Paciente("Luís","1999/02/01", 182, 90);
        paciente.adicionarMedicao(new FrequenciaCardiaca(70, "2024/10/11", tecnicoDeSaude1));
        paciente.adicionarMedicao(new SaturacaoDeOxigenio(99, "2024/10/12", tecnicoDeSaude1));
        paciente.adicionarMedicao(new Temperatura(37, "2024/10/13", tecnicoDeSaude1));
        sistema.adicionarPessoas(paciente);
    }
}
