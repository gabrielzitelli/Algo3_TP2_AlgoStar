package edu.fiuba.algo3.entrega_3;


import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaAmoSupremo;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso26Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01ConstruyoUnCriaderoParaTenerSuministroYCreoUnidades() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(375), new Gas(0));
        imperioZerg.construirCriadero(coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaZerling()));

        //No pasan los turnos, aun no tengo al Zerlign
        assertFalse(imperioZerg.tieneUnidad(new Zerling()));

        //Pasan dos turnos y lo tenemos
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Zerling()));

        assertTrue(imperioZerg.tenesEsteSuministro(5));

        assertTrue(imperioZerg.tenesEstaPoblacion(1));
    }

    @Test
    public void test02ConstruyoUnPilonParaTenerSuministroYCreoUnidades() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos(new Mineral(375), new Gas(50));
        imperioProtoss.construirPilon(new Coordenada(0,0));

        //Esperamos a que se construya el pilon
        for(int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirAcceso(new Coordenada(1,0));

        //esperamos a que se construya el acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        //obtenemos el edificio
        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricaDragon()));

        //No pasan los turnos, aun no tengo al Dragon
        assertFalse(imperioProtoss.tieneUnidad(new Dragon()));

        //Pasan dos turnos y lo tenemos
        for (int i = 0; i < 6; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Dragon()));

        assertTrue(imperioProtoss.tenesEsteSuministro(5));

        assertTrue(imperioProtoss.tenesEstaPoblacion(3));
    }

    @Test
    public void test03ConstruyoUnAmoSupremoParaTenerSuministro() {
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);

        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(375), new Gas(0));
        imperioZerg.construirCriadero(coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(coordenadaCriadero);

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaAmoSupremo()));

        //No pasan los turnos, aun no tengo al amo supremo
        assertFalse(imperioZerg.tieneUnidad(new AmoSupremo()));

        //Pasan 5 turnos y lo tenemos
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new AmoSupremo()));

        assertTrue(imperioZerg.tenesEsteSuministro(10)); //5 del criadero + 5 del amo supremo

        assertTrue(imperioZerg.tenesEstaPoblacion(0));
    }
}
