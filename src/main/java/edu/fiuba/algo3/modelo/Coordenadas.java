package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaNoExistente;

import java.util.ArrayList;

public class Coordenadas {

    private int x;
    private int y;

    private static int transformarAR1(int x, int y, int ancho) {
        return y * ancho + x;
    }

    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int obtenerPosicionRelativaEnR1(int ancho) {
        return transformarAR1(x, y, ancho);
    }

    public static int obtenerPosicionRelativaEnR1(int x, int y, int ancho) {
        return transformarAR1(x, y, ancho);
    }

    public ArrayList<Coordenadas> coordenadasAdyacentes(int ancho, int alto) {
        ArrayList<Coordenadas> coordenadas = new ArrayList<Coordenadas>();
        if (x + 1 < ancho) { coordenadas.add(new Coordenadas(x + 1, y)); }
        if (x - 1 >= 0) { coordenadas.add(new Coordenadas(x - 1, y)); }
        if (y + 1 < alto) { coordenadas.add(new Coordenadas(x, y + 1)); }
        if (y - 1 >= 0) { coordenadas.add(new Coordenadas(x, y - 1)); }
        return coordenadas;
    }
}

