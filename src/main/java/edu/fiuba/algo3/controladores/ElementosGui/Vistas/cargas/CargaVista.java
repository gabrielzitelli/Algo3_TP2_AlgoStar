package edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Cargable;

import java.util.ArrayList;

public abstract class CargaVista extends Vista{
    private static ArrayList<Vista> cargas = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> cargas = new ArrayList<>();
        cargas.add(new ConCargaVista());
        return cargas;
    }
    public static Vista obtenerCarga(Object obtenerCarga) {
        return obtenerVista((String)obtenerCarga, cargas);
    }
}
