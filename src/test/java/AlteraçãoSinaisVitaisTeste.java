
import static org.junit.jupiter.api.Assertions.*;

import org.example.service.MenuGerenciador;
import org.example.service.SistemaMonitorizacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlteraçãoSinaisVitaisTeste {

    MenuGerenciador gerenciador;

    @Test
    void testAlteracaoSinaisVitaisComEntradaValida() {
        String input = "75\n"; // Simula um input válido
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Simula entrada do Scanner

        // Executa método público que chama `alteracaoSinaisVitais()`
        gerenciador.alterarSinaisVitais(75);
    }

    @Test
    void testAlteracaoSinaisVitaisComEntradaInvalida() {
        String input = "abc\n75\n"; // Primeiro input inválido, depois válido
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out)); // Captura saída do console

        gerenciador.alterarSinaisVitais(75);

        String output = out.toString();
        assertTrue(output.contains("A percentagem que introduziu não é valida!(introduza um número)"));
    }
}
