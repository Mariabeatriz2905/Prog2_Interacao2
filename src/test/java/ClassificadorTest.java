
import org.example.model.*;

import org.example.utils.Classificador;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ClassificadorTest {

    @Test
    public void testScorePaciente() {
        Paciente paciente = new Paciente("João", "1990/01/01", 180, 75);
        paciente.adicionarTemperatura(36.5, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));
        paciente.adicionarFrequenciaCardiaca(80, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));
        paciente.adicionarSaturacaoDeOxigenio(96, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));

        double score = Classificador.ScorePaciente(paciente);

        assertEquals(1.1, score, 0.0001);
    }

    @Test
    public void testClassificarGravidade() {
        Paciente pacienteBaixo = new Paciente("João", "1990/01/01", 180, 75);
        pacienteBaixo.adicionarTemperatura(36.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteBaixo.adicionarFrequenciaCardiaca(80, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteBaixo.adicionarSaturacaoDeOxigenio(96, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        Paciente pacienteModerado = new Paciente("Carlos", "1975/03/20", 175, 70);
        pacienteModerado.adicionarTemperatura(38.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteModerado.adicionarFrequenciaCardiaca(120, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteModerado.adicionarSaturacaoDeOxigenio(91, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        Paciente pacienteAlto = new Paciente("Beatriz", "1965/12/15", 160, 55);
        pacienteAlto.adicionarTemperatura(38.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteAlto.adicionarFrequenciaCardiaca(140, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteAlto.adicionarSaturacaoDeOxigenio(86, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        assertEquals("Gravidade Baixa", Classificador.classificarGravidade(pacienteBaixo));
        assertEquals("Gravidade Moderada", Classificador.classificarGravidade(pacienteModerado));
        assertEquals("Gravidade Alta", Classificador.classificarGravidade(pacienteAlto));
    }

    @Test
    public void testPacienteEmMaiorRisco() {
        Paciente pacienteBaixo = new Paciente("João", "1990/01/01", 180, 75);
        pacienteBaixo.adicionarTemperatura(36.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteBaixo.adicionarFrequenciaCardiaca(80, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteBaixo.adicionarSaturacaoDeOxigenio(96, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        Paciente pacienteModerado = new Paciente("Carlos", "1975/03/20", 175, 70);
        pacienteModerado.adicionarTemperatura(38.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteModerado.adicionarFrequenciaCardiaca(120, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteModerado.adicionarSaturacaoDeOxigenio(91, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        Paciente pacienteAlto = new Paciente("Beatriz", "1965/12/15", 160, 55);
        pacienteAlto.adicionarTemperatura(38.5, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteAlto.adicionarFrequenciaCardiaca(140, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));
        pacienteAlto.adicionarSaturacaoDeOxigenio(86, "2025/05/26", new TecnicoDeSaude("Ana", "1979/07/09", "Médico"));

        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(pacienteBaixo);
        pacientes.add(pacienteModerado);
        pacientes.add(pacienteAlto);

        Paciente maiorRisco = Classificador.pacienteEmMaiorRisco(pacientes);

        assertNotNull(maiorRisco);
        assertEquals(pacienteAlto, maiorRisco);
    }

    @Test
    public void testPacienteEmMaiorRiscoListaVazia() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Paciente maiorRisco = Classificador.pacienteEmMaiorRisco(pacientes);
        assertNull(maiorRisco);
    }
}
