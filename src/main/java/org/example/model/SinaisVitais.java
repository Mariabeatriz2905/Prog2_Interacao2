package org.example.model;

/**
 * Interface para representar um conjunto de sinais vitais
 * Esta interface estende SinalVital e adiciona métodos específicos para arrays de medições
 */
public interface SinaisVitais extends SinalVital {
    /**
     * Calcula o valor máximo das medições dentro de um intervalo de datas
     * @param dataInicial data inicial do intervalo
     * @param dataFinal data final do intervalo
     * @return o valor máximo encontrado
     */
    public double calcularMaximo(Data dataInicial, Data dataFinal);

    /**
     * Calcula o valor mínimo das medições dentro de um intervalo de datas
     * @param dataInicial data inicial do intervalo
     * @param dataFinal data final do intervalo
     * @return o valor mínimo encontrado
     */
    public double calcularMinimo(Data dataInicial, Data dataFinal);

    /**
     * Calcula a média das medições dentro de um intervalo de datas
     * @param dataInicial data inicial do intervalo
     * @param dataFinal data final do intervalo
     * @return a média dos valores
     */
    public double calcularMedia(Data dataInicial, Data dataFinal);

    /**
     * Calcula o desvio padrão das medições dentro de um intervalo de datas
     * @param dataInicial data inicial do intervalo
     * @param dataFinal data final do intervalo
     * @return o desvio padrão dos valores
     */
    public double calcularDesvioPadrao(Data dataInicial, Data dataFinal);

    /**
     * Adiciona um sinal vital ao conjunto
     * @param sinaisVitais o sinal vital a ser adicionado
     */
    public void adicionar(SinalVital sinaisVitais);
}
