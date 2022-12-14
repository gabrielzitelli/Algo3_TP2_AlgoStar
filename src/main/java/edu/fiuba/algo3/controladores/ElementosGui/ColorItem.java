package edu.fiuba.algo3.controladores.ElementosGui;

import javafx.scene.paint.Color;

public class ColorItem {

    private final String valor;
    private final Color color;

    public ColorItem(String valor, Color color){
        this.valor = valor;
        this.color = color;
    }

    public String obtenerValor(){
        return this.valor;
    }

    public Color obtenerColor(){
        return this.color;
    }
}
