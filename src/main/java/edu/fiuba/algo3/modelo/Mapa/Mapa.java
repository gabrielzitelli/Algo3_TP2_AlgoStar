package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMasCasillasLibresEnElMapa;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeMoverUnaUnidadQueNoExiste;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.CasillaDecorator.CasillaDecorator;
import edu.fiuba.algo3.modelo.Mapa.CasillaDecorator.OcupableDecorator;
import edu.fiuba.algo3.modelo.Mapa.CasillaDecorator.SuperficieBase;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.SinOcupar;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.lang.Math.*;

public class Mapa {
    static private Mapa mapaInstanciaUnica = new Mapa();
    private int tamanio = 100;
    private CasillaDecorator[][] matriz;
    private Map<Coordenada, Ocupable> diccionarioOcupables = new HashMap<>()
    private double coeficienteBases = 0.12; //Relacion entre bases y tamanio del mapa

    private Mapa(){
        this.inicializarMapaConCasillasVacias();
        this.inicializarBases();
        this.inicializarTerrenoEspacial();
    }

    private void inicializarMapaConCasillasVacias(){
        matriz = new CasillaDecorator[tamanio][tamanio];
        for (int i = 0; i < tamanio; i++)
            for (int j = 0; j < tamanio; j++)
                matriz[i][j] = new SuperficieBase(new Coordenada(i, j));
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

    private void inicializarTerrenoEspacial(){
        colocarTerrenoEspacialCircular(new Coordenada(tamanio/2, tamanio-1), (int)(0.15*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(tamanio/3, 5), (int)(0.08*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(tamanio, 15), (int)(0.06*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(tamanio/2, tamanio/2), (int)(0.05*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(3*tamanio/4, tamanio/2), (int)(0.05*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(0, -5), (int)(0.15*tamanio));
        colocarTerrenoEspacialCircular(new Coordenada(4*tamanio/5, (int)(tamanio*1.1)), (int)(0.15*tamanio));
    }

    private void colocarTerrenoEspacialCircular(Coordenada coordenadaCentro, int radio){
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                Coordenada coordenadaAColocar = new Coordenada(i, j);
                int dist = distanciaEntreDosCoordenadas(coordenadaAColocar, coordenadaCentro);

                if(dist <= radio)
                    colocarSuperficie(new SuperficieAerea(), coordenadaAColocar);
            }
        }
    }

    static public Mapa obtener(){
        return mapaInstanciaUnica;
    }
/*
    public void construirEdificioVerificacion(Edificio unEdificio, Coordenada coordenada){
        Casilla casillaDondeConstruir = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDondeConstruir.construirEdificioVerificacion(unEdificio);
    }
*/
    public void colocarOcupable(Ocupable unOcupable, Coordenada coordenada) {
        CasillaDecorator casillaADecorar = this.encontrarCasillaPorCoordenada(coordenada);
        CasillaDecorator nuevaCasillaDecorada = new OcupableDecorator(unOcupable, casillaADecorar);
        this.actualizarCasillaPorCoordenada(coordenada, nuevaCasillaDecorada);
    }

    public void quitarOcupable(Coordenada coordenada) {
        CasillaDecorator casillaAQuitarOcupable = this.encontrarCasillaPorCoordenada(coordenada);
        // TODO Colocar el valor Boolean estoyOcupada en casilla.
        casillaAQuitarOcupable = casillaAQuitarOcupable.quitarOcupable();
        this.actualizarCasillaPorCoordenada(coordenada, casillaAQuitarOcupable);
    }

    public Ocupable obtenerOcupable(Coordenada coordenada) {
        return new Zangano();
        // OcupableDecorator a = (OcupableDecorator)this.encontrarCasillaPorCoordenada(coordenada);
        // a.obtenerOcupable;
        // TODO Completar la logica de ocupable en el decorator
        //return this.encontrarCasillaPorCoordenada(coordenada).obtenerOcupable();
    }

    private CasillaDecorator encontrarCasillaPorCoordenada(Coordenada coordenada){
        int fila = coordenada.getCoordenadaX();
        int columna = coordenada.getCoordenadaY();
        return matriz[fila][columna];
    }

    private void actualizarCasillaPorCoordenada(Coordenada coordenada, CasillaDecorator casillaNueva){
        int fila = coordenada.getCoordenadaX();
        int columna = coordenada.getCoordenadaY();
        matriz[fila][columna] = casillaNueva;
    }

    /**
     * Reinicia el mapa con todas las casillas vacias y terrestres, no hay nada mas
     */
    public void reiniciarMapa(){
        this.inicializarMapaConCasillasVacias();
    }
    /**
     * Reinicia todas las bases del mapa, y lo deja listo como para una revancha
     */
    public void prepararMapaParaRevancha(){
        this.inicializarMapaConCasillasVacias();
        this.inicializarBases();
        this.inicializarTerrenoEspacial();
    }

    public void recolocarBasesIniciales(){
        this.reiniciarMapa();
        this.inicializarBases();
        this.inicializarTerrenoEspacial();
    }

    public void colocarMaterial(SiRecolectable materialAColocar, Coordenada coordenada){
        CasillaDecorator casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        // TODO
        //casillaDestino.colocarMaterial(materialAColocar);
    }

    public void colocarSuperficie(Superficie superficieAColocar, Coordenada coordenada) {
        CasillaDecorator casillaDestino = this.encontrarCasillaPorCoordenada(coordenada);
        casillaDestino.colocarSuperficie(superficieAColocar);
    }

    public int distanciaEntreDosCoordenadas(Coordenada coordenada1, Coordenada coordenada2){
        int distanciaEnX = abs( coordenada1.getCoordenadaX() - coordenada2.getCoordenadaX() );
        int distanciaEnY = abs( coordenada1.getCoordenadaY() - coordenada2.getCoordenadaY() );

        return distanciaEnX + distanciaEnY;
    }

    private boolean estaCoordenadaEnAnilloParteCircunferencial(Coordenada coordenadaAVerificar, Coordenada coordenadaCentro, int distanciaDesdeElCentro ){
        int xCoordenadaFinal = coordenadaAVerificar.getCoordenadaX();
        int yCoordenadaFinal = coordenadaAVerificar.getCoordenadaY();
        int xCoordenadaActual = coordenadaCentro.getCoordenadaX();
        int yCoordenadaActual = coordenadaCentro.getCoordenadaY();

        double hipotenusaAlCuadrado = (Math.pow((xCoordenadaFinal-xCoordenadaActual),2) + Math.pow((yCoordenadaFinal-yCoordenadaActual),2) );
        double radio = Math.pow(distanciaDesdeElCentro,2);

        return (hipotenusaAlCuadrado <= radio);
    }

    private LinkedList<Casilla> obtenerCasillasEnUnAnilloCentrado(Coordenada centroAnillo, int distanciaDesdeElCentro){

        LinkedList<Casilla> casillasSobreElAnillo = new LinkedList<>();

        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++) {
                if( estaCoordenadaEnAnilloParteCircunferencial( matriz[i][j].obtenerCoordenada(), centroAnillo, distanciaDesdeElCentro ) )
                    casillasSobreElAnillo.add(matriz[i][j]);
            }
        }

        return casillasSobreElAnillo;
    }

    public void colocarUnidadEnCasillaLibreMasCercana(Coordenada coordenadaOrigen, Unidad unaUnidad){

        boolean sePudoColocarUnidad = false;
        LinkedList<Casilla> casillasSobreUnAnillo;
        Casilla casillaOrigen = obtenerCasilla(coordenadaOrigen);

        try{
            colocarOcupable(unaUnidad, casillaOrigen.obtenerCoordenada());
            sePudoColocarUnidad = true;
        } catch(RuntimeException ignore) {}

        int i = 1;
        while ( i < tamanio && !sePudoColocarUnidad ){
            casillasSobreUnAnillo = obtenerCasillasEnUnAnilloCentrado(coordenadaOrigen, i);

            for(Casilla casillaCandidataParaColocar : casillasSobreUnAnillo){
                try{
                    casillaCandidataParaColocar.tieneEsteRecoletable(new NoRecolectable());
                    colocarOcupable(unaUnidad, casillaCandidataParaColocar.obtenerCoordenada());
                    sePudoColocarUnidad = true;
                    break;
                } catch(RuntimeException ignore) {}
            }
            i++;
        }

        if(!sePudoColocarUnidad)
            throw new ErrorNoHayMasCasillasLibresEnElMapa();
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
    public void desabastecerEnergia(Coordenada origenDeExpansion, int radioDeEnergia) {
        LinkedList<Casilla> casillasDentroDelRadio = obtenerCasillasDentroDelRadio(origenDeExpansion, radioDeEnergia);
        for(Casilla unaCasilla : casillasDentroDelRadio)
            unaCasilla.descargarDeEnergia();
    }

    public void revelar(Coordenada coordenadaOrigen, int radio) {
        LinkedList<Casilla> casillasDentroDelRadio = obtenerCasillasDentroDelRadio(coordenadaOrigen, radio);
        for (Casilla unaCasilla : casillasDentroDelRadio)
            unaCasilla.revelar();
    }

    public void desRevelar(Coordenada coordenadaOrigen, int radio) {
        LinkedList<Casilla> casillasDentroDelRadio = obtenerCasillasDentroDelRadio(coordenadaOrigen, radio);
        for (Casilla unaCasilla : casillasDentroDelRadio)
            unaCasilla.desRevelar();
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

        if (casillaInicial.obtenerOcupable().getClass().equals(SinOcupar.class)){
            throw new ErrorNoSePuedeMoverUnaUnidadQueNoExiste();
        }

        //Actualizo la casillaFinal con una casilla que ahora tiene la Unidad de casillaInicial
        casillaFinal = casillaInicial.moverUnidadHacia(casillaFinal);
        this.actualizarCasillaPorCoordenada(coordenadaFinal, casillaFinal);

        //Actualizo la casillaInicial con una casilla con los mismo atributos que tenia casillaInicial pero ahora
        //sin la unidad que contenia
        casillaInicial = casillaInicial.quitarOcupable();
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

    public Casilla obtenerVolcanBaseLejanaPrimeraMitad(){
        LinkedList<Casilla> volcanesDeGas = this.obtenerVolcanesDeGas();
        return volcanesDeGas.getFirst();
    }

    public Casilla obtenerVolcanBaseLejanaSegundaMitad(){
        LinkedList<Casilla> volcanesDeGas = this.obtenerVolcanesDeGas();
        return volcanesDeGas.getLast();
    }

    public boolean estaEnergizado(Coordenada coordenada) {
        boolean carga = true;
        Casilla casilla = encontrarCasillaPorCoordenada(coordenada);

        try {
            casilla.tieneEstaCarga(new ConCarga());
        } catch (ErrorEdificioNoSePuedeConstruirEnEstaCasilla error){
            carga = false;
        }

        return carga;
    }

    public boolean estaDentroDeRango(Coordenada coordenadaOrigen, Casilla casillaDestino, int rango) {
        LinkedList<Casilla> casillas = obtenerCasillasDentroDelRadio(coordenadaOrigen, rango);
        return casillas.contains(casillaDestino);
    }

    public int obtenerTamanioMapa() {
        return tamanio;
    }

    public Casilla obtenerCasilla(Coordenada coordenada) {
        return encontrarCasillaPorCoordenada(coordenada);
    }
}
