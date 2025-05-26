import org.example.model.Temperatura;
import org.example.model.TemperaturaArray;
import org.example.service.EstatisticasCalculador;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class EstatisticasCalculadorTest {

    @Test
    public void testMediaTemperaturaArray() {
        Temperatura t1 = new Temperatura(36.2, null);
        Temperatura t2 = new Temperatura(37.8, null);
        TemperaturaArray array = new TemperaturaArray(Arrays.asList(t1, t2));
        double resultado = EstatisticasCalculador.mediaTemperaturas(array);
        assertEquals(37.0, resultado, 0.01);
    }
}