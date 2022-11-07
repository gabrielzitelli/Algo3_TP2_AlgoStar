package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso1Test {
    @Test
    public void test01CreoUnCriaderoYPuedoEgendrar3ZanganosPeroNoCuatroEnUnTurno(){
        boolean excepcionLanzada = false;
        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
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
}
