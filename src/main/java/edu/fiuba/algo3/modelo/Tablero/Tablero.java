package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Coordenadas;
import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.excepciones.CasillaNoExistente;

import java.util.*;

public class Tablero {
    private Nodo[] nodos;
    int filas;
    int columnas;
    public Tablero(int tamanioHorizontal, int tamanioVertical) {
        filas = tamanioVertical;
        columnas = tamanioHorizontal;
        generarTablero(filas, columnas);

    }
    private void generarTablero(int filas, int columnas) {
        nodos = new Nodo[filas * columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++){
                nodos[Coordenadas.obtenerPosicionRelativaEnR1(i,j,columnas)] = new Nodo();
            }
        }
    }
    private Nodo encontrarNodo(Coordenadas coordenadas) {
        int posicion = coordenadas.obtenerPosicionRelativaEnR1(columnas);
        posicionValida(posicion);
        return nodos[coordenadas.obtenerPosicionRelativaEnR1(columnas)];
    }
    private ArrayList<Coordenadas> encontrarAdyacentes(Coordenadas c) {
        return c.coordenadasAdyacentes(columnas, filas);
    }
    private void posicionValida(int posicion) {
        if (posicion >= filas*columnas){
            throw new CasillaNoExistente();
        }
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
        Set<Coordenadas> visitados = new HashSet<Coordenadas>();
        visitados.add(coordenadas);
        int nivel = 0;
        Deque<Coordenadas> cola = new ArrayDeque<Coordenadas>();
        cola.addFirst(coordenadas);
        while (!cola.isEmpty() && nivel < radio) {
            Coordenadas c = cola.pop();
            for(Coordenadas adyacente: this.encontrarAdyacentes(c)){
                if (!visitados.contains(adyacente)){
                    visitados.add(adyacente);
                    cola.addFirst(adyacente);
                    Nodo nodo = encontrarNodo(adyacente);
                    nodo.actualizarTerrenoCon(terreno);
                }
            }
            nivel++;
        }

    }

    public int extraer(Coordenadas coordenadas, int cantidad) {
        //TODO
        return 0;
    }
}
