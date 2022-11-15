package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso8Test {
    @Test
    public void test01SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnCriadero() {
        Zerg imperioZerg = new Zerg();
        //imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class , () ->imperioZerg.construirCriadero(new Coordenada(0,0)));
    }
    @Test
    public void test02TengoLosRecursosNecesariosPuedoConstruirUnCriadero() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertDoesNotThrow(() -> imperioZerg.construirCriadero(new Coordenada(0,0)));
    }
    @Test
    public void test03SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnaReserva() {
        Zerg imperioZerg = new Zerg();
        //imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class , () ->imperioZerg.construirReservaDeReproduccion(new Coordenada(0,0)));
    }
    @Test
    public void test04TengoLosRecursosNecesariosPuedoConstruirUnaReserva() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirReservaDeReproduccion(new Coordenada(0,0)));
    }
    @Test
    public void test05SiNoTengoLosRecursosNecesariosNoPuedoConstruirUnExtractor() {
        Zerg imperioZerg = new Zerg();
        //imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class , () ->imperioZerg.construirExtractor(new Coordenada(0,0)));
    }
    @Test
    public void test06TengoLosRecursosNecesariosPuedoConstruirUnExtractor() {
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class,
                () -> imperioZerg.construirExtractor(new Coordenada(0,0)));
    }
    @Test
    public void test07NOTengoLosRecursosNecesariosNOPuedoConstruirUnaGuarida(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Recurso(200), new Recurso(0));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        imperioZerg.construirCriadero(new Coordenada(0,0));
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirGuarida(new Coordenada(1,1)));
    }
    @Test
    public void test08TengoLosRecursosNecesariosPuedoConstruirUnaGuarida(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        imperioZerg.construirCriadero(new Coordenada(0,0));
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));

        assertDoesNotThrow(() ->imperioZerg.construirGuarida(new Coordenada(1,1)));
    }
    @Test
    public void test09NOTengoLosRecursosNecesariosNOPuedoConstruirUnEspiral(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Recurso(400), new Recurso(100));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        imperioZerg.construirCriadero(new Coordenada(0,0));
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));
        imperioZerg.construirGuarida(new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.construirEspiral(new Coordenada(1,1)));
    }
    @Test
    public void test10TengoLosRecursosNecesariosPuedoConstruirUnEspiral(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        imperioZerg.construirCriadero(new Coordenada(0,0));
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.construirReservaDeReproduccion(new Coordenada(0,1));
        imperioZerg.construirGuarida(new Coordenada(1,0));

        assertDoesNotThrow(() ->imperioZerg.construirEspiral(new Coordenada(1,1)));
    }
    @Test
    public void test11NoTengoLosRecursosNecesariosNoPuedoConstruirUnNexoMineral() {
        Protoss protoss = new Protoss();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirNexoMineral(coordenada));
    }
    @Test
    public void test12TengoLosRecursosNecesariosPuedoConstruirUnNexoMineral() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirNexoMineral(coordenada));

    }
    @Test
    public void test13NoTengoLosRecursosNecesariosNoPuedoConstruirUnAsimilador() {
        Protoss protoss = new Protoss();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarMaterial(new MineralRecolectable(), coordenada);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirAsimilador(coordenada));
    }
    @Test
    public void test14TengoLosRecursosNecesariosPuedoConstruirUnAsimilador() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        elMapa.colocarMaterial(new GasRecolectable(), coordenada);

        assertDoesNotThrow(() -> protoss.construirAsimilador(coordenada));
    }
    @Test
    public void test15NoTengoLosRecursosNecesariosNoPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPilon(coordenada));
    }
    @Test
    public void test16TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos();
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);

        assertDoesNotThrow(() -> protoss.construirPilon(coordenada));
    }
    @Test
    public void test17NoTengoLosRecursosNecesariosNoPuedoConstruirUnAcceso() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(100), new Recurso(0));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirAcceso(new Coordenada(1,1)));
    }
    @Test
    public void test18TengoLosRecursosNecesariosPuedoConstruirUnPilon() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(250), new Recurso(0));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 6; i++){
            protoss.terminarTurno();
        }

        assertDoesNotThrow(() -> protoss.construirAcceso(new Coordenada(0,1)));
    }
    @Test
    public void test19NoTengoLosRecursosNecesariosNoPuedoConstruirUnPuertoEstelar() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(250), new Recurso(0));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }
        protoss.construirAcceso(new Coordenada(1,0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> protoss.construirPuertoEstelar(new Coordenada(1,1)));
    }
    @Test
    public void test20TengoLosRecursosNecesariosPuedoConstruirUnPuertoEstelar() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(400), new Recurso(150));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        Coordenada coordenada = new Coordenada(0,0);
        protoss.construirPilon(coordenada);
        for(int i = 0; i < 6; i++){
            protoss.terminarTurno();
        }
        protoss.construirAcceso(new Coordenada(1,0));

        assertDoesNotThrow(() -> protoss.construirPuertoEstelar(new Coordenada(0,1)));
    }
}
