package org.example.service;

import org.example.model.Paciente;
import org.example.model.Pessoa;
import org.example.model.TecnicoDeSaude;

import java.util.ArrayList;
import java.util.Comparator;

public class SistemaMonitorizacao {
    private ArrayList<Paciente> pacientes;
    private ArrayList<TecnicoDeSaude> tecnicos;

    /**
     * Construtor da classe que será utilizado para inicializar os objetos quando a classe SistemaMonitorizacao for instanciada.
     * É também criada uma nova instância de um ArrayList vazio que será usado para armazenar objetos do tipo Pessoa.
     */
    public SistemaMonitorizacao() {
        this.pacientes = new ArrayList<Paciente>();
        this.tecnicos = new ArrayList<TecnicoDeSaude>();
    }
    public SistemaMonitorizacao(ArrayList<Paciente> pacientes, ArrayList<TecnicoDeSaude> tecnicos) {
        this.pacientes = pacientes;
        this.tecnicos = tecnicos;
    }

    /**
     * Método que devolve uma lista do tipo ArrayList contendo objetos do tipo Paciente.
     * É criada uma nova lista vazia chamada pacientes para armazenar objetos do tipo Paciente.
     * O ciclo for percorre os objetos presentes no arrayList pessoas e o if verifica se o objeto p na iteração é uma instância da classe Paciente. Se for, este objeto é convertido e adicionado à nova lista pacientes.
     * Além disso, é utilizado o método sort da classe ArrayList para ordenar os elementos da lista pacientes. São comparados dois objetos baseando-se na data de nascimento, mais especificamente de dois valores inteiros das datas (Integer.compare(...))
     * @return é devolvida uma lista que contém objetos do tipo Paciente, ordenados por data de nascimento
     */
    public ArrayList<Paciente> getPacientes (){
        this.pacientes.sort(new Comparator<Paciente>() {
            public int compare(Paciente p1, Paciente p2) {
                return Integer.compare(p1.getDataNascimento().getValue(), p2.getDataNascimento().getValue());
            }
        });
        return pacientes;
    }

    public void adicionarPessoa(Pessoa p) {
        if (p instanceof Paciente) {
            this.pacientes.add((Paciente) p);
        }else if (p instanceof TecnicoDeSaude) {
            this.tecnicos.add((TecnicoDeSaude) p);
        }
    }

    /**
     * Método de acesso, é utilizado para filtrar e organizar uma lista de objetos do tipo TecnicoDeSaude
     * No ciclo for, é percorrida a lista pessoas e verifica-se se o objeto p é uma instância da classe TecnicoDeSaude. Se sim, o objeto é convertido para o tipo TecnicoDeSaude e adicionado à lista tecnicos.
     * O método sort ordena os objetos presentes na lista por ordem alfabética.
     * @return devolve os tecnicos encontrados
     */
    public ArrayList<TecnicoDeSaude> getTecnicosDeSaude (){
        // Ordena alfabeticamente pelo nome
        this.tecnicos.sort(new Comparator<TecnicoDeSaude>() {
            public int compare(TecnicoDeSaude t1, TecnicoDeSaude t2) {
                return t1.getNome().compareTo(t2.getNome());
            }
        });
        return tecnicos;
    }

    /**
     *O método percorre o arrayList pacientes e procura um objeto do tipo Paciente que tenha o nome especificado pelo parâmetro nome e seja do tipo Paciente
     * @param nome da pessoa que pretendemos encontrar no arrayList
     * @return devolve o objeto encontrado ou indica que não encontrou nenhum objeto
     */
    public Paciente encontrarPaciente(String nome) {
        for (Paciente p: this.pacientes) {
            if (p.getNome().equals(nome)){
                return p;
            }

        }
        return null;
    }

    /**
     *O método percorre o arrayList tecnicos e procura um objeto do tipo TecnicoDeSaude que tenha o nome especificado pelo parâmetro nome
     * @param nome da pessoa que pretendemos encontrar no arrayList
     * @return devolve o objeto encontrado ou indica que não encontrou nenhum objeto
     */
    public TecnicoDeSaude encontrarTecnico(String nome) {
        for (TecnicoDeSaude t: this.tecnicos) {
            if (t.getNome().equals(nome)){
                return t;
            }

        }
        return null;
    }
    /**
    * O método percorre a arrayList de pacientes e adiciona-lhes uma nova medida com uma mudança percentual em cada um dos seus sinais vitais
     * @param percentagem variação da percentagem
     */
    public void alterarSinaisVitais(double percentagem){
        for(Paciente paciente : this.pacientes){
            if (paciente.getTemperatura() != null && paciente.getTemperatura().getMedicao() != null) {
                ArrayList<Double> medicoesTemperatura = paciente.getTemperatura().getMedicao();
                if (!medicoesTemperatura.isEmpty()) {
                    double ultimaTemperatura = medicoesTemperatura.getLast();
                    medicoesTemperatura.add(alterarPercentualmente(percentagem, ultimaTemperatura));
                }
            }

            if (paciente.getFrequenciaCardiaca() != null && paciente.getFrequenciaCardiaca().getMedicao() != null) {
                ArrayList<Integer> medicoesFreqCardiaca = paciente.getFrequenciaCardiaca().getMedicao();
                if (!medicoesFreqCardiaca.isEmpty()) {
                    int ultimaFreqCardiaca = medicoesFreqCardiaca.getLast();
                    medicoesFreqCardiaca.add((int) alterarPercentualmente(percentagem, ultimaFreqCardiaca));
                }
            }
            if (paciente.getSaturacaoDeOxigenio() != null && paciente.getSaturacaoDeOxigenio().getMedicao() != null) {
                ArrayList<Integer> medicoesSatOxigenio = paciente.getSaturacaoDeOxigenio().getMedicao();
                if (!medicoesSatOxigenio.isEmpty()) {
                    int ultimaSatOxigenio = medicoesSatOxigenio.getLast();
                    medicoesSatOxigenio.add((int) alterarPercentualmente(percentagem, ultimaSatOxigenio));
                }
            }
        }
    }

    /**
     * Este método faz a conta necessária para causar a variação a partir da ultima medição
     * @param percentagem percentagem pedida para a variação
     * @param medicao a última medição
     * @return nova medição com a variação percentual efetuada
     */
    private double alterarPercentualmente(double percentagem, double medicao){
        return (medicao * (1 + percentagem/ 100));
    }

    /**
     * Este método percorre a arrayList pacientes e verifica a quantidade percentual de pacientes em situação crítica
     * @return Percentagem de pacientes  em situação crítica
     */
    public float pacientesEmSituacaoCritica(){
        float pacientesCriticos = 0;
        float nPacientes = 0;
        for(Paciente paciente : this.pacientes){
            nPacientes ++;
            String estado = paciente.classificarPaciente();
            if(estado.equals( "Critico")){
                pacientesCriticos ++;
            }
        }
        return pacientesCriticos / nPacientes * 100;
    }

}
