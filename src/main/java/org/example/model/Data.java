package org.example.model;

public class Data {
    private int value;

    /**
     * Construtor da classe Data
     * @param value
     */
    public Data(int value) {
        this.value = value;
    }

    /**
     * Este método recebe por parâmetro o objeto data.
     * Atráves do método split, a string fornecida é dividida em partes, separadas pelo caractere /. Cada parte é então armazenada numa posição específica do array.
     * Além disso, o método Integer.parseInt converte cada parte de string para inteiro. Por fim, o valor de value correponde à soma do ano*10000 + mês*100+dia. O value vai ser necessário para posteriormente se fazerem comparações de datas.
     * @param data objeto data do tipo string no formato "aaaa/mm/dd"
     */
    public Data(String data) {
        String[] partes = data.split("/");
        this.value = Integer.parseInt(partes[0]) * 10000 + Integer.parseInt(partes[1]) * 100 + Integer.parseInt(partes[2]);
    }

    /**
     * Método de acesso
     * @return devolve o conteúdo da variável de instância value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Este método converte o valor do atributo value para uma string. De seguida, o método substring seleciona os quatro primeiros caracteres da string e guarda-os numa variável year.
     * O mesmo acontece para os 4 ao 6 caracteres e para 6 ao 8.
     * @return devolve uma string formada pela concatenação dos valores do ano, mês e dia através do separador /.
     */
    public String toString() {
        String temp = String.valueOf(this.value);
        String year = temp.substring(0, 4);
        String month = temp.substring(4, 6);
        String day = temp.substring(6, 8);
        return year + "/" + month + "/" + day;
    }
}

