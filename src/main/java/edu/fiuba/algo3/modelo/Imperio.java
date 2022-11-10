package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.NoSeCumplenLosPreRequisitosDelEdificio;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Imperio {

    protected Tablero tablero;
    protected Recurso minerales;
    protected Recurso gasVespeno;
    protected LinkedList<Edificio> edificios;

    public abstract void terminarTurno();
    protected void coomprobarRequisitos(ArrayList<Edificio> requisitos) {
        int requisitosCumplidos = 0;
        for(Edificio requisito: requisitos){
            for(Edificio edificio: edificios) {
                if (edificio.getClass().equals(requisito.getClass())){
                    requisitosCumplidos++;
                    break;
                }
            }
        }
        if (requisitosCumplidos != requisitos.size()){
            throw new NoSeCumplenLosPreRequisitosDelEdificio();
        }
    }
}
