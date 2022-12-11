package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;

public abstract class EdificioVista extends OcupableVista {
    protected String edificiosRequisitos = "";

    public String getEdificiosRequisitos(){
        return edificiosRequisitos;
    }
}
