package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;

import java.util.LinkedList;

public class NexoMineral extends Edificio {

    private Recurso minerales;
    private NodoRecurso nodoMinerales;
    private int unidadesPorTurno = 10;

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

        minerales.depositar(nodoMinerales.extraer(unidadesPorTurno));
    }

    @Override
    public void esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        super.esCompatible(terreno, nodoRecurso);
        nodoMinerales = nodoRecurso;
    }
}
