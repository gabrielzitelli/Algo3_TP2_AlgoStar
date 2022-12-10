package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaAmoSupremo;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso31Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01ConstruyoUnCriaderoYLuegoLoDestruyoYLaPoblacionVuelveASerCero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        Coordenada coordenadaDragon = new Coordenada(1,0);

        imperioZerg.abastecerDeRecursos(new Mineral(375), new Gas(0));
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //Ahora tengo el criadero construido, tengo 5 de poblacion y quiero atacarlo hasta destruirlo
        //Creo un Dragon para atacar al criadero
        Unidad unDragon = new Dragon();
        elMapa.colocarOcupable(unDragon, coordenadaDragon);

        //Destruyo el criadero
        for (int i = 0; i < 25; i++){
            elMapa.atacar(coordenadaDragon, coordenadaCriadero);
            unDragon.pasarTurno();
        }


        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tenesEsteSuministro(0));
    }

    @Test
    public void test02ConstruyoUnPilonYLuegoLoDestruyoYLaPoblacionVuelveASerCero() {
        Mapa elMapa = Mapa.obtener();
        Protoss imperioProtoss = new Protoss();
        Coordenada coordenadaPilon = new Coordenada(0, 0);
        Coordenada coordenadaHidralisco = new Coordenada(1, 0);

        imperioProtoss.abastecerDeRecursos(new Mineral(375), new Gas(50));
        imperioProtoss.construirPilon(coordenadaPilon);

        //Esperamos a que se construya el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        //Ahora tengo el pilon construido, tengo 5 de poblacion y quiero atacarlo hasta destruirlo
        //Creo un Dragon para atacar al pilon
        Unidad unHidralisco = new Hidralisco();
        elMapa.colocarOcupable(unHidralisco, coordenadaHidralisco);

        //Destruyo el pilon
        for (int i = 0; i < 60; i++){
            elMapa.atacar(coordenadaHidralisco, coordenadaPilon);
            unHidralisco.pasarTurno();
        }

        imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tenesEsteSuministro(0));
    }

    @Test
    public void test03ConstruyoUnAmoSupremoYLuegoLoDestruyoYLaPoblacionVuelveASerCero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(3000), new Gas(3000));
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));
        criadero.crearUnidad(new FabricaAmoSupremo());

        //Pasan 5 turnos y lo tenemos
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaDragon = new Coordenada(1,2);

        ArrayList<Unidad> listaZergUnidades = imperioZerg.dameLaListaUnidades();
        Unidad unAmoSupremo = listaZergUnidades.get(0);
        Coordenada coordenadaAmoSupremo = unAmoSupremo.obtenerCoordenada();

        Unidad unDragon = new Dragon();
        elMapa.colocarOcupable(unDragon, coordenadaDragon);

        //Mato al Amo Supremo
        for (int i = 0; i < 10; i++){
            elMapa.atacar(coordenadaDragon, coordenadaAmoSupremo);
            unDragon.pasarTurno();
        }


        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tenesEsteSuministro(5)); //5 del criadero + 0 del AmoSupremo porque esta muerto
    }
}
