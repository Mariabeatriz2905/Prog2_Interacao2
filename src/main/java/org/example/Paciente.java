package org.example;

/**
 Classe Paciente que herda de Pessoa e contém sinais vitais
 */

public class Paciente extends Pessoa{
    private int altura ;// altura em cm
    private int  peso;// peso em kg
    private FrequenciaCardiaca frequenciaCardiaca;
    private SaturacaoDeOxigenio saturacaoDeOxigenio;
    private Temperatura temperatura;

    /**
     * Construtor completo
     * @param nome
     * @param dataNascimento
     * @param altura
     * @param peso
     */
    public Paciente(String nome, String dataNascimento, int altura, int peso ) {
        super (nome, dataNascimento);
        this.altura = altura;
        this.peso = peso;
        this.frequenciaCardiaca = new FrequenciaCardiaca();
        this.saturacaoDeOxigenio = new SaturacaoDeOxigenio();
        this.temperatura = new Temperatura();

    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância frequenciaCardiaca
     */
    public FrequenciaCardiaca getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância saturacaoDeOxigenio
     */
    public SaturacaoDeOxigenio getSaturacaoDeOxigenio() {
        return saturacaoDeOxigenio;
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância temperatura
     */
    public Temperatura getTemperatura() {
        return temperatura;
    }

    /**
     * Este método aceita um parâmetro do tipo SinaisVitais, que engloba a frequencia cardíaca,
     a saturação de oxigénio e a temperatura.
     * De seguida, através do instanceof, o método verifica a que classe é que a instância pertence,
     para assim chmar o método adicionar presente na classe da medição pretendida.
     * @param sinaisVitais é no fim convertido para o tipo específico de medida pretendida.
     */
    public void adicionarMedicao(SinaisVitais sinaisVitais)
    {
        if (sinaisVitais instanceof FrequenciaCardiaca) {
            this.frequenciaCardiaca.adicionar((FrequenciaCardiaca) sinaisVitais);
        } else if (sinaisVitais instanceof Temperatura) {
            this.temperatura.adicionar((Temperatura) sinaisVitais);
        } else if (sinaisVitais instanceof SaturacaoDeOxigenio) {
            this.saturacaoDeOxigenio.adicionar((SaturacaoDeOxigenio) sinaisVitais);
        }
    }

    /**
     * Este método tem a função de classificar o estado geral de um paciente com base nas classificações
     de três sinais vitais: temperatura, frequência cardíaca e saturação de oxigênio.
     * @return Se pelo menos um dos sinais for crítico, atenção ou normal, o método retorna "Critico",
    "Atenção" ou "Normal", respetivamente
     */
    public String classificarPaciente() {
        if (temperatura.classificacao().equals("Critico") || frequenciaCardiaca.classificacao().equals("Critico") || saturacaoDeOxigenio.classificacao().equals("Critico")) {
            return "Critico";
        } else  if (temperatura.classificacao().equals("Atenção") || frequenciaCardiaca.classificacao().equals("Atenção") || saturacaoDeOxigenio.classificacao().equals("Atenção")) {
            return "Atenção";
        } else {
            return "Normal";
        }
    }
}