package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.*;

public class Mapa {
    static private Mapa mapaInstanciaUnica = new Mapa();
    private int tamanio = 100;
    private Casilla[][] matriz;
    private double coeficienteBases = 0.12; //Relacion entre bases y tamanio del mapa

    private Mapa(){
        this.inicializarMapaConCasillasVacias();
        this.inicializarBases();
    }

    private void inicializarMapaConCasillasVacias(){
        matriz = new Casilla[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                matriz[i][j] = new CasillaVacia(new Coordenada(i, j));
            }
        }
    }

    private void colocarBasesPorMitadDeMapa(double densidadDeBasesPorEje, int cantidadDeBases, int comienzoDeLaMitadDelMapa){

        int cantidadEjesHorizontales = 0; // Cantidad de ejes horizontales
        int totalDeBasesRestantes = cantidadDeBases;

        // Establezco la cantidad de ejes horizontales acorde al tamanio del mapa y a la densidadDeBasesPorEje
        do {
            cantidadEjesHorizontales++;
            totalDeBasesRestantes -= tamanio * densidadDeBasesPorEje;
        } while (totalDeBasesRestantes >= 0);

        totalDeBasesRestantes = cantidadDeBases;
        //Por cada eje horizontal colocaré las bases de los ejes verticales
        for (int i = 0; i < cantidadEjesHorizontales; i++) {

            int posicionHorizontal = (int)(tamanio * ( (i+1) / (cantidadDeBases+2.0)));
            int cantidadDeEjesVerticales = (int)ceil((cantidadDeBases+1.0)/cantidadEjesHorizontales);

            //Si hay bases por colocar, las coloco
            for (int j = 0; j < cantidadDeEjesVerticales; j++) {
                int posicionEquidistanteEnY = (int)(tamanio * ( (j+1) / (cantidadDeEjesVerticales+1.0) ));

                if(totalDeBasesRestantes > 0){
                    Coordenada coordenadaCentroBase = new Coordenada(posicionHorizontal + comienzoDeLaMitadDelMapa,
                            posicionEquidistanteEnY);
                    this.colocarUnaBase(coordenadaCentroBase);
                    totalDeBasesRestantes--;
                }
            }
        }
    }

    private void inicializarBases(){

        int cantidadBases = (int) round(tamanio * coeficienteBases);
        int cantLadoDerecho = cantidadBases - (int) ceil(cantidadBases / 2.0);
        int cantLadoIzquierdo = cantidadBases - (int) floor(cantidadBases / 2.0);

        //Definira la cantidad de bases por eje horizontal en relacion con el tamanio del mapa
        double densidadDeBasesPorEje = 0.02;

        //Coloco bases en la primera mitad del mapa
        this.colocarBasesPorMitadDeMapa(densidadDeBasesPorEje, cantLadoDerecho, 0);

        //Coloco bases en la segunda mitad del mapa
        this.colocarBasesPorMitadDeMapa(densidadDeBasesPorEje, cantLadoIzquierdo, tamanio/2);
    }

    /* Forma de una base. M = Mineral, V = Volcan de gas
     *  - - - - -
     *  - M M M -
     *  - - V M -
     *  - M M M -
     *  - - - - -
     */
    private void colocarUnaBase(Coordenada centroBase){

        int xCentro = centroBase.getCoordenadaX();
        int yCentro = centroBase.getCoordenadaY();

        //Coloco los minerales en formación
        for (int i = (xCentro -1); i < (xCentro +2); i++) {
            for (int j = (yCentro -1); j < (yCentro +2); j++) {

                boolean esPosicionVolcan = (xCentro == i) && (yCentro == j);
                boolean esPosicionDedicadaVacia = (xCentro == i) && ((yCentro -1) == j);

                if( !esPosicionVolcan && !esPosicionDedicadaVacia )
                    colocarMaterial(new MineralRecolectable(), new Coordenada(i, j));
            }
        }

        //Coloco el volcan de gas en el centro
        colocarMaterial(new GasRecolectable(), new Coordenada(xCentro, yCentro));
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
        casillaDestruir = casillaDestruir.desconstruirEdificio(coordenada);
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

    public void recolocarBasesIniciales(){
        this.reiniciarMapa();
        this.inicializarBases();
    }

    public void colocarMaterial(SiRecolectable materialAColocar, Coordenada coordenada){
        Casilla casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestino.colocarMaterial(materialAColocar);
    }

    public void colocarSuperficie(Superficie superficieAColocar, Coordenada coordenada) {
        Casilla casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestino.colocarSuperficie(superficieAColocar);
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

    public void colocarUnidadZerg(UnidadZerg unaUnidadZerg, Coordenada unaCoordenada) {
        Casilla casillaDondeColocar = this.encontrarCasillaPorCoordenada(unaCoordenada);

        casillaDondeColocar = casillaDondeColocar.colocarUnidadZerg(unaUnidadZerg);

        this.actualizarCasillaPorCoordenada(unaCoordenada, casillaDondeColocar);
    }
    public Edificio obtenerEdificio(Coordenada coordenada) {
        Casilla casillaConEdificio = this.encontrarCasillaPorCoordenada(coordenada);
        return casillaConEdificio.obtenerEdificio();
    }


    public void colocarUnaUnidad(Unidad unaUnidad, Coordenada coordenada){
        // Busco la casilla de la coordenada y creo una nueva casilla ocupada por la unidad
        Casilla casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestino = casillaDestino.colocarUnidad(unaUnidad);
        this.actualizarCasillaPorCoordenada(coordenada, casillaDestino);
    }

    public void atacar(Coordenada atacante, Coordenada atacado){
        // Busco la casilla de atacante y atacado y hago que el atacante la ataque
        Casilla casillaAtacante = this.encontrarCasillaPorCoordenada(atacante);
        Casilla casillaAtacado = this.encontrarCasillaPorCoordenada(atacado);

        casillaAtacante.atacar(casillaAtacado);
    }

    public void moverUnidad(Coordenada coordenadaInicial, Coordenada coordenadaFinal){
        Casilla casillaInicial = this.encontrarCasillaPorCoordenada(coordenadaInicial);
        Casilla casillaFinal = this.encontrarCasillaPorCoordenada(coordenadaFinal);

        //Actualizo la casillaFinal con una casilla que ahora tiene la Unidad de casillaInicial
        casillaFinal = casillaInicial.moverUnidadHacia(casillaFinal);
        this.actualizarCasillaPorCoordenada(coordenadaFinal, casillaFinal);

        //Actualizo la casillaInicial con una casilla con los mismo atributos que tenia casillaInicial pero ahora
        //sin la unidad que contenia
        casillaInicial = casillaInicial.quitarUnidad();
        this.actualizarCasillaPorCoordenada(coordenadaInicial, casillaInicial);
    }

    public LinkedList<Casilla> obtenerMinerales(){

        LinkedList<Casilla> minerales = new  LinkedList<>();

        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                boolean tieneCasillaMineral = matriz[i][j].obtenerMaterial().getClass().equals(MineralBruto.class);
                if (tieneCasillaMineral)
                    minerales.push(matriz[i][j]);
            }
        }
        return minerales;
    }

    public LinkedList<Casilla> obtenerVolcanesDeGas(){

        LinkedList<Casilla> volcanesDeGas = new  LinkedList<>();

        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                boolean tieneCasillaVolcan = matriz[i][j].obtenerMaterial().getClass().equals(GasBruto.class);
                if (tieneCasillaVolcan)
                    volcanesDeGas.push(matriz[i][j]);
            }
        }
        return volcanesDeGas;
    }
}
