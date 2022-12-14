package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
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
        elMapa.colocarOcupable(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () -> imperioZerg.construirEdificio(new FabricaCriadero(), coordenada));
    }

    @Test
    public void test02TengoLosRecursosNecesariosPuedoConstruirUnCriadero() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenada);

        assertDoesNotThrow(() -> imperioZerg.construirEdificio(new FabricaCriadero(),coordenada));
    }

    @Test
    public void test03SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnaReserva() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(),coordenada));
    }

    @Test
    public void test04TengoLosRecursosNecesariosPuedoConstruirUnaReserva() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), new Coordenada(0,0)));
    }

    @Test
    public void test05SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnExtractor() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class ,
                () ->imperioZerg.construirEdificio(new FabricaExtractor(),coordenada));
    }

    @Test
    public void test06TengoLosRecursosNecesariosPuedoConstruirUnExtractor() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirEdificio(new FabricaExtractor(), new Coordenada(0,0)));
    }

    @Test
    public void test07NOTengoLosRecursosNecesariosNOPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(350), new Gas(0));

        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida));
    }

    @Test
    public void test08TengoLosRecursosNecesariosPuedoConstruirUnaGuarida(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        assertDoesNotThrow(() ->imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida));
    }

    @Test
    public void test09NOTengoLosRecursosNecesariosNOPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(550), new Gas(100));

        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        Coordenada coordenadaEspiral = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral));
    }

    @Test
    public void test10TengoLosRecursosNecesariosPuedoConstruirUnEspiral(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos();

        Coordenada coordenadaCriadero = new Coordenada(0,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva =new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida =new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        Coordenada coordenadaEspiral =new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);
        assertDoesNotThrow(() ->imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral));
    }

    @Test
    public void test11NoTengoLosRecursosNecesariosNoPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaNexoMineral(), coordenada));
    }

    @Test
    public void test12TengoLosRecursosNecesariosPuedoConstruirUnNexoMineral() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaNexoMineral(), coordenada));
    }

    @Test
    public void test13NoTengoLosRecursosNecesariosNoPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaAsimilador(), coordenada));
    }

    @Test
    public void test14TengoLosRecursosNecesariosPuedoConstruirUnAsimilador() {
        Mapa elMapa = Mapa.obtener();
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();
        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaAsimilador(), coordenada));
    }

    @Test
    public void test15NoTengoLosRecursosNecesariosNoPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaPilon(), coordenada));
    }

    @Test
    public void test16TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos();

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaPilon(), coordenada));
    }

    @Test
    public void test17NoTengoLosRecursosNecesariosNoPuedoConstruirUnAcceso() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(100), new Gas(0));
        protoss.construirEdificio(new FabricaPilon(), coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,1)));
    }

    @Test
    public void test18TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirEdificio(new FabricaPilon(), coordenada);
        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaAcceso(), new Coordenada(0,1)));
    }

    @Test
    public void test19NoTengoLosRecursosNecesariosNoPuedoConstruirUnPuertoEstelar() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(0));
        protoss.construirEdificio(new FabricaPilon(), coordenada);

        for(int i = 0; i < 5; i++)
            protoss.terminarTurno();

        protoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirEdificio(new FabricaPuertoEstelar(), new Coordenada(1,1)));
    }

    @Test
    public void test20TengoLosRecursosNecesariosPuedoConstruirUnPuertoEstelar() {
        Protoss protoss = new Protoss();
        Coordenada coordenada = new Coordenada(0,0);

        protoss.abastecerDeRecursos(new Mineral(400), new Gas(150));
        protoss.construirEdificio(new FabricaPilon(), coordenada);

        for(int i = 0; i < 6; i++)
            protoss.terminarTurno();

        protoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,0));

        assertDoesNotThrow(() -> protoss.construirEdificio(new FabricaPuertoEstelar(), new Coordenada(0,1)));
    }
}
