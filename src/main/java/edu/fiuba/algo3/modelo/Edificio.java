package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;

import java.util.stream.IntStream;

public abstract class Edificio implements Turno, Daniable {

    NodoCompatible nodoCompatible;

    public int turnosDeConstruccion;

    public int turnosExistiendo;


    public void recibirDanio(int danio){
    }


    public void estaActiva(){
        IntStream rangoDeExistencia = IntStream.range(0, turnosExistiendo + 1); // crea array {0 ... turnosExistiendo}
        if( rangoDeExistencia.noneMatch(each -> turnosDeConstruccion == each))
            throw new RuntimeException("Construccion no finalizada");
    }

    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        return nodoCompatible.esCompatible(terreno, nodoRecurso);
    }
}
