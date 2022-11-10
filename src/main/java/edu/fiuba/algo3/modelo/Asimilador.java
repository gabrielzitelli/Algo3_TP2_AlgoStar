package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import edu.fiuba.algo3.modelo.excepciones.EdificioEnConstruccion;

public class Asimilador extends Edificio {

    private Recurso gasVespeno;
    private NodoRecurso nodoGasVespeno;
    private int unidadesPorTurno = 20;

    public Asimilador(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
    }

    @Override
    public void accionDeTurno() {
        try {
            this.estaActiva();
            gasVespeno.depositar(nodoGasVespeno.extraer(unidadesPorTurno));
        }
        catch(EdificioEnConstruccion e){
        }
        turnosExistiendo ++;

    }

    @Override
    public void esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        super.esCompatible(terreno, nodoRecurso);
        nodoGasVespeno = nodoRecurso;
    }
}
