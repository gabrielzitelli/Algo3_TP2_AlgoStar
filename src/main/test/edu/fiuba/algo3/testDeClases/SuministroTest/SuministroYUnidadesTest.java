package edu.fiuba.algo3.testDeClases.SuministroTest;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zerling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SuministroYUnidadesTest {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }


    @Test
    public void test01ConstruyoUnCriaderoParaTener5DeSuministroYCreo5UnidadesParaTener5DePoblacion() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();


        imperioZerg.abastecerDeRecursos(new Mineral(3000), new Gas(3000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));
        criadero.crearUnidad(new FabricaZerling());

            //Pasan dos turnos y lo tenemos
        imperioZerg.terminarTurno();

        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();

        Coordenada coordenadaZerling = new Coordenada(6,6);

        ArrayList<Unidad> listaZergUnidades = imperioZerg.dameLaListaUnidades();

        elMapa.colocarUnaUnidad((listaZergUnidades.get(0)), coordenadaZerling);

        Unidad unDragon = new Dragon();
        Coordenada coordenadaDragon = new Coordenada(5,5);
        elMapa.colocarUnaUnidad(unDragon, coordenadaDragon);

        for (int i = 0; i < 2; i++) {
            elMapa.atacar(coordenadaDragon, coordenadaZerling);
        }

        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tenesEsteSuministro(5));

        assertTrue(imperioZerg.tenesEstaPoblacion(0));
    }
}
