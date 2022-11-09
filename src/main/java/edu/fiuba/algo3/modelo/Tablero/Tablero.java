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
    //Metodo para pruebas mientras tanto TODO
    public void establecerTerreno(Terreno terreno, Coordenadas coordenadas){
        Nodo nodo = encontrarNodo(coordenadas);
        nodo.actualizarTerrenoCon(terreno);
    }
    public void construir(Edificio construccion, Coordenadas coordenadas) {
        Nodo nodo = encontrarNodo(coordenadas);
        nodo.construir(construccion);
    }
    public void actualizarTerreno(Coordenadas coordenadas, int radio, Terreno terreno) {
        //Recorrido bfs de un grafo aplicado a un tablero de r2.
        Set<Nodo> visitados = new HashSet<>();
        visitados.add(encontrarNodo(coordenadas));
        HashMap<Nodo, Integer> distancia = new HashMap<>();
        distancia.put(encontrarNodo(coordenadas), 0);
        Deque<Coordenadas> cola = new ArrayDeque<>();
        cola.addFirst(coordenadas);

        while (!cola.isEmpty()) {
            Coordenadas c = cola.pop();
            for(Coordenadas adyacente: this.encontrarAdyacentes(c)){
                Nodo nodo = encontrarNodo(adyacente);
                if (!visitados.contains(nodo)){
                    distancia.put(nodo, distancia.get(encontrarNodo(c)) + 1);
                    if (distancia.get(nodo) <= radio) {
                        visitados.add(nodo);
                        cola.addFirst(adyacente);
                        nodo.actualizarTerrenoCon(terreno);
                    }
                }
            }
        }

    }

    public int extraer(Coordenadas coordenadas, int cantidad) {
        //TODO
        return 0;
    }
}
