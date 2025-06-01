package org.example.io;
import org.example.model.*;
import org.example.service.SistemaMonitorizacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorFicheiros {

    static SistemaMonitorizacao sistema = new SistemaMonitorizacao();

    public static void lerPacientes (){
            String caminhoDoFicheiro = "pacientes.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoFicheiro))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");

                    String nome = dados[0];
                    String dataNascimento = dados[1];
                    int altura = Integer.parseInt(dados[2]);
                    int peso = Integer.parseInt(dados[3]);

                    System.out.println("Nome: " + nome);
                    System.out.println("Data de nascimento: " + dataNascimento);
                    System.out.println("Altura: " + altura + "cm");
                    System.out.println("Peso: " + peso + "kg");
                    System.out.println("---------------------------");

                    Paciente paciente = new Paciente(nome, dataNascimento, altura, peso);
                    sistema.adicionarPessoa(paciente);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
            }
    }

    public static void lerTecnicos(){
            String caminhoDoFicheiro = "tecnicos.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoFicheiro))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");

                    String nome = dados[0];
                    String dataNascimento = dados[1];
                    String categoriaProfissional = dados[2];

                    System.out.println("Nome do técnico: " + nome);
                    System.out.println("Data de nascimento: " + dataNascimento);
                    System.out.println("Categoria profissional: " + categoriaProfissional);
                    System.out.println("---------------------------");

                    TecnicoDeSaude tecnico = new TecnicoDeSaude(nome, dataNascimento, categoriaProfissional);
                    sistema.adicionarPessoa(tecnico);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
            }
    }

    public static void lerMedicao() {
        String caminhoDoFicheiro = "adicionarmedicao.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoFicheiro))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int escolha = Integer.parseInt(dados[0]);
                String nomeTecnico = dados[1];
                TecnicoDeSaude tecnico = sistema.encontrarTecnico(nomeTecnico);
                String nomePaciente = dados[2];
                String dataColheita = dados[3];

                String tipoSinalVital;
                int valorFormatado = 0;
                switch (escolha) {
                    case 1:
                        tipoSinalVital = "Frequência Cardíaca (FC)";
                        valorFormatado = Integer.parseInt(dados[4]);
                        FrequenciaCardiaca fq = new FrequenciaCardiaca(valorFormatado, dataColheita, tecnico);
                        break;
                    case 2:
                        tipoSinalVital = "Saturação de Oxigénio";
                        valorFormatado = Integer.parseInt(dados[4]);
                        SaturacaoDeOxigenio so = new SaturacaoDeOxigenio(valorFormatado, dataColheita, tecnico);
                        break;
                    case 3:
                        tipoSinalVital = "Temperatura";
                        valorFormatado = Integer.parseInt(dados[4]);
                        Temperatura tmp = new Temperatura(valorFormatado, dataColheita, tecnico);
                        break;
                    default:
                        tipoSinalVital = "Desconhecido";
                }

                System.out.println("Nome do técnico: " + nomeTecnico);
                System.out.println("Nome do paciente: " + nomePaciente);
                System.out.println("Data da colheita: " + dataColheita);
                System.out.println("Sinal vital: " + tipoSinalVital);
                System.out.println("Valor: " + valorFormatado);
                System.out.println("---------------------------");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valores numéricos: " + e.getMessage());
        }
    }
}
