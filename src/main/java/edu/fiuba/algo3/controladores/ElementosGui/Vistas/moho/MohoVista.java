package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;

import java.util.ArrayList;

public abstract class MohoVista extends Vista {

    private static final ArrayList<Vista> mohos = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> moho = new ArrayList<>();
        moho.add(new ConMohoVista());
        return moho;
    }
    
    public static Vista obtenerMoho(Object obtenerMoho) {
        return obtenerVista((String)obtenerMoho, mohos);
    }
}
