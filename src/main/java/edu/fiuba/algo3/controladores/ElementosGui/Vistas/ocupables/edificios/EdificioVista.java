package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class EdificioVista extends OcupableVista {
    protected String edificiosRequisitos = "";

    public String getEdificiosRequisitos(){
        return edificiosRequisitos;
    }

    protected void aplicarEfectoBlancoYNegroImageView(ImageView imageView){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1.0);

        DropShadow efectorBordePNGBlanco = new DropShadow( 10, Color.WHITE );
        efectorBordePNGBlanco.setInput(colorAdjust);
        imageView.setEffect(efectorBordePNGBlanco);
    }
}
