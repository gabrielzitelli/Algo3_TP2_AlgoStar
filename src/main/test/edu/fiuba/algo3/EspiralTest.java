package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioZerg.Espiral;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EspiralTest {

    @Test
    public void test01UnEspiralNoEstaConstruidoEn9Turnos() {
        Espiral unEspiral = new Espiral();

        int turnosAPasar = 9;

        for (int i = 0; i < turnosAPasar; i++) {
            unEspiral.pasarTurno();
        }

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unEspiral.crearFabricaMutalisco());
    }

    @Test
    public void test02UnEspiralEstaConstruidoEn10Turnos() {
        Espiral unEspiral = new Espiral();

        int turnosAPasar = 10;

        for (int i = 0; i < turnosAPasar; i++) {
            unEspiral.pasarTurno();
        }

        assertDoesNotThrow(() -> unEspiral.crearFabricaMutalisco());
    }
}
