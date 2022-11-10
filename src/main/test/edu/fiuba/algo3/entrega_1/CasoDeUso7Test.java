package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeRecursoInsuficiente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CasoDeUso7Test {

    @Test
    public void UnNexoMineralExtraeMineralPor5TurnosYAlcanzaParaConstruirOtroNexoMineral() {
        Tablero tablero = new Tablero(2, 2);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(1, 1));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(1,1));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio nexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();

        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();

        assertDoesNotThrow(() -> protoss.construirNexoMineral(new Coordenadas(1, 1)));
    }

    @Test
    public void UnNexoMineralExtraMineralPor4TurnosYNoAlcanzaParaConstruirOtroNexoMineral() {
        Tablero tablero = new Tablero(2, 2);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(1, 1));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(1,1));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio nexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();

        assertThrows(CantidadDeRecursoInsuficiente.class ,() -> protoss.construirNexoMineral(new Coordenadas(1, 1)));
    }
}
