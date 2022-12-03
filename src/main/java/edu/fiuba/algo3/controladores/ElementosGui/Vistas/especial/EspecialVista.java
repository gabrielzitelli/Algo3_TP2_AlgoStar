package edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.modelo.Ataque.Ocupable;

import java.util.ArrayList;

public abstract class EspecialVista extends Vista{

    private static ArrayList<Vista> especiales = inicializarLista();

    private static ArrayList<Vista> inicializarLista() {
        ArrayList<Vista> especiales = new ArrayList<>();
        especiales.add(new EnConstruccionVista());
        return especiales;
    }

    public static Vista obtenerEspecial(Object obtenerOcupable) {
        return obtenerVista((String)obtenerOcupable, especiales);
    }
}
