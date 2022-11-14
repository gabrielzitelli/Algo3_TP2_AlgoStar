package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.CasillaVacia;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SiRecolectable;

import java.util.LinkedList;

import static java.lang.Math.abs;

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
                matriz[i][j] = new CasillaVacia(new Coordenada(i, j));
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

    public void destruirEdificio(Coordenada coordenada){
        // Capaz estoy acoplando mucho edificio y mapa con esto
        Casilla casillaDestruir = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestruir = new CasillaVacia(coordenada);
        this.actualizarCasillaPorCoordenada(coordenada, casillaDestruir);
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

    public int distanciaEntreDosCoordenadas(Coordenada coordenada1, Coordenada coordenada2){
        int distanciaEnX = abs( coordenada1.getCoordenadaX() - coordenada2.getCoordenadaX() );
        int distanciaEnY = abs( coordenada1.getCoordenadaY() - coordenada2.getCoordenadaY() );

        return distanciaEnX + distanciaEnY;
    }

    private LinkedList<Casilla> obtenerCasillasDentroDelRadio(Coordenada origenDeExpansion, int radio){

        LinkedList<Casilla> casillasDentroDelRadio = new LinkedList<>();

        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++) {
                if( distanciaEntreDosCoordenadas(origenDeExpansion, matriz[i][j].obtenerCoordenada()) <= radio )
                    casillasDentroDelRadio.add(matriz[i][j]);
            }
        }
        return casillasDentroDelRadio;
    }

    public void expandirMoho(Coordenada origenDeExpansion, int radio){
        LinkedList<Casilla> casillasDentroDelRadio = obtenerCasillasDentroDelRadio(origenDeExpansion, radio);
        for(Casilla unaCasilla : casillasDentroDelRadio)
            unaCasilla.llenarDeMoho();
    }

    public void abastecerEnergia(Coordenada origenDeExpansion, int radioDeEnergia) {
        LinkedList<Casilla> casillasDentroDelRadio = obtenerCasillasDentroDelRadio(origenDeExpansion, radioDeEnergia);
        for(Casilla unaCasilla : casillasDentroDelRadio)
            unaCasilla.cargarDeEnergia();
    }
}
