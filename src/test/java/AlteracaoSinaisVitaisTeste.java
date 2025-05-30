
import static org.junit.jupiter.api.Assertions.*;

import org.example.service.MenuGerenciador;
import org.example.service.SistemaMonitorizacao;
import org.example.model.Paciente;
import org.example.model.TecnicoDeSaude;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlteracaoSinaisVitaisTeste {

    private MenuGerenciador gerenciador;
    private SistemaMonitorizacao sistema;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        sistema = new SistemaMonitorizacao();
        gerenciador = new MenuGerenciador(sistema);

        // Criar paciente com sinais vitais
        paciente = new Paciente("João", "1990/01/01", 175, 70);
        paciente.adicionarTemperatura(36.5, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));
        paciente.adicionarFrequenciaCardiaca(80, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));
        paciente.adicionarSaturacaoDeOxigenio(98, "2025/05/26", new TecnicoDeSaude("Carlos", "1979/03/03", "Médico"));

        // Adicionar paciente ao sistema
        sistema.adicionarPessoa(paciente);
    }

    @Test
    void testAlteracaoComEntradaValida() {
        String inputSimulado = "25"; // Simula entrada válida
        ByteArrayInputStream in = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(in);

        gerenciador.alterarSinaisVitais(Double.parseDouble(inputSimulado)); // Executa o método

        // Verifica se os valores foram alterados corretamente
        assertEquals(36.5 + 36.5 * 1.25, paciente.getTemperatura().getMedicao().getLast(), 0.01);
        assertEquals(80 + 80 * 1.25, paciente.getFrequenciaCardiaca().getMedicao().getLast(), 0.01);
        assertEquals(98 + 98 * 1.25, paciente.getSaturacaoDeOxigenio().getMedicao().getLast(), 0.01);
    }

    @Test
    void testAlteracaoComEntradaInvalidaSeguidoDeValida() {
        String inputSimulado = "-1"; // Primeiro inválido, depois válido
        ByteArrayInputStream in = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out)); // Captura saída do console

        gerenciador.alterarSinaisVitais(-1);

        String output = out.toString();
        assertTrue(output.contains("A percentagem que introduziu não é valida!(introduza um número)"));

    }
}

