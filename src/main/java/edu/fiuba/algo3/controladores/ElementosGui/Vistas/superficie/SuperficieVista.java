package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos.RecursoVista;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class SuperficieVista extends Vista {

    private static ArrayList<Vista> superficies = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> superficies = new ArrayList<>();
        superficies.add(new SuperficieTerrestreVista());
        superficies.add(new SuperficieAereaVista());
        return superficies;
    }
    public static Vista obtenerSuperficie(Superficie obtenerSuperficie) {
        return obtenerVista(obtenerSuperficie, superficies);
    }
}
