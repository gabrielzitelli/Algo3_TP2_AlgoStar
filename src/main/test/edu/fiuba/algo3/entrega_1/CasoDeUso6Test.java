package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso6Test {

    @Test
    public void MohoAumentaEn1DespuesDe2TurnosDelCriaderoEstandoActivo() {
        Tablero tablero = new Tablero(10, 10);
        Zergs zergs = new Zergs(tablero, new Recurso(150), new Recurso());
        Zangano zangano = new Zangano(tablero, new Coordenadas(0,0), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatible, new Coordenadas(0,0), zergs);
        tablero.construir(criadero, zangano.getPosicion());

        Zangano zanganoParaReserva = new Zangano(tablero, new Coordenadas(5,0), new Recurso());

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        assertDoesNotThrow(() -> zergs.construirReservaDeReproduccion(zanganoParaReserva));
    }

    @Test
    public void MohoNoAumentaEn1DespuesDe1TurnoDelCriaderoEstandoActivo() {
        Tablero tablero = new Tablero(10, 10);
        Zergs zergs = new Zergs(tablero, new Recurso(150), new Recurso());
        Zangano zangano = new Zangano(tablero, new Coordenadas(0,0), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatible, new Coordenadas(0,0), zergs);
        tablero.construir(criadero, zangano.getPosicion());

        Zangano zanganoParaReserva = new Zangano(tablero, new Coordenadas(5,0), new Recurso());

        criadero.accionDeTurno();

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> {
            zergs.construirReservaDeReproduccion(zanganoParaReserva);
        });
    }

    @Test
    public void IntentarConstruirEnRango2SiMohoSoloAumentoEn1LanzaExcepcion() {
        Tablero tablero = new Tablero(10, 10);
        Zergs zergs = new Zergs(tablero, new Recurso(150), new Recurso());
        Zangano zangano = new Zangano(tablero, new Coordenadas(0,0), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatible, new Coordenadas(0,0), zergs);
        tablero.construir(criadero, zangano.getPosicion());

        Zangano zanganoParaReserva = new Zangano(tablero, new Coordenadas(6,0), new Recurso());

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> {
            zergs.construirReservaDeReproduccion(zanganoParaReserva);
        });
    }
}
