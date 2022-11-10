package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;

public class Asimilador extends Edificio {


    private Recurso gasVespeno;
    private NodoRecurso recursoSobreElQueEsta;

    public Asimilador(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
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
        recursoSobreElQueEsta.modificarRecurso(gasVespeno, 20);
    }
}
