package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;

import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso1Test {

    NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
    Imperio zergs = new Zergs(new Recurso(), new Recurso());

    @Test
    public void test01CreoUnCriaderoYPuedoEgendrar3ZanganosPeroNoCuatroEnUnTurno(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //Tengo 3 larvas inicialmente, gasto las 3
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otra larva
        assertThrows(CriaderoSinLarvas.class, () -> criadero.criarZangano());
    }

    @Test
    public void test02DespuesDeGastarTodasLasLarvasDeUnCriaderoEsperoUnTurnoYPuedoCriarOtroMas() {
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //Tengo 3 larvas inicialmente, gasto las 3
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Pasa un turno, se regenera una larva
        criadero.accionDeTurno();

        //puedo obtener un zangano nuevamente
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otro Zangano
        assertThrows(CriaderoSinLarvas.class, () -> criadero.criarZangano());
    }

    @Test
    public void test03DespuesDeGastar3LarvasDeUnCriaderoDeboEsperar3TurnosParaPoderGastar3Devuelta() {
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //Tengo 3 larvas inicialmente, gasto las 3
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Pasan 3 turnos
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //Puedo obtener 3 zanganos nuevamente, me gasto nuevamente 3 larvas
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otro Zangano
        assertThrows(CriaderoSinLarvas.class, () -> criadero.criarZangano());
    }

    @Test
    public void test04CreoUnCriaderoYPuedoEgendrar1ZanganoYElTurnoSiguientePuedoEngendrar3(){
        Criadero criadero = new Criadero(nodo, new Recurso(50), zergs);
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //Tengo 3 larvas inicialmente y crio 1 zangano
        criadero.criarZangano();

        //Pasa un turno y ahora puedo crear 3 Zanganos
        criadero.accionDeTurno();

        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otro zangano
        assertThrows(CriaderoSinLarvas.class, () -> criadero.criarZangano());
    }
}

