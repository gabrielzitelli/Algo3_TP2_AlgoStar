package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;

import java.util.ArrayList;

public abstract class SuperficieVista extends Vista {

    private static final ArrayList<Vista> superficies = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> superficies = new ArrayList<>();
        superficies.add(new SuperficieTerrestreVista());
        superficies.add(new SuperficieAereaVista());
        return superficies;
    }

    public static Vista obtenerSuperficie(Object obtenerSuperficie) {
        return obtenerVista((String) obtenerSuperficie, superficies);
    }
}
