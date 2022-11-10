package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso14Test {

    @Test
    public void IntentarConstruirEdificioProtossEnRangoDePilonPeroSobreMohoLanzaExcepcion() {
        Tablero tablero = new Tablero(3, 3);
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());
        Coordenadas coordenadas = new Coordenadas(1, 0);
        Pilon pilon = new Pilon(tablero, nodoCompatible, coordenadas);
        tablero.construir(pilon, coordenadas);

        pilon.accionDeTurno();

        NodoCompatible requisitoAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso acceso = new Acceso(requisitoAcceso);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> {
            tablero.construir(acceso, new Coordenadas(0, 0));
        });
    }
}
