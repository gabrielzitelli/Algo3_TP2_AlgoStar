package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZerling;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaDeReproduccionTest {

    @Test
    public void test01UnaReservaDeReproduccionNoSeConstruyeEn11Turnos(){
        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();

        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        unaReserva.asignarListaDeUnidades(unidadesDisponibles);

        for(int i = 0; i < 11; i++)
            unaReserva.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaZerling()));
    }

    @Test
    public void test02UnaReservaDeReproduccionSePuedeConstruirEn12Turnos(){
        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();

        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        unaReserva.asignarListaDeUnidades(unidadesDisponibles);

        for (int i = 0; i < 12; i++)
            unaReserva.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaZerling()));
    }
}
