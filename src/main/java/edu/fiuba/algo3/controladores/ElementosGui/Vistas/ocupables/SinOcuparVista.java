package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables;

import edu.fiuba.algo3.modelo.Unidades.SinOcupar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class SinOcuparVista extends OcupableVista {

    public SinOcuparVista(){
        this.info = "Libre";
        this.identificador = "ninguno";
        this.imagenParaDisplay = null;
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        //No renderiza
    }

    public void manejarBotones(Button[] arrayBotones){
        for(Button boton : arrayBotones)
            boton.setVisible(false);
    }
}
