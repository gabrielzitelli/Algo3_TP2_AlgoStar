package edu.fiuba.algo3.controladores.ElementosGui.Vistas;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Vista {


    protected String identificador;
    protected Tile tile;
    protected String info;
    private static Vista sinVista = new SinVista();

    protected static Vista obtenerVista(String obtenerElemento, ArrayList<Vista> elementosVista) {
        for (Vista vista : elementosVista){

            if (obtenerElemento != null && vista.identificador.equals(obtenerElemento)){
                return vista;
            }
        }
        return sinVista;
    }

    public void render(GraphicsContext gc, int x, int y){
        tile.render(gc, x, y);
    }

    public String getInfo() {
        return info;
    }
}
