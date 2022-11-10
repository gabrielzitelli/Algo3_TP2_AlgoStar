package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;

public class NodoCompatible {

    private Terreno terrenoCompatible;
    private NodoRecurso recursoCompatible;

    public NodoCompatible(Terreno terreno, NodoRecurso recurso) {
        this.terrenoCompatible = terreno;
        this.recursoCompatible = recurso;
    }

    public NodoCompatible() {
        this.terrenoCompatible = new Neutro();
        this.recursoCompatible = new SinRecurso();
    }

    public void esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        if (!(terrenoCompatible.igualA(terreno) && recursoCompatible.igualA(nodoRecurso))) {
            throw new TerrenoNoCompatibleConEdificio();
        }
    }
}
