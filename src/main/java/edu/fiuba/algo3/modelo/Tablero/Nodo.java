package edu.fiuba.algo3.modelo.Tablero;

public class Nodo {

    private Terreno terreno;
    private NodoRecurso nodoRecurso;
    private NodoEstado nodoEstado;

    public Nodo(Terreno _terreno, NodoRecurso _nodoRecurso) {
        terreno = _terreno;
        nodoRecurso = _nodoRecurso;
        nodoEstado = new NodoVacio();
    }

    //Para pruebas, luego eliminar TODO
    public void establecerRecurso(NodoRecurso _nodoRecurso) {
        nodoRecurso = _nodoRecurso;
    }
}
