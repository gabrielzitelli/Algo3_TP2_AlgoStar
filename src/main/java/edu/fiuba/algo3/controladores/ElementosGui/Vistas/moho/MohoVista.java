package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.modelo.Mapa.Casilla.EstadoMoho;

import java.util.ArrayList;

public abstract class MohoVista extends Vista {

    private static ArrayList<Vista> mohos = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> moho = new ArrayList<>();
        moho.add(new ConMohoVista());
        moho.add(new SinMohoVista());
        return moho;
    }
    
    public static Vista obtenerMoho(EstadoMoho obtenerMoho) {
        return obtenerVista(obtenerMoho, mohos);
    }
}
