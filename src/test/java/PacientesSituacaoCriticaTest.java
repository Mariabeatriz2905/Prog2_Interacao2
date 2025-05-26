import static org.junit.jupiter.api.Assertions.*;

import org.example.service.MenuGerenciador;
import org.example.service.SistemaMonitorizacao;
import org.example.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PacientesSituacaoCriticaTest {

    private MenuGerenciador gerenciador;
    private SistemaMonitorizacao sistema;

    @BeforeEach
    void setUp() {
        sistema = new SistemaMonitorizacao();
        gerenciador = new MenuGerenciador(sistema);
    }

    @Test
    void testPacientesEmSituacaoCritica() {
        // Criando lista de pacientes
        List<Paciente> pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente("Joana", "2000/02/08", 156, 57);
        Paciente paciente2 = new Paciente("Pedro", "1989/07/25", 178, 80);


        pacientes.add(paciente1);
        pacientes.add(paciente2);

        // Simulando os pacientes dentro do sistema
        sistema.setPacientes(pacientes);

        // Capturando a saída do console
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Executa o método
        gerenciador.pacientesEmSituacaoCritica();

        // Obtém a saída do console
        String output = out.toString().trim();

        // Calcula a porcentagem esperada
        int expectedPercentage = (2 * 100) / 4; // 2 pacientes críticos de um total de 4

        // Verifica se a saída contém a informação correta
        assertTrue(output.contains("Pacientes em Situação Crítica(%): " + expectedPercentage + "%"));
    }
}

