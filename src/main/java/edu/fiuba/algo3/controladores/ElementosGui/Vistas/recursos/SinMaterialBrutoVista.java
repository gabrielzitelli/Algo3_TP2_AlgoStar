package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.modelo.Mapa.SinMaterialBruto;
import javafx.scene.canvas.GraphicsContext;

public class SinMaterialBrutoVista extends RecursoVista {

    public SinMaterialBrutoVista() {
        elemento = new SinMaterialBruto();
        info = "ninguno";
    }

    @Override
    public void render(GraphicsContext gc, int i, int j){
        //No grafica
    }
}
