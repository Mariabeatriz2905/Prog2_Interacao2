package org.example;

public interface SinaisVitais {
    public String ultimaMedicao();

    public double calcularMaximo(Data dataInicial, Data dataFinal);

    public double calcularMinimo(Data dataInicial, Data dataFinal);

    public double calcularMedia(Data dataInicial, Data dataFinal);

    public double calcularDesvioPadrao(Data dataInicial, Data dataFinal);

    public String classificacao();

    public void adicionar(SinaisVitais sinaisVitais);

    public double getScore();
}
