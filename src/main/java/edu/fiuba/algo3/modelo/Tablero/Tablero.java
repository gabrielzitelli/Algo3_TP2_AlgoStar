package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Coordenadas;
import edu.fiuba.algo3.modelo.Edificio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    private Nodo[][] nodos;
    int filas;
    int columnas;
    public Tablero(int tamanioHorizontal, int tamanioVertical) {
        filas = tamanioVertical;
        columnas = tamanioHorizontal;
        generarTablero(filas, columnas);

    }
    private void generarTablero(int filas, int columnas) {
        nodos = new Nodo[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++){
                nodos[i][j] = new Nodo();
            }
        }
    }
    private Nodo encontrarNodo(Coordenadas coordenadas) {
        return nodos[coordenadas.obtenerX()][coordenadas.obtenerY()];
    }
    //Metodo para pruebas mientras tanto TODO
    public void establecerRecurso(NodoRecurso nodoRecurso, Coordenadas coordenadas){
        Nodo nodo = encontrarNodo(coordenadas);
        nodo.establecerRecurso(nodoRecurso);
    }
    public void construir(Edificio construccion, Coordenadas coordenadas) {
        Nodo nodo = encontrarNodo(coordenadas);
        nodo.construir(construccion);
    }
    public void actualizarTerreno(Coordenadas coordenadas, int radio, Terreno terreno) {
        //TODO
    }
    public int extraer(Coordenadas coordenadas, int cantidad) {
        //TODO
        return 0;
    }
}
