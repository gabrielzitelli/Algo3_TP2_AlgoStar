package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso3Test {

    @Test
    public void test01IntentoConstruirUnCriaderoEnUnVolcanYLanzaExcepcion() {
        Tablero tablero = new Tablero(10, 10);
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Zergs zergs = new Zergs(tablero,new Recurso(), new Recurso());

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio criadero = new Criadero(tablero, nodo, new Coordenadas(0,0), zergs);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(criadero, coordenadas));
    }

    @Test
    public void test02IntentoConstruirUnaReservaDeProducccionEnUnVolcanYLanzaExcepcion() {
        Tablero tablero = new Tablero(10, 10);
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio reserva = new ReservaDeReproduccion(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(reserva, coordenadas));
    }

    @Test
    public void test03IntentoConstruirUnaGuaridaEnUnVolcanYLanzaExcepcion() {
        Tablero tablero = new Tablero(10, 10);
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio guarida = new Guarida(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(guarida, coordenadas));
    }

    @Test
    public void test04IntentoConstruirUnEspiralEnUnVolcanYLanzaExcepcion() {
        Tablero tablero = new Tablero(10, 10);
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio espiral = new Espiral(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(espiral, coordenadas));
    }

    @Test
    public void test05IntentoConstruirUnNexoMineralEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        Tablero tablero = new Tablero(10, 10);

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio nexoMineral = new NexoMineral(nodo, new Recurso());

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(nexoMineral, coordenadas));
    }

    @Test
    public void test06IntentoConstruirUnPilonEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        Tablero tablero = new Tablero(10, 10);

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio pilon = new Pilon(tablero, nodo, coordenadas);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(pilon, coordenadas));
    }

    @Test
    public void test07IntentoConstruirUnAccesoEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        Tablero tablero = new Tablero(10, 10);

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio acceso = new Acceso(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(acceso, coordenadas));
    }

    @Test
    public void test08IntentoConstruirUnPuertoEstelarEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        Tablero tablero = new Tablero(10, 10);

        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio puertoEstelar = new PuertoEstelar(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(puertoEstelar, coordenadas));
    }

    @Test
    public void test09PuedoConstruirUnAsimiladorEnUnVolcan() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new VolcanGasVespeno());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio asimilador = new Asimilador(nodo, new Recurso());

        assertDoesNotThrow(() -> tablero.construir(asimilador, coordenadas));
    }
    @Test
    public void test10PuedoConstruirUnExtractorEnUnVolcan() {
        Tablero tablero1 = new Tablero(1, 1);
        NodoCompatible nodo = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Imperio zergs = new Zergs(tablero1, new Recurso(), new Recurso());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio extractor = new Asimilador(nodo, new Recurso());

        assertDoesNotThrow(() -> tablero.construir(extractor, coordenadas));
    }
}
