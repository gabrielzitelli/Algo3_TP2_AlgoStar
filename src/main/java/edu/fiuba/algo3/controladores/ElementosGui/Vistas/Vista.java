package edu.fiuba.algo3.controladores.ElementosGui.Vistas;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos.RecursoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Vista {


    protected Object elemento;
    protected Tile tile;
    protected String info;
    private static Vista sinVista = new SinVista();

    protected static Vista obtenerVista(Object obtenerElemento, ArrayList<Vista> elementosVista) {

        for (Vista vista : elementosVista){
            Object elementoGuardado = vista.elemento;
            if (obtenerElemento != null && elementoGuardado.getClass().equals(obtenerElemento.getClass())){
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
