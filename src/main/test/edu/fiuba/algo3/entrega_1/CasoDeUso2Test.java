package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso2Test {
    NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
    Imperio zergs = new Zergs(new Recurso(), new Recurso());

    @Test
    public void Test1ConstruyoUnCriaderoEIntentoUsarloSinQuePasenTurnosParaConstruirse(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);

        RuntimeException thrown = assertThrows(
                RuntimeException.class, () -> criadero.criarZangano() , "Expected RuntimeException to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Construccion no finalizada"));

    }
    @Test
    public void Test2ConstruyoUnCriaderoEIntentoUsarloLuegoDeUnTurno(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        RuntimeException thrown = assertThrows(
                RuntimeException.class, () -> criadero.criarZangano() , "Expected RuntimeException to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Construccion no finalizada"));

    }

    @Test
    public void Test3ConstruyoUnCriaderoEIntentoUsarloLuegoDeDosTurnos(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        RuntimeException thrown = assertThrows(
                RuntimeException.class, () -> criadero.criarZangano() , "Expected RuntimeException to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Construccion no finalizada"));

    }


    @Test
    public void Test4ConstruyoUnCriaderoEIntentoUsarloLuegoDeTresTurnos(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        RuntimeException thrown = assertThrows(
                RuntimeException.class, () -> criadero.criarZangano() , "Expected RuntimeException to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Construccion no finalizada"));

    }

    /*
    @Test
    public void Test5ConstruyoUnCriaderoEIntentoUsarloLuegoDeCuatroTurnosCuandoYaSeConstruyo(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);

        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        RuntimeException thrown = assertThrows(
                RuntimeException.class, () -> criadero.criarZangano() , "Expected RuntimeException to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Expected RuntimeException to throw, but it didn't"));

    }

    */



}
