package org.example.service;

import org.example.model.Paciente;
import org.example.model.TecnicoDeSaude;

/**
 * Classe responsável por gerenciar as operações do menu
 * Esta classe foi criada para separar a lógica de negócio da interface com o usuário
 */
public class MenuGerenciador {
    private SistemaMonitorizacao sistema;

    /**
     * Construtor que inicializa o sistema de monitorização
     * @param sistema sistema de monitorização
     */
    public MenuGerenciador(SistemaMonitorizacao sistema) {
        this.sistema = sistema;
    }

    /**
     * Adiciona um paciente ao sistema
     * @param nome nome do paciente
     * @param dataNascimento data de nascimento do paciente
     * @param altura altura do paciente em cm
     * @param peso peso do paciente em kg
     * @return o paciente adicionado
     */
    public Paciente adicionarPaciente(String nome, String dataNascimento, int altura, int peso) {
        Paciente paciente = new Paciente(nome, dataNascimento, altura, peso);
        sistema.adicionarPessoa(paciente);
        return paciente;
    }

    /**
     * Adiciona um técnico de saúde ao sistema
     * @param nome nome do técnico
     * @param dataNascimento data de nascimento do técnico
     * @param categoriaProfissional categoria profissional do técnico
     * @return o técnico adicionado
     */
    public TecnicoDeSaude adicionarTecnico(String nome, String dataNascimento, String categoriaProfissional) {
        TecnicoDeSaude tecnico = new TecnicoDeSaude(nome, dataNascimento, categoriaProfissional);
        sistema.adicionarPessoa(tecnico);
        return tecnico;
    }

    /**
     * Adiciona uma medição de frequência cardíaca a um paciente
     * @param paciente paciente a receber a medição
     * @param valor valor da frequência cardíaca
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarFrequenciaCardiaca(Paciente paciente, int valor, String dataColheita, TecnicoDeSaude tecnico) {
        paciente.adicionarFrequenciaCardiaca(valor, dataColheita, tecnico);
    }

    /**
     * Adiciona uma medição de saturação de oxigênio a um paciente
     * @param paciente paciente a receber a medição
     * @param valor valor da saturação de oxigênio
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarSaturacaoDeOxigenio(Paciente paciente, int valor, String dataColheita, TecnicoDeSaude tecnico) {
        paciente.adicionarSaturacaoDeOxigenio(valor, dataColheita, tecnico);
    }

    /**
     * Adiciona uma medição de temperatura a um paciente
     * @param paciente paciente a receber a medição
     * @param valor valor da temperatura
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarTemperatura(Paciente paciente, double valor, String dataColheita, TecnicoDeSaude tecnico) {
        paciente.adicionarTemperatura(valor, dataColheita, tecnico);
    }

    /**
     * Encontra um paciente pelo nome
     * @param nome nome do paciente
     * @return o paciente encontrado ou null se não encontrar
     */
    public Paciente encontrarPaciente(String nome) {
        return sistema.encontrarPaciente(nome);
    }

    /**
     * Encontra um técnico pelo nome
     * @param nome nome do técnico
     * @return o técnico encontrado ou null se não encontrar
     */
    public TecnicoDeSaude encontrarTecnico(String nome) {
        return sistema.encontrarTecnico(nome);
    }

    /**
     * Obtém todos os pacientes do sistema
     * @return lista de pacientes
     */
    public java.util.ArrayList<Paciente> getPacientes() {
        return sistema.getPacientes();
    }

    /**
     * Obtém todos os técnicos de saúde do sistema
     * @return lista de técnicos de saúde
     */
    public java.util.ArrayList<TecnicoDeSaude> getTecnicosDeSaude() {
        return sistema.getTecnicosDeSaude();
    }

    /**
     * Cria objetos de teste para o sistema
     */
    public void createTestObjects() {
        TecnicoDeSaude tecnicoDeSaude1 = new TecnicoDeSaude("Maria", "1995/05/19", "Enfermeira");
        sistema.adicionarPessoa(tecnicoDeSaude1);
        TecnicoDeSaude tecnicoDeSaude = new TecnicoDeSaude("José", "1989/06/23", "Médico");
        sistema.adicionarPessoa(tecnicoDeSaude);

        Paciente paciente1 = new Paciente("Beatriz", "2006/05/29", 168, 68);
        paciente1.adicionarFrequenciaCardiaca(190, "2024/10/11", tecnicoDeSaude);
        paciente1.adicionarSaturacaoDeOxigenio(90, "2024/10/12", tecnicoDeSaude);
        paciente1.adicionarTemperatura(38.0, "2024/10/13", tecnicoDeSaude);
        sistema.adicionarPessoa(paciente1);

        Paciente paciente = new Paciente("Luís", "1999/02/01", 182, 90);
        paciente.adicionarFrequenciaCardiaca(70, "2024/10/11", tecnicoDeSaude1);
        paciente.adicionarSaturacaoDeOxigenio(99, "2024/10/12", tecnicoDeSaude1);
        paciente.adicionarTemperatura(37, "2024/10/13", tecnicoDeSaude1);
        sistema.adicionarPessoa(paciente);
    }
    /**
     * Altera subitamente de forma percentual os sinais vitais dos pacientes
     * @param percentagem variação da percentagem
     */
    public void alterarSinaisVitais(double percentagem){
        sistema.alterarSinaisVitais(percentagem);
    }

    /**
     * Mostra a percentagem de pacientes em situação critica
     * @return percentagem de pacientes em situação critica
     */
    public int pacientesEmSituacaoCritica(){
        return sistema.pacientesEmSituacaoCritica();
    }
}