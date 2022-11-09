package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.CasillaNoExistente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TableroTest {
    @Test
    public void test01CreoUnTableroYPuedoConstruirEnUnNodoDelMismo() {
        boolean excepcionLanzada = false;
        Tablero tablero = new Tablero(20, 20);
        Coordenadas coordenadas = new Coordenadas(5,5);
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatible,new Recurso(50));

        try {
            tablero.construir(criadero, new Coordenadas(5,5));
        } catch (CasillaNoExistente casilla) {
            excepcionLanzada = true;
        }
        assertFalse(excepcionLanzada);
    }
}
