import org.example.model.FrequenciaCardiacaArray;
import org.example.model.TecnicoDeSaude;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.model.Data;
import java.util.ArrayList;

public class EstatisticasTest {

    private FrequenciaCardiacaArray fcArray = new FrequenciaCardiacaArray();

    @Test
    void testCalcularMaximo_ComMedicoes() {
        Data dataInicial = new Data("2025/05/01");
        Data dataFinal = new Data("2025/05/31");

        fcArray.adicionarMedicao(85, "2025/5/10", new TecnicoDeSaude("José", "1980/07/05", "Enfermeiro"));
        fcArray.adicionarMedicao(90, "2025/5/15", new TecnicoDeSaude("Bruno", "2000/03/29", "Médico"));
        fcArray.adicionarMedicao(120, "2025/5/20", new TecnicoDeSaude("Ana", "1985/02/17", "Enfermeira"));

        assertEquals(120, fcArray.calcularMaximo(dataInicial, dataFinal));
    }

    @Test
    void testCalcularMaximo_SemMedicoes() {
        Data dataInicial = new Data("2025/5/1");
        Data dataFinal = new Data("2025/5/31");

        assertEquals(0, fcArray.calcularMaximo(dataInicial, dataFinal));
    }

    @Test
    void testCalcularMinimo_ComMedicoes() {
        Data dataInicial = new Data("2025/5/1");
        Data dataFinal = new Data("2025/5/31");

        fcArray.adicionarMedicao(85, "2025/5/10", new TecnicoDeSaude("José", "1980/07/05", "Enfermeiro"));
        fcArray.adicionarMedicao(90, "2025/5/15", new TecnicoDeSaude("Bruno", "2000/03/29", "Médico"));
        fcArray.adicionarMedicao(120, "2025/5/20", new TecnicoDeSaude("Ana", "1985/02/17", "Enfermeira"));

        assertEquals(85, fcArray.calcularMinimo(dataInicial, dataFinal));
    }

    @Test
    void testCalcularMinimo_SemMedicoes() {
        Data dataInicial = new Data("2025/5/1");
        Data dataFinal = new Data("2025/5/31");

        assertEquals(0, fcArray.calcularMinimo(dataInicial, dataFinal));
    }

    @Test
        void testCalcularMedia_ComMedicoes() {
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            fcArray.adicionarMedicao(85, "2025/5/10", new TecnicoDeSaude("José", "1980/07/05", "Enfermeiro"));
            fcArray.adicionarMedicao(90, "2025/5/15", new TecnicoDeSaude("Bruno", "2000/03/29", "Médico"));
            fcArray.adicionarMedicao(120, "2025/5/20", new TecnicoDeSaude("Ana", "1985/02/17", "Enfermeira"));

            assertEquals(98.33, fcArray.calcularMedia(dataInicial, dataFinal), 0.01);
        }

        @Test
        void testCalcularMedia_SemMedicoes() {
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            assertEquals(0.0, fcArray.calcularMedia(dataInicial, dataFinal), 0.01);
        }

        @Test
        void testCalcularDesvioPadrao_ComMedicoes() {
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            fcArray.adicionarMedicao(85, "2025/5/10", new TecnicoDeSaude("José", "1980/07/05", "Enfermeiro"));
            fcArray.adicionarMedicao(90, "2025/5/15", new TecnicoDeSaude("Bruno", "2000/03/29", "Médico"));
            fcArray.adicionarMedicao(120, "2025/5/20", new TecnicoDeSaude("Ana", "1985/02/17", "Enfermeira"));

            assertEquals(15.456, fcArray.calcularDesvioPadrao(dataInicial, dataFinal), 0.001);
        }

        @Test
        void testCalcularDesvioPadrao_SemMedicoes() {
            ArrayList<Integer> medicao = new ArrayList<>();
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            assertEquals(0.0, fcArray.calcularDesvioPadrao(dataInicial, dataFinal), 0.001);
        }
}


