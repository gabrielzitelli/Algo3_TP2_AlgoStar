package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso3Test {

    @Test
    public void test01IntentoConstruirUnEdificioDistintoDeAsimiladorEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Imperio zergs = new Zergs(new Recurso(), new Recurso());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio criadero = new Criadero(nodo, new Recurso(50), zergs);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(criadero, coordenadas));
    }

    @Test
    public void test02IntentoConstruirUnEdificioDistintoDeExtractorEnUnVolcanYLanzaExcepcion() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new SinRecurso());
        // Imperio protoss = new Protoss(new Recurso(), new Recurso());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio pilon = new Pilon(nodo);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> tablero.construir(pilon, coordenadas));
    }

    @Test
    public void test03PuedoConstruirUnAsimiladorEnUnVolcan() {
        NodoCompatible nodo = new NodoCompatible(new Energia(), new VolcanGasVespeno());
        // Imperio protoss = new Protoss(new Recurso(), new Recurso());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Energia(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio asimilador = new Asimilador(nodo, new Recurso());

        assertDoesNotThrow(() -> tablero.construir(asimilador, coordenadas));
    }
    @Test
    public void test04PuedoConstruirUnExtractorEnUnVolcan() {
        NodoCompatible nodo = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Imperio zergs = new Zergs(new Recurso(), new Recurso());

        Tablero tablero = new Tablero(10, 10);
        Coordenadas coordenadas = new Coordenadas(5, 5);

        tablero.establecerTerreno(new Moho(), coordenadas);
        tablero.establecerRecurso(new VolcanGasVespeno(), coordenadas);

        Edificio extractor = new Asimilador(nodo, new Recurso());

        assertDoesNotThrow(() -> tablero.construir(extractor, coordenadas));
    }
}
