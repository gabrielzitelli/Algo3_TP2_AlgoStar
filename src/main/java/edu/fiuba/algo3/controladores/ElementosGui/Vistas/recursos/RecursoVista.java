package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class RecursoVista extends Vista {

    private static ArrayList<Vista> recursos = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> recursos = new ArrayList<>();
        recursos.add(new MineralBrutoVista());
        recursos.add(new GasBrutoVista());
        return recursos;
    }

    public static Vista obtenerRecurso(Object obtenerMaterial) {
        return obtenerVista((String) obtenerMaterial, recursos);
    }
}
