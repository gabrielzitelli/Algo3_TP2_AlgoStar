package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Imperio {
    protected Recurso mineralesDelImperio;
    protected Recurso gasDelImperio;
    protected LinkedList<Edificio> edificios;

    public void terminarTurno(){
        for (Edificio edificio : edificios) {
            edificio.pasarTurno();
        }
    }

    protected void construirEdificio(Edificio edificio, Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void comprobarRequisitos(ArrayList<Edificio> requisitos) {
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
            throw new ErrorNoSeCumplenLosPreRequisitosDelEdificio();
        }
    }
    public boolean tienesEstaCantidadDeMineral(int recurso) {
        return mineralesDelImperio.tenesCantidadDeRecurso(recurso);
    }

    public boolean tienesEstaCantidadDeGas(int recurso) {
        return gasDelImperio.tenesCantidadDeRecurso(recurso);
    }
    public Edificio conseguirEdificio(Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        return mapa.obtenerEdificio(coordenada);
    }
}
