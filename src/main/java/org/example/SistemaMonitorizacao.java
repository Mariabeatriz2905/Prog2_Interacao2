package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class SistemaMonitorizacao {
    private ArrayList<Pessoa> pessoas;

    /**
     * Construtor da classe que será utilizado para inicializar os objetos quando a classe SistemaMonitorizacao for instanciada.
     * É também criada uma nova instância de um ArrayList vazio que será usado para armazenar objetos do tipo Pessoa.
     */
    public SistemaMonitorizacao() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    /**
     * Método que devolve uma lista do tipo ArrayList contendo objetos do tipo Paciente.
     * É criada uma nova lista vazia chamada pacientes para armazenar objetos do tipo Paciente.
     * O ciclo for percorre os objetos presentes no arrayList pessoas e o if verifica se o objeto p na iteração é uma instância da classe Paciente. Se for, este objeto é convertido e adicionado à nova lista pacientes.
     * Além disso, é utilizado o método sort da classe ArrayList para ordenar os elementos da lista pacientes. São comparados dois objetos baseando-se na data de nascimento, mais especificamente de dois valores inteiros das datas (Integer.compare(...))
     * @return é devolvida uma lista que contém objetos do tipo Paciente, ordenados por data de nascimento
     */
    public ArrayList<Paciente> getPacientes (){
        ArrayList<Paciente> pacientes= new ArrayList<>();
        for(Pessoa p: pessoas){
            if(p instanceof Paciente){
                pacientes.add((Paciente) p);
            }
        }
        pacientes.sort(new Comparator<Paciente>() {
            public int compare(Paciente p1, Paciente p2) {
                return Integer.compare(p1.getDataNascimento().getValue(), p2.getDataNascimento().getValue());
            }
        });
        return pacientes;
    }


    /**
     * Método de acesso, é utilizado para filtrar e organizar uma lista de objetos do tipo TecnicoDeSaude
     * No ciclo for, é percorrida a lista pessoas e verifica-se se o objeto p é uma instância da classe TecnicoDeSaude. Se sim, o objeto é convertido para o tipo TecnicoDeSaude e adicionado à lista tecnicos.
     * O método sort ordena os objetos presentes na lista por ordem alfabética.
     * @return devolve os tecnicos encontrados
     */
    public ArrayList<TecnicoDeSaude> getTecnicosDeSaude (){
        ArrayList<TecnicoDeSaude> tecnicos= new ArrayList<>();
        for(Pessoa p: pessoas){
            if(p instanceof TecnicoDeSaude){
                tecnicos.add((TecnicoDeSaude) p);
            }
        }
        // Ordena alfabeticamente pelo nome
        tecnicos.sort(new Comparator<TecnicoDeSaude>() {
            public int compare(TecnicoDeSaude t1, TecnicoDeSaude t2) {
                return t1.getNome().compareTo(t2.getNome());
            }
        });
        return tecnicos;
    }

    /**
     * Este método permite a adição de objetos do tipo pessoa ao arrayList pessoas.
     * @param p objeto passado por parâmetroque vai ser adicionado ao arrayList
     */
    public void adicionarPessoas (Pessoa p){
        pessoas.add(p);
    }

    /**
     *O método percorre o arrayList pessoas e procura um objeto do tipo Pessoa que tenha o nome especificado pelo parâmetro nome e seja do tipo Paciente e tenha o cargo "paciente", ou seja do tipo TecnicoDeSaude e tenha o cargo "tecnico".
     * @param nome da pessoa que pretendemos encontrar no arrayList
     * @param cargo que corresponde ao nome
     * @return devolve o objeto encontrado ou indica que não encontrou nenhum objeto
     */
    public Pessoa encontrarPessoa(String nome, String cargo) {
        for (Pessoa p: pessoas) {
            if (p.getNome().equals(nome)){
                if (p instanceof Paciente && cargo=="paciente"){
                    return p;
                }else if (p instanceof TecnicoDeSaude && cargo=="tecnico"){
                    return p;
                }
            }
        }
        return null;
    }
}