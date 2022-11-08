package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Coordenadas;
import edu.fiuba.algo3.modelo.Edificio;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final Nodo[][] nodos;
    public Tablero(int tamanioHorizontal, int tamanioVertical) {
        nodos = new Nodo[tamanioHorizontal][tamanioVertical];
        for (int i = 0; i < tamanioHorizontal; i++) {
            for (int j = 0; j < tamanioVertical; j++){
                nodos[i][j] = new Nodo(new Neutro(), new SinRecurso());
            }
        }
        generarRecursos();
    }
    private void generarRecursos() {
        //TODO
    }
    private Nodo encontrarNodo(Coordenadas coordenadas) {
        //TODO
        return new Nodo(new Neutro(), new SinRecurso());
    }

    //Metodo para pruebas mientras tanto TODO
    public void establecerRecurso(NodoRecurso nodoRecurso, Coordenadas coordenadas){
        Nodo nodo = encontrarNodo(coordenadas);
        nodo.establecerRecurso(nodoRecurso);
    }
    public List<Coordenadas> obtenerNodosConGas(){
        //TODO
        return new ArrayList<>();
    }
    public List<Coordenadas> obtenerNodosConMinerales(){
        //TODO
        return new ArrayList<>();
    }
    public void construir(Edificio construccion, Coordenadas coordenadas) {
        //TODO
    }
    public void actualizarTerreno(Coordenadas coordenadas, int radio, Terreno terreno) {
        //TODO
    }
    public int extraer(Coordenadas coordenadas, int cantidad) {
        //TODO
        return 0;
    }
}
