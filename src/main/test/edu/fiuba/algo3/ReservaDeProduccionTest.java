package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Edificio_Zerg.Criadero;
import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Edificio_Zerg.ReservaDeProduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaDeProduccionTest {

    @Test
    public void test01UnaReservaDeProduccionNoSeConstruyeEn11Turnos(){
        ReservaDeProduccion unaReserva = new ReservaDeProduccion();

        for(int i = 0; i < 11; i++)
            unaReserva.pasarTurno();


        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaReserva.crearFabricaZerling());
    }

    @Test
    public void test02UnaReservaDeProduccionSePuedeConstruirEn12Turnos(){
        ReservaDeProduccion unaReserva = new ReservaDeProduccion();

        for(int i = 0; i < 12; i++)
            unaReserva.pasarTurno();

        assertDoesNotThrow(() -> unaReserva.crearFabricaZerling());
    }
}
