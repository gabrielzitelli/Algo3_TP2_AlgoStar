package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Imperio {
    protected Mineral mineralesDelImperio;
    protected Gas gasDelImperio;
    protected LinkedList<Edificio> edificios;
    protected FabricasDisponibles fabricasDisponibles;
    protected ArrayList<Unidad> unidades;
    protected int cantidadInicialDeMineral = 200;

    public void terminarTurno(){
        for (Edificio edificio : edificios) {
            edificio.pasarTurno();
        }
        for (Unidad unidad: unidades){
            unidad.pasarTurno();
        }
    }
    protected void construirEdificio(Edificio edificio, Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        comprobarRequisitosMateriales(edificio);
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void construirUnidad(Unidad unaUnidad, Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        comprobarRequisitosMaterialesUnidada(unaUnidad);
        mapa.colocarUnaUnidad(unaUnidad, coordenada);
        // TODO Agregar la unidad a una coleccion de unidades del imperio
    }

    private void comprobarRequisitosMateriales(Edificio edificio){
        ArrayList<Recurso> listaDeRequisitos = edificio.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        mineralesDelImperio.consumir(mineralAConsumir.obtenerCantidad());
        Recurso gasAconsumir = listaDeRequisitos.get(1);
        gasDelImperio.consumir(gasAconsumir.obtenerCantidad());
    }

    private void comprobarRequisitosMaterialesUnidada(Unidad unaUnidad){
        ArrayList<Recurso> listaDeRequisitos = unaUnidad.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        mineralesDelImperio.consumir(mineralAConsumir.obtenerCantidad());
        Recurso gasAconsumir = listaDeRequisitos.get(1);
        gasDelImperio.consumir(gasAconsumir.obtenerCantidad());
    }

    protected void comprobarRequisitos(ArrayList<Edificio> requisitos) {
        int requisitosCumplidos = 0;
        for(Edificio requisito: requisitos){
            for(Edificio edificio: edificios) {
                if (edificio.getClass().equals(requisito.getClass())){
                    requisitosCumplidos++;
                    break;
                }
            }
        }
        if (requisitosCumplidos != requisitos.size()){
            throw new ErrorNoSeCumplenLosPreRequisitosDelEdificio();
        }
    }
    public boolean tienesEstaCantidadDeMineral(int recurso) {
        return mineralesDelImperio.tenesCantidadDeRecurso(recurso);
    }

    public boolean tienesEstaCantidadDeGas(int recurso) {
        return gasDelImperio.tenesCantidadDeRecurso(recurso);
    }
    public Edificio conseguirEdificio(Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        return mapa.obtenerEdificio(coordenada);
    }

    public void abastecerDeRecursos() {
        //Metodo De inicializacion Utilitario
        mineralesDelImperio = new Mineral(5000);
        gasDelImperio = new Gas(5000);
    }

    public void abastecerDeRecursos(Mineral mineral, Gas gas) {
        //Metodo de inicializacion utilitario
        mineralesDelImperio = mineral;
        gasDelImperio = gas;
    }

    public boolean tieneEdificio(Edificio edificioABuscar) {
        for (Edificio edificio : edificios) {
            if (edificio.esIgualA(edificioABuscar))
                return true;
        }
        return false;
    }

    public boolean tieneUnidad(Unidad unidadABuscar) {
        for (Unidad unidad : unidades){
            if (unidad.esIgualA(unidadABuscar))
                return true;
        }
        return false;
    }
}
