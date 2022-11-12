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

        unaReserva.pasarTurno();
        unaReserva.pasarTurno();
        unaReserva.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unaReserva.crearFabricaZerling());
    }

    /*@Test
    public void test03UnCriaderoSeConstruyeEn4Turnos(){
        Criadero unCriadero = new Criadero();

        unCriadero.pasarTurno();
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaZangano()));
    }*/
}
