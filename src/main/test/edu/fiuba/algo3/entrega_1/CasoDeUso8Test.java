package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.*;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso8Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnCriadero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirCriadero(coordenada));
    }

    @Test
    public void test02TengoLosRecursosNecesariosPuedoConstruirUnCriadero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenada);

        assertDoesNotThrow(() -> imperioZerg.construirCriadero(coordenada));
    }

    @Test
    public void test03SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnaReserva() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirReservaDeReproduccion(coordenada));
    }

    @Test
    public void test04TengoLosRecursosNecesariosPuedoConstruirUnaReserva() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirReservaDeReproduccion(new Coordenada(0,0)));
    }

    @Test
    public void test05SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnExtractor() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirExtractor(coordenada));
    }

    @Test
    public void test06TengoLosRecursosNecesariosPuedoConstruirUnExtractor() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirExtractor(new Coordenada(0,0)));
    }

    @Test
    public void test07NOTengoLosRecursosNecesariosNOPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(350), new Gas(0));
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        imperioZerg.construirCriadero(coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();


        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);
        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirGuarida(new Coordenada(1,1)));
    }

    @Test
    public void test08TengoLosRecursosNecesariosPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        imperioZerg.construirCriadero(coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);
        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaGuarida);
        assertDoesNotThrow(() ->imperioZerg.construirGuarida(coordenadaGuarida));
    }

    @Test
    public void test09NOTengoLosRecursosNecesariosNOPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(550), new Gas(100));
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        imperioZerg.construirCriadero(coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);
        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaGuarida);
        imperioZerg.construirGuarida(coordenadaGuarida);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirEspiral(new Coordenada(1,1)));
    }

    @Test
    public void test10TengoLosRecursosNecesariosPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaCriadero);
        imperioZerg.construirCriadero(coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaReserva);
        imperioZerg.construirReservaDeReproduccion(coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,0);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaGuarida);
        imperioZerg.construirGuarida(coordenadaGuarida);

        Coordenada coordenadaEspiral =new Coordenada(1,1);
        elMapa.colocarUnaUnidad(new Zangano(), coordenadaEspiral);
        assertDoesNotThrow(() ->imperioZerg.construirEspiral(coordenadaEspiral));
    }

    @Test
    public void test11NoTengoLosRecursosNecesariosNoPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirNexoMineral(coordenada));
    }

    @Test
    public void test12TengoLosRecursosNecesariosPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirNexoMineral(coordenada));
    }

    @Test
    public void test13NoTengoLosRecursosNecesariosNoPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirAsimilador(coordenada));
    }

    @Test
    public void test14TengoLosRecursosNecesariosPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirAsimilador(coordenada));
    }

    @Test
    public void test15NoTengoLosRecursosNecesariosNoPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPilon(coordenada));
    }

    @Test
    public void test16TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();

        assertDoesNotThrow(() -> protoss.construirPilon(coordenada));
    }

    @Test
    public void test17NoTengoLosRecursosNecesariosNoPuedoConstruirUnAcceso() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirAcceso(new Coordenada(1,1)));
    }

    @Test
    public void test18TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        assertDoesNotThrow(() -> protoss.construirAcceso(new Coordenada(0,1)));
    }

    @Test
    public void test19NoTengoLosRecursosNecesariosNoPuedoConstruirUnPuertoEstelar() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        protoss.construirAcceso(new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPuertoEstelar(new Coordenada(1,1)));
    }

    @Test
    public void test20TengoLosRecursosNecesariosPuedoConstruirUnPuertoEstelar() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(400), new Gas(150));
        protoss.construirPilon(coordenada);

        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        protoss.construirAcceso(new Coordenada(1,0));

        assertDoesNotThrow(() -> protoss.construirPuertoEstelar(new Coordenada(0,1)));
    }
}
