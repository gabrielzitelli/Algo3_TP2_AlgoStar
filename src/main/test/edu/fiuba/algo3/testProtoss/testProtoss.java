package edu.fiuba.algo3.testProtoss;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testProtoss {

    @Test
    public void construirPilonNoDevuelveError(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodo = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon pilon = new Pilon(tablero, nodo, new Coordenadas(0,0));

        //assertDoesNotThrow(Pilon pilon = new Pilon(nodo));
    }
}
