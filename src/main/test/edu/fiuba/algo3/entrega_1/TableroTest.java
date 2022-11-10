package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.CasillaNoExistente;
import edu.fiuba.algo3.modelo.excepciones.CasillaOcupada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TableroTest {
    Tablero tablero = new Tablero(1, 1);
    Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
    @Test
    public void test01CreoUnTableroYPuedoConstruirEnUnNodoDelMismo() {
        Tablero tablero = new Tablero(20, 20);
        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);

        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatible, new Recurso(50), zergs);

        assertDoesNotThrow(() -> tablero.construir(criadero, coordenadas));

    }

    @Test
    public void test02CreoUnTableroYNoPuedoConstruirEnUnNodoFueraDelMismo() {
        Tablero tablero = new Tablero(20, 20);
        Coordenadas coordenadas = new Coordenadas(40, 33);
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatible, new Recurso(50), zergs);

        assertThrows(CasillaNoExistente.class, () -> tablero.construir(criadero, coordenadas));
    }

    @Test
    public void test03CreoUnTableroYActualizaElTerrenoDeFormaCoherente() {
        Tablero tablero = new Tablero(20, 20);
        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatible, new Recurso(50), zergs);

        tablero.actualizarTerreno(coordenadas, 5, new Moho());

        assertDoesNotThrow(() -> tablero.construir(criadero, new Coordenadas(10, 5)));
    }
    @Test
    public void test04CreoUnTableroYConstruyoUnEdificioEnUnaUbicacionYLuegoIntentoCOnstruirOtroEnLaMismaYLanzaExcepcion() {
        Tablero tablero = new Tablero(20, 20);
        Coordenadas coordenadas = new Coordenadas(5, 5);
        tablero.establecerTerreno(new Moho(), coordenadas);
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatible, new Recurso(50), zergs);

        tablero.construir(criadero, coordenadas);

        assertThrows(CasillaOcupada.class, () -> tablero.construir(criadero, coordenadas));
    }

}
