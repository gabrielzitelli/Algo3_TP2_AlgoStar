package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.AlgoStar.Jugador;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Imperio {
    protected Mineral mineralesDelImperio;
    protected Gas gasDelImperio;
    protected Suministro poblacion;
    protected LinkedList<Edificio> edificios;
    protected FabricasDisponibles fabricasDisponibles;
    protected ArrayList<Unidad> unidades;
    protected int cantidadInicialDeMineral = 200;
    protected Jugador jugadorQueControlaImperio;

    public void terminarTurno(){
        revisarDestruccionDeEdificios();

        for (Edificio edificio : edificios)
            edificio.pasarTurno();

        for (Unidad unidad: unidades)
            unidad.pasarTurno();
    }

    private void revisarDestruccionDeEdificios() {
        LinkedList<Edificio> edificiosDestruidos = new LinkedList<>();

        for (Edificio edificio : edificios){
            if(edificio.estaDestruido())
                edificiosDestruidos.add(edificio);

        for (Edificio edificioDestruido : edificiosDestruidos)
            this.edificios.remove(edificioDestruido);
    }

    protected void construirEdificio(Edificio edificio, Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        edificio.modificarPoblacion(poblacion);
        comprobarRequisitosMateriales(edificio);
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void construirEdificioSinVerificacionesMateriales(Edificio edificio, Coordenada coordenada){
        Mapa mapa = Mapa.obtener();
        edificio.modificarPoblacion(poblacion);
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void comprobarRequisitosMateriales(Ocupable ocupable){
        ArrayList<Recurso> listaDeRequisitos = ocupable.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        Recurso gasAconsumir = listaDeRequisitos.get(1);

        if (mineralesDelImperio.tienesMasQue(mineralAConsumir) && gasDelImperio.tienesMasQue(gasAconsumir)){
            mineralesDelImperio.consumir(mineralAConsumir.obtenerCantidad());
            gasDelImperio.consumir(gasAconsumir.obtenerCantidad());
        } else {
            throw new ErrorCantidadDeRecursoInsuficiente();
        }
    }

    protected void comprobarRequisitos(ArrayList<Edificio> requisitos) {
        //Digase de los edificios que son prerequisitos de otro edificio
        int requisitosCumplidos = 0;
        for(Edificio requisito: requisitos){
            for(Edificio edificio: edificios) {
                if (edificio.getClass().equals(requisito.getClass())){
                    requisitosCumplidos++;
                    break;
                }
            }
        }

        if (requisitosCumplidos != requisitos.size())
            throw new ErrorNoSeCumplenLosPreRequisitosDelEdificio();
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

    public boolean tenesEsteSuministro(int unaCantidad){
        return ( unaCantidad == poblacion.obtenerSuministro() );
    }


    public void asignarJugadorAlImperio(Jugador unJugador){
        this.jugadorQueControlaImperio = unJugador;
    }

    public boolean partidaTerminada(){
        return (this.edificios.size() == 0);
    }

    public boolean tenesEstaPoblacion(int unaCantidad){
        return (unaCantidad == poblacion.obtenerPoblacion());
    }
}
