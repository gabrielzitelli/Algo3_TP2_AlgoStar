package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CasoDeUso2Test {

    Tablero tablero = new Tablero(1, 1);
    NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
    Zergs zergs = new Zergs(tablero, new Recurso(), new Recurso());

    @Test
    public void Test1ConstruyoUnCriaderoEIntentoUsarloSinQuePasenTurnosParaConstruirse() {
        Criadero criadero = new Criadero(tablero, nodo, new Recurso(50), zergs);

        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());
    }

    @Test
    public void Test2ConstruyoUnCriaderoEIntentoUsarloLuegoDeUnTurno() {
        Criadero criadero = new Criadero(tablero, nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }

    @Test
    public void Test3ConstruyoUnCriaderoEIntentoUsarloLuegoDeDosTurnos() {
        Criadero criadero = new Criadero(tablero, nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }


    @Test
    public void Test4ConstruyoUnCriaderoEIntentoUsarloLuegoDeTresTurnos() {
        Criadero criadero = new Criadero(tablero, nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        assertThrows(EdificioEnConstruccion.class, () -> criadero.criarZangano());

    }

    @Test
    public void Test5ConstruyoUnCriaderoEIntentoUsarloLuegoDeCuatroTurnosCuandoYaSeConstruyo() {
        Criadero criadero = new Criadero(tablero, nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        assertDoesNotThrow(() -> criadero.criarZangano());

    }
}
