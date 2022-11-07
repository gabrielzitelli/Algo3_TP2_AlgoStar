package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso1 {
    @Test
    public void test01CreoUnCriaderoYPuedoEgendrar3ZanganosPeroNoCuatroEnUnTurno(){

        NodoCompatible nodo = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodo, new Recurso());

        //Tengo 3 larvas inicialmente
        criadero.criarZangano();
        criadero.criarZangano();
        criadero.criarZangano();

        //Lanza una excepción
        assertThrows(CriaderoSinLarvas.class);
    }
}
