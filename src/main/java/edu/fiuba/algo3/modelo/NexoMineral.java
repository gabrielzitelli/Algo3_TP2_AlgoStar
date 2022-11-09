package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;

import java.util.LinkedList;

public class NexoMineral extends Edificio {

    private Recurso minerales;
    private NodoRecurso recursoSobreElQueEsta;

    private LinkedList<Zangano> zanganosEmpleados;

    public NexoMineral(NodoCompatible requisitos, Recurso _minerales) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 4;
        nodoCompatible = requisitos;
        this.minerales = _minerales;
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.extraer();
        // TODO
    }

    @Override
    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        this.recursoSobreElQueEsta = nodoRecurso;
        return nodoCompatible.esCompatible(terreno, nodoRecurso);
    }

    public void extraer(){
        //recursoSobreElQueEsta.modificarRecurso(gasVespeno, ... );
    }
}
