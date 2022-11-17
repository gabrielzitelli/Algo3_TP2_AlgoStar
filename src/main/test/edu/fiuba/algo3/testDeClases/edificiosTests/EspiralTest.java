package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EspiralTest {

    @Test
    public void test01UnEspiralNoEstaConstruidoEn9Turnos() {
        Espiral unEspiral = new Espiral();

        for (int i = 0; i < 9; i++)
            unEspiral.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unEspiral.crearFabricaMutalisco());
    }

    @Test
    public void test02UnEspiralEstaConstruidoEn10Turnos() {
        Espiral unEspiral = new Espiral();

        for (int i = 0; i < 10; i++)
            unEspiral.pasarTurno();

        assertDoesNotThrow(() -> unEspiral.crearFabricaMutalisco());
    }
}
