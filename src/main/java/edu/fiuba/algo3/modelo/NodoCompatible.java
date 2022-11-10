package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;

public class NodoCompatible {

    private Terreno terrenoCompatible;
    private NodoRecurso recursoCompatible;
    public NodoCompatible(Terreno terreno, NodoRecurso recurso) {
        terrenoCompatible = terreno;
        recursoCompatible = recurso;
    }

    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        if (!(terrenoCompatible.igualA(terreno) && recursoCompatible.igualA(nodoRecurso))) {
            throw new TerrenoNoCompatibleConEdificio();
        }
        return true;
    }
}
