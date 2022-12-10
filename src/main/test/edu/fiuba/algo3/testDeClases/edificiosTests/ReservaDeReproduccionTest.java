package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaDeReproduccionTest {

    @Test
    public void test01UnaReservaDeReproduccionNoSeConstruyeEn11Turnos(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        unaReserva.asignarListaDeUnidades(fabricasDisponibles);

        for(int i = 0; i < 11; i++)
            unaReserva.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(1));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaZerling()));
    }

    @Test
    public void test02UnaReservaDeReproduccionSePuedeConstruirEn12Turnos(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        unaReserva.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 12; i++)
            unaReserva.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(1000));
        unCriadero.asignarSuministro(new Suministro(1));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaZerling()));
    }

    @Test
    public void test03SiSeDestruyeUnaReservaDeReproduccionNoSePuedeCrearLaUnidadQueHabilita() {
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(1));
        elMapa.colocarOcupable(unCriadero, new Coordenada(0,0));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Construyo reserva de reproduccion
        ReservaDeReproduccion unaReserva = new ReservaDeReproduccion();
        elMapa.colocarOcupable(unaReserva, new Coordenada(1,0));
        unaReserva.asignarListaDeUnidades(fabricasDisponibles);
        for (int i = 0; i < 12; i++)
            unaReserva.pasarTurno();

        unaReserva.recibirAtaque(new Ataque(new DanioTerrestre(1000)));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaZerling()));
    }
}
