package org.example.model;

/**
 * Interface para representar um sinal vital individual
 * Esta interface contém apenas os métodos necessários para um sinal vital individual,
 * sem os métodos específicos para arrays de medições
 */
public interface SinalVital {
    /**
     * Retorna a última medição do sinal vital
     * @return String representando a última medição
     */
    public String ultimaMedicao();
    
    /**
     * Retorna a classificação do sinal vital
     * @return String representando a classificação (Normal, Atenção, Crítico, etc.)
     */
    public String classificacao();
    
    /**
     * Retorna o score de gravidade do sinal vital
     * @return double representando o score de gravidade
     */
    public double getScore();
}