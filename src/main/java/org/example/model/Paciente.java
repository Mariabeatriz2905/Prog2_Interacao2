package org.example.model;

/**
 * Classe Paciente que herda de Pessoa e contém sinais vitais
 */
public class Paciente extends Pessoa {
    private int altura; // altura em cm
    private int peso; // peso em kg
    private FrequenciaCardiacaArray frequenciaCardiaca;
    private SaturacaoDeOxigenioArray saturacaoDeOxigenio;
    private TemperaturaArray temperatura;

    /**
     * Construtor completo
     * @param nome nome do paciente
     * @param dataNascimento data de nascimento do paciente
     * @param altura altura do paciente em cm
     * @param peso peso do paciente em kg
     */
    public Paciente(String nome, String dataNascimento, int altura, int peso) {
        super(nome, dataNascimento);
        this.altura = altura;
        this.peso = peso;
        this.frequenciaCardiaca = new FrequenciaCardiacaArray();
        this.saturacaoDeOxigenio = new SaturacaoDeOxigenioArray();
        this.temperatura = new TemperaturaArray();
    }

    /**
     * Método de acesso para altura
     * @return altura do paciente em cm
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Método de acesso para peso
     * @return peso do paciente em kg
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância frequenciaCardiaca
     */
    public FrequenciaCardiacaArray getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância saturacaoDeOxigenio
     */
    public SaturacaoDeOxigenioArray getSaturacaoDeOxigenio() {
        return saturacaoDeOxigenio;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância temperatura
     */
    public TemperaturaArray getTemperatura() {
        return temperatura;
    }

    /**
     * Adiciona uma medição de frequência cardíaca
     * @param valor valor da frequência cardíaca
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarFrequenciaCardiaca(int valor, String dataColheita, TecnicoDeSaude tecnico) {
        this.frequenciaCardiaca.adicionarMedicao(valor, dataColheita, tecnico);
    }

    /**
     * Adiciona uma medição de saturação de oxigênio
     * @param valor valor da saturação de oxigênio
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarSaturacaoDeOxigenio(int valor, String dataColheita, TecnicoDeSaude tecnico) {
        this.saturacaoDeOxigenio.adicionarMedicao(valor, dataColheita, tecnico);
    }

    /**
     * Adiciona uma medição de temperatura
     * @param valor valor da temperatura
     * @param dataColheita data da colheita
     * @param tecnico técnico responsável
     */
    public void adicionarTemperatura(double valor, String dataColheita, TecnicoDeSaude tecnico) {
        this.temperatura.adicionarMedicao(valor, dataColheita, tecnico);
    }

    /**
     * Este método aceita um parâmetro do tipo SinalVital, que engloba a frequencia cardíaca,
     * a saturação de oxigénio e a temperatura.
     * De seguida, através do instanceof, o método verifica a que classe é que a instância pertence,
     * para assim chamar o método adicionar presente na classe da medição pretendida.
     * @param sinalVital é no fim convertido para o tipo específico de medida pretendida.
     */
    public void adicionarMedicao(SinalVital sinalVital) {
        if (sinalVital instanceof FrequenciaCardiaca) {
            this.frequenciaCardiaca.adicionar(sinalVital);
        } else if (sinalVital instanceof Temperatura) {
            this.temperatura.adicionar(sinalVital);
        } else if (sinalVital instanceof SaturacaoDeOxigenio) {
            this.saturacaoDeOxigenio.adicionar(sinalVital);
        } else if (sinalVital instanceof FrequenciaCardiacaArray) {
            this.frequenciaCardiaca.adicionar(sinalVital);
        } else if (sinalVital instanceof TemperaturaArray) {
            this.temperatura.adicionar(sinalVital);
        } else if (sinalVital instanceof SaturacaoDeOxigenioArray) {
            this.saturacaoDeOxigenio.adicionar(sinalVital);
        }
    }

    /**
     * Este método tem a função de classificar o estado geral de um paciente com base nas classificações
     * de três sinais vitais: temperatura, frequência cardíaca e saturação de oxigênio.
     * @return Se pelo menos um dos sinais for crítico, atenção ou normal, o método retorna "Critico",
     * "Atenção" ou "Normal", respetivamente
     */
    public String classificarPaciente() {
        if (temperatura.classificacao().equals("Critico") || 
            frequenciaCardiaca.classificacao().equals("Critico") || 
            saturacaoDeOxigenio.classificacao().equals("Critico")) {
            return "Critico";
        } else if (temperatura.classificacao().equals("Atenção") || 
                  frequenciaCardiaca.classificacao().equals("Atenção") || 
                  saturacaoDeOxigenio.classificacao().equals("Atenção")) {
            return "Atenção";
        } else {
            return "Normal";
        }
    }
}