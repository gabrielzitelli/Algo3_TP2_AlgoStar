package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.CasillaVacia;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SiRecolectable;

public class Mapa {
    static private Mapa mapaInstanciaUnica = new Mapa();

    //Hardcodeado, ver a futuro para crear en funcion de la cantidad de bases
    private int tamanio = 100;
    private Casilla matriz[][];

    private Mapa(){
        this.inicializarMapaConCasillasVacias();
    }

    private void inicializarMapaConCasillasVacias(){
        matriz = new Casilla[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                matriz[i][j] = new CasillaVacia();
            }
        }
    }

    static public Mapa obtener(){
        return mapaInstanciaUnica;
    }

    public void construirEdificio(Edificio unEdificio, Coordenada coordenada){
        Casilla casillaDondeConstruir = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDondeConstruir = casillaDondeConstruir.construirEdificio(unEdificio);
        this.actualizarCasillaPorCoordenada(coordenada, casillaDondeConstruir);
    }

    private Casilla encontrarCasillaPorCoordenada(Coordenada coordenada){
        int fila = coordenada.getCoordenadaX();
        int columna = coordenada.getCoordenadaY();
        return matriz[fila][columna];
    }

    private void actualizarCasillaPorCoordenada(Coordenada coordenada, Casilla casillaNueva){
        int fila = coordenada.getCoordenadaX();
        int columna = coordenada.getCoordenadaY();
        matriz[fila][columna] = casillaNueva;
    }

    public void reiniciarMapa(){
        this.inicializarMapaConCasillasVacias();
    }

    public void colocarMaterial(SiRecolectable materialAColocar, Coordenada coordenada){
        Casilla casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestino.colocarMaterial(materialAColocar);
    }
}
