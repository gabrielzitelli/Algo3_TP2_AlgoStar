package edu.fiuba.algo3.controladores.ElementosGui;

import javafx.scene.paint.Color;

public class ColorItem {

    private String valor;
    private Color color;

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
