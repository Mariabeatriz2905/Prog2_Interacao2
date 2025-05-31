import org.example.model.FrequenciaCardiacaArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.model.Data;
import java.util.ArrayList;

public class EstatisticasTest {

    private FrequenciaCardiacaArray fcArray = new FrequenciaCardiacaArray();

    @Test
    void testCalcularMaximo_ComMedicoes() {
        Data dataInicial = new Data("2025/5/1");
        Data dataFinal = new Data("2025/5/31");

        ArrayList<Integer> medicao = new ArrayList<>();
        medicao.add(85);
        medicao.add(90);
        medicao.add(120);

        fcArray.setMedicao(medicao);

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

        ArrayList<Integer> medicao = new ArrayList<>();
        medicao.add(85);
        medicao.add(90);
        medicao.add(120);

        fcArray.setMedicao(medicao);

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

            ArrayList<Integer> medicao = new ArrayList<>();
            medicao.add(85);
            medicao.add(90);
            medicao.add(120);

            fcArray.setMedicao(medicao);

            assertEquals(98.33, fcArray.calcularMedia(dataInicial, dataFinal), 0.01);
        }

        @Test
        void testCalcularMedia_SemMedicoes() {
            ArrayList<Integer> medicao = new ArrayList<>();
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            assertEquals(0.0, fcArray.calcularMedia(dataInicial, dataFinal), 0.01);
        }

        @Test
        void testCalcularDesvioPadrao_ComMedicoes() {
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            ArrayList<Integer> medicao = new ArrayList<>();
            medicao.add(85);
            medicao.add(90);
            medicao.add(120);

            fcArray.setMedicao(medicao);

            assertEquals(15.275, fcArray.calcularDesvioPadrao(dataInicial, dataFinal), 0.001);
        }

        @Test
        void testCalcularDesvioPadrao_SemMedicoes() {
            ArrayList<Integer> medicao = new ArrayList<>();
            Data dataInicial = new Data("2025/5/1");
            Data dataFinal = new Data("2025/5/31");

            assertEquals(0.0, fcArray.calcularDesvioPadrao(dataInicial, dataFinal), 0.001);
        }
}


