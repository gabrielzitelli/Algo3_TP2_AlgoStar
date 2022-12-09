package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSuperaMaximoDePoblacionActual;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso30Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01ConstruyoUnCriaderoParaTener5DeSuministroYCreo5UnidadesParaTener5DePoblacion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(3000), new Gas(3000));
        imperioZerg.construirCriadero(coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));
        for (int i = 0; i < 5 ; i++){
            criadero.crearUnidad(new FabricaZerling());

            //Pasan dos turnos y lo tenemos
            imperioZerg.terminarTurno();
            imperioZerg.terminarTurno();
            imperioZerg.terminarTurno();
        }

        assertTrue(imperioZerg.tieneUnidad(new Zerling()));

        assertTrue(imperioZerg.tenesEsteSuministro(5));

        assertTrue(imperioZerg.tenesEstaPoblacion(5));
    }

    @Test
    public void test02ConstruyoUnCriaderoParaTener5DeSuministroYCreo6UnidadesMeDeberiaTirarUnaExcepcion() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(3000), new Gas(3000));
        imperioZerg.construirCriadero(coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));
        for (int i = 0; i < 5 ; i++){
            criadero.crearUnidad(new FabricaZerling());

            //Pasan dos turnos y lo tenemos
            imperioZerg.terminarTurno();
            imperioZerg.terminarTurno();
            imperioZerg.terminarTurno();
        }

        assertTrue(imperioZerg.tenesEsteSuministro(5));

        assertTrue(imperioZerg.tenesEstaPoblacion(5));

        assertThrows(ErrorSuperaMaximoDePoblacionActual.class,
                () -> criadero.crearUnidad(new FabricaZerling()));
    }
}
