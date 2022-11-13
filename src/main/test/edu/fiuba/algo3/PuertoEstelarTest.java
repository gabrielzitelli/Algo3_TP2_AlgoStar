package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PuertoEstelarTest {
    @Test
    public void test01UnPuertoEstelarNoSeConstruyeEn9Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();

        for(int i = 0; i < 9; i++)
            puertoEstelar.pasarTurno();


        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> puertoEstelar.crearFabricaScout());
    }
    @Test
    public void test02UnPuertoEstelarSePuedeConstruirEn10Turnos(){
        PuertoEstelar puertoEstelar = new PuertoEstelar();

        for(int i = 0; i < 10; i++)
            puertoEstelar.pasarTurno();

        assertDoesNotThrow(() -> puertoEstelar.crearFabricaScout());
    }
}
