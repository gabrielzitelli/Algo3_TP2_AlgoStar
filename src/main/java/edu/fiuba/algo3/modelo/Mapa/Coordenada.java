package edu.fiuba.algo3.modelo.Mapa;

public class Coordenada {
    private final int coordenadaX;
    private final int coordenadaY;

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
