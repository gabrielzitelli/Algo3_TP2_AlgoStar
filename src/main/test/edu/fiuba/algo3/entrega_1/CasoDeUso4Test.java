package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.Nodo;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeRecursoInsuficiente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso4Test {

    Tablero tablero = new Tablero(1, 1);

    @Test
    public void ExtractorSinZanganosTrabajandoNoGeneraGas() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        // Extrae un turno
        extractor.accionDeTurno();

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> gasJuntado.consumir(1));
    }

    @Test
    public void ExtractorConUnZanganoTrabajandoUnTurnoLanzaExcepcionSiSePiden11DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador);

        // Extrae un turno
        extractor.accionDeTurno();

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> gasJuntado.consumir(11));
    }

    @Test
    public void ExtractorConUnZanganoTrabajandoUnTurnoNoLanzaExcepcionSiSePiden9DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador);

        // Extrae un turno
        extractor.accionDeTurno();

        assertDoesNotThrow(() -> gasJuntado.consumir(9));
    }

    @Test
    public void ExtractorConDosZanganosTrabajandoUnTurnoLanzaExcepcionSiSePiden21DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador1 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador2 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador1);
        extractor.contratarZangano(trabajador2);

        // Extrae un turno
        extractor.accionDeTurno();

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> gasJuntado.consumir(21));
    }

    @Test
    public void ExtractorConDosZanganosTrabajandoUnTurnoNoLanzaExcepcionSiSePiden19DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador1 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador2 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador1);
        extractor.contratarZangano(trabajador2);

        // Extrae un turno
        extractor.accionDeTurno();

        assertDoesNotThrow(() -> gasJuntado.consumir(19));
    }

    @Test
    public void ExtractorConTresZanganosTrabajandoUnTurnoLanzaExcepcionSiSePiden31DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador1 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador2 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador3 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador1);
        extractor.contratarZangano(trabajador2);
        extractor.contratarZangano(trabajador3);

        // Extrae un turno
        extractor.accionDeTurno();

        assertThrows(CantidadDeRecursoInsuficiente.class, () -> gasJuntado.consumir(31));
    }

    @Test
    public void ExtractorConTresZanganosTrabajandoUnTurnoNoLanzaExcepcionSiSePiden29DeGas() {
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());
        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Recurso gasJuntado = new Recurso();
        Extractor extractor = new Extractor(nodoCompatible, gasJuntado);
        nodo.construir(extractor);

        // Tiempo de construccion
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        Zangano trabajador1 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador2 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        Zangano trabajador3 = new Zangano(tablero, new Coordenadas(0, 0), new Recurso());
        extractor.contratarZangano(trabajador1);
        extractor.contratarZangano(trabajador2);
        extractor.contratarZangano(trabajador3);

        // Extrae un turno
        extractor.accionDeTurno();

        assertDoesNotThrow(() -> gasJuntado.consumir(29));
    }
}
