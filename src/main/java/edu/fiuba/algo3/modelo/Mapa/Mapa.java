package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.UnidadZerg;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class Mapa {
    static private Mapa mapaInstanciaUnica = new Mapa();

    //Hardcodeado, ver a futuro para crear en funcion de la cantidad de bases
    private int tamanio = 100;
    private Casilla[][] matriz;

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

    private void inicializarBases(){
        int mitadLadoMapa = tamanio/2;
        int cuartoLadoMapa = tamanio/4;

        this.colocarUnaBase(new Coordenada(mitadLadoMapa, cuartoLadoMapa));
        this.colocarUnaBase(new Coordenada(tamanio - mitadLadoMapa, cuartoLadoMapa));
    }

    private void colocarUnaBase(Coordenada centroBase){

        Coordenada[] coordMineralesBase = {
                new Coordenada(centroBase.getCoordenadaX() +1, centroBase.getCoordenadaY()-1),
                new Coordenada(centroBase.getCoordenadaX() +1, centroBase.getCoordenadaY()),
                new Coordenada(centroBase.getCoordenadaX() +1, centroBase.getCoordenadaY()+1),
        };

        colocarMaterial(new GasRecolectable(), centroBase);
        colocarMaterial(new MineralRecolectable(), coordMineralesBase[0]);
        colocarMaterial(new MineralRecolectable(), coordMineralesBase[1]);
        colocarMaterial(new MineralRecolectable(), coordMineralesBase[2]);
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
}
