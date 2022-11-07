package edu.fiuba.algo3.modelo;

public class Tablero {
    private Nodo[][] nodos;
    public Tablero(int tamanioHorizontal, int tamanioVertical) {
        for (int i = 0; i < tamanioHorizontal; i++) {
            for (int j = 0; j < tamanioVertical; j++){
                nodos[i][j] = new Nodo();
            }
        }
    }
}
