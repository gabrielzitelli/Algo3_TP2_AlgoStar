package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaDeReproduccionTest {

    @Test
    public void test01UnaReservaDeReproduccionNoSeConstruyeEn11Turnos(){
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();

        for(int i = 0; i < 11; i++)
            unaReserva.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaReserva.crearFabricaZerling());
    }

    @Test
    public void test02UnaReservaDeReproduccionSePuedeConstruirEn12Turnos(){
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();

        //Construyo la Reserva de Reproduccion
        for(int i = 0; i < 12; i++)
            unaReserva.pasarTurno();

        assertDoesNotThrow(() -> unaReserva.crearFabricaZerling());
    }
}
