import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ScorePacienteTest {

    @Test
    void testScorePaciente() {
        // Criando mocks para os sinais vitais do paciente
        SinalVital frequenciaCardiaca = new SinalVital(80, 2.0); // Exemplo de score
        SinalVital temperatura = new SinalVital(37.5, 3.0);
        SinalVital saturacaoDeOxigenio = new SinalVital(98, 1.5);

        // Criando um paciente com os sinais vitais simulados
        Paciente paciente = new Paciente(frequenciaCardiaca, temperatura, saturacaoDeOxigenio);

        // Calculando o score esperado
        double expectedScore = (0.3 * 2.0) + (0.4 * 3.0) + (0.4 * 1.5);

        // Testando o método
        assertEquals(expectedScore, ScorePaciente(paciente), 0.01);
    }
}
