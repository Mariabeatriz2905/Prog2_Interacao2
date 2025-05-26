import org.example.model.*;
import org.example.service.SistemaMonitorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaMonitorizacaoTest {

    @Test
    public void testAdicionarPaciente() {
        SistemaMonitorizacao sistema = new SistemaMonitorizacao();
        Paciente paciente = new Paciente("João Silva", "1989/03/23", 163, 64);
        sistema.adicionarPessoa(paciente);
        assertTrue(sistema.getPacientes().contains(paciente));
    }

    @Test
    public void testAdicionarTecnico() {
        SistemaMonitorizacao sistema = new SistemaMonitorizacao();
        TecnicoDeSaude tecnico = new TecnicoDeSaude("José", "1978/06/12", "Enfermeiro");
        sistema.adicionarPessoa(tecnico);
        assertTrue(sistema.getTecnicosDeSaude().contains(tecnico));
    }
}

