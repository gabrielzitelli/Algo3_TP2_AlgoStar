package edu.fiuba.algo3.modelo.Mapa;

public class Coordenada {
    private int coordenadaX;
    private int coordenadaY;

    public Coordenada(int coordenadaX, int coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaX(){
        return this.coordenadaX;
    }

    public int getCoordenadaY(){
        return this.coordenadaY;
    }
}
