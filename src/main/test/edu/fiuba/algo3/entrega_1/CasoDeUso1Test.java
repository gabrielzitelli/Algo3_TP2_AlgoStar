package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso1Test {

    NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());

    @Test
    public void test01CreoUnCriaderoYPuedoEgendrar3ZanganosPeroNoCuatroEnUnTurno(){
        boolean excepcionLanzada = false;
        Criadero criadero = new Criadero(nodo, new Recurso());

        //Tengo 3 larvas inicialmente
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otra larva
        try {
            criadero.criarZangano();
        } catch(CriaderoSinLarvas unCriaderoSinLarvas) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }
    @Test
    public void test02DespuesDeGastarTodasLasLarvasDeUnCriaderoEsperoUnTurnoYPuedoCriarOtroMas() {
        boolean excepcionLanzada = false;
        Criadero criadero = new Criadero(nodo, new Recurso());

        //Tengo 3 larvas inicialmente
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //pasa un turno
        criadero.accionDeTurno();

        //puedo obtener un zangano nuevamente
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otro Zangano
        try {
            criadero.criarZangano();
        } catch(CriaderoSinLarvas unCriaderoSinLarvas) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void test03DespuesDeGastar3LarvasDeUnCriaderoDeboEsperar3TurnosParaPoderGastar3Devuelta() {
        boolean excepcionLanzada = false;
        Criadero criadero = new Criadero(nodo, new Recurso());

        //Tengo 3 larvas inicialmente
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //pasan 3 turnos
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //puedo obtener 3 zanganos nuevamente
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otro Zangano
        try {
            criadero.criarZangano();
        } catch(CriaderoSinLarvas unCriaderoSinLarvas) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }

    @Test
    public void test04CreoUnCriaderoYPuedoEgendrar1ZanganoYElTurnoSiguientePuedoEngendrar3(){
        boolean excepcionLanzada = false;
        Criadero criadero = new Criadero(nodo, new Recurso());

        //Tengo 3 larvas inicialmente y crio 1 zangano
        criadero.criarZangano();

        //Pasa un turno y ahora puedo egendrar 3 Zanganos
        criadero.accionDeTurno();
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción cuando intento criar otra larva
        try {
            criadero.criarZangano();
        } catch(CriaderoSinLarvas unCriaderoSinLarvas) {
            excepcionLanzada = true;
        }
        assertTrue(excepcionLanzada);
    }
}
