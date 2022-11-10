package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso6Test {

    @Test
    public void MohoAumentaEn1DespuesDe2TurnosDelCriaderoActivo() {
        Tablero tablero = new Tablero(10, 10);
        Zergs zergs = new Zergs(tablero, new Recurso(50), new Recurso());
        Zangano zangano = new Zangano(tablero, new Coordenadas(0,0), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatible, new Recurso(), zergs);
        tablero.construir(criadero, zangano.getPosicion());

        Zangano zanganoParaCriadero = new Zangano(tablero, new Coordenadas(5,0), new Recurso());

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        assertDoesNotThrow(() -> zergs.construirCriadero(zanganoParaCriadero));
    }
}
