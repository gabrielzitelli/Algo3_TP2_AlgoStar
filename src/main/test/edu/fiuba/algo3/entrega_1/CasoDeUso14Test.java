package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        Acceso acceso = new Acceso(requisitoAcceso, new Coordenadas(0,0));

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> {
            tablero.construir(acceso, new Coordenadas(0, 0));
        });
    }

    @Test
    public void MohoSeExpandePorAreaEnergizadaPorPilonYSePuedeConstruirEdificioZerg() {
        Tablero tablero = new Tablero(10,10);
        Zergs zergs = new Zergs(tablero, new Recurso(), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());

        Coordenadas coordenadasPilon = new Coordenadas(5, 0);
        Pilon pilon = new Pilon(tablero, nodoCompatible, coordenadasPilon);
        tablero.construir(pilon, coordenadasPilon);

        // Pilon energiza los nodos en un radio de 3
        pilon.accionDeTurno();

        Coordenadas coordenadasCriadero = new Coordenadas(0, 0);
        Criadero criadero = new Criadero(tablero, nodoCompatible, coordenadasCriadero, zergs);
        tablero.construir(criadero, coordenadasCriadero);

        // Tiempos de construccion
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new SinRecurso());
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(nodoCompatibleReserva);

        assertDoesNotThrow(() -> tablero.construir(reserva, new Coordenadas(4, 0)));
    }

    @Test
    public void ExpandirMohoSobreNodoOcupadoNoDejaAlNodoConMoho() {
        Tablero tablero = new Tablero(5,5);
        Zergs zergs = new Zergs(tablero, new Recurso(), new Recurso());

        NodoCompatible nodoCompatible = new NodoCompatible(new Neutro(), new SinRecurso());

        Coordenadas coordenadasPilon = new Coordenadas(0, 0);
        Pilon pilon = new Pilon(tablero, nodoCompatible, coordenadasPilon);
        tablero.construir(pilon, coordenadasPilon);
        pilon.accionDeTurno();

        Coordenadas coordenadasCriadero = new Coordenadas(1, 1);
        Criadero criadero = new Criadero(tablero, nodoCompatible, coordenadasCriadero, zergs);
        tablero.construir(criadero, coordenadasCriadero);

        // Tiempos de construccion
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        pilon.destruir();

        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new SinRecurso());
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(nodoCompatibleReserva);

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> {
            tablero.construir(reserva, coordenadasPilon);
        });
    }
}
