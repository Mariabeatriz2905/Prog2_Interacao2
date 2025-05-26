
import org.example.model.*;

import org.example.utils.Classificador;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ClassificadorTest {

    @Test
    public void testScorePaciente() {
        Paciente paciente = new Paciente("João", "1990/01/01", 180, 75);
        paciente.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        paciente.setTemperatura(new TemperaturaArray());
        paciente.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        double score = Classificador.ScorePaciente(paciente);

        assertEquals(3.4, score, 0.0001);
    }

    @Test
    public void testClassificarGravidade() {
        Paciente pacienteBaixo = new Paciente("Ana", "1980/05/10", 165, 60);
        pacienteBaixo.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        pacienteBaixo.setTemperatura(new TemperaturaArray());
        pacienteBaixo.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        Paciente pacienteModerado = new Paciente("Carlos", "1975/03/20", 175, 70);
        pacienteModerado.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        pacienteModerado.setTemperatura(new TemperaturaArray());
        pacienteModerado.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        Paciente pacienteAlto = new Paciente("Beatriz", "1965/12/15", 160, 55);
        pacienteAlto.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        pacienteAlto.setTemperatura(new TemperaturaArray());
        pacienteAlto.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        assertEquals("Gravidade Baixa", Classificador.classificarGravidade(pacienteBaixo));
        assertEquals("Gravidade Moderada", Classificador.classificarGravidade(pacienteModerado));
        assertEquals("Gravidade Alta", Classificador.classificarGravidade(pacienteAlto));
    }

    @Test
    public void testPacienteEmMaiorRisco() {
        Paciente p1 = new Paciente("P1", "1995/01/01", 170, 70);
        p1.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        p1.setTemperatura(new TemperaturaArray());
        p1.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        Paciente p2 = new Paciente("P2", "1990/01/01", 175, 75);
        p2.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        p2.setTemperatura(new TemperaturaArray());
        p2.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());

        Paciente p3 = new Paciente("P3", "1985/01/01", 165, 65);
        p3.setFrequenciaCardiaca(new FrequenciaCardiacaArray());
        p3.setTemperatura(new TemperaturaArray());
        p3.setSaturacaoDeOxigenio(new SaturacaoDeOxigenioArray());
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(p1);
        pacientes.add(p2);
        pacientes.add(p3);

        Paciente maiorRisco = Classificador.pacienteEmMaiorRisco(pacientes);

        assertNotNull(maiorRisco);
        assertEquals(p2, maiorRisco);
    }

    @Test
    public void testPacienteEmMaiorRiscoListaVazia() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Paciente maiorRisco = Classificador.pacienteEmMaiorRisco(pacientes);
        assertNull(maiorRisco);
    }
}
