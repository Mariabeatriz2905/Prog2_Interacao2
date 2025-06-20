import static org.junit.jupiter.api.Assertions.*;
import org.example.model.TecnicoDeSaude;
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
        List<Paciente> pacientes = new ArrayList<>();

        Paciente paciente1 = new Paciente("Joana", "2000/02/08", 156, 57);
        Paciente paciente2 = new Paciente("Pedro", "1989/07/25", 178, 80);
        TecnicoDeSaude tecnico1 = new TecnicoDeSaude("Jorge", "1976/06/24", "Médico");
        TecnicoDeSaude tecnico2 = new TecnicoDeSaude("Teresa", "1967/05/20", "Enfermeira");

        pacientes.add(paciente1);
        pacientes.add(paciente2);

        sistema.adicionarPessoa(paciente1);
        sistema.adicionarPessoa(paciente2);
        sistema.adicionarPessoa(tecnico1);
        sistema.adicionarPessoa(tecnico2);

        gerenciador.adicionarFrequenciaCardiaca(paciente1, 130, "2024/03/09", tecnico1);
        gerenciador.adicionarFrequenciaCardiaca(paciente2, 60, "2024/03/09", tecnico2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        float percentagemCriticos=gerenciador.pacientesEmSituacaoCritica();

        float expectedPercentage = (float) (100) / 2;

        assertEquals(expectedPercentage, percentagemCriticos);
    }
}

