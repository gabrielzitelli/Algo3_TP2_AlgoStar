package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
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
    protected String identificador;
    private Mapa mapa = Mapa.obtener();

    public String toString() {
        String info = identificador;
        info += " mineral " + mineralesDelImperio.obtenerCantidad();
        info += " gas " + gasDelImperio.obtenerCantidad();
        info += " poblacion " + poblacion.obtenerPoblacion();
        info += " suministro " + poblacion.obtenerSuministro();
        return info;
    }

    public abstract void inicializarAsentamientoPrimerTurno();

    public void terminarTurno(){
        revisarDestrucciones();
        for (Edificio edificio : edificios)
            edificio.pasarTurno();

        for (Unidad unidad: unidades)
            unidad.pasarTurno();
    }

    public void revisarDestrucciones() {
        revisarDestruccionDeEdificios();
        revisarDestruccionDeUnidades();
    }

    private void revisarDestruccionDeEdificios() {
        LinkedList<Edificio> edificiosDestruidos = new LinkedList<>();

        for (Edificio edificio : edificios) {
            if (edificio.estaDestruido())
                edificiosDestruidos.add(edificio);
        }
        for (Edificio edificioDestruido : edificiosDestruidos) {
            this.edificios.remove(edificioDestruido);
        }
    }


    private void revisarDestruccionDeUnidades() {
        LinkedList<Unidad> unidadesAsesinadas = new LinkedList<>();

        for (Unidad unidad : unidades) {
            if (unidad.estaMuerta())
                unidadesAsesinadas.add(unidad);
        }
        for (Unidad unidadAsesinada : unidadesAsesinadas) {
            unidadAsesinada.disminuirPoblacion(poblacion);
            this.unidades.remove(unidadAsesinada);
        }
    }

    protected void construirEdificio(Edificio edificio, Coordenada coordenada){
        edificio.modificarPoblacion(poblacion);
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void construirEdificioSinVerificacionesMateriales(Edificio edificio, Coordenada coordenada){
        edificio.modificarPoblacion(poblacion);
        mapa.construirEdificio(edificio, coordenada);
        edificios.add(edificio);
    }

    protected void  comprobarRequisitosMateriales(Ocupable ocupable){
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

    protected void  comprobarRequisitosMaterialesVerificacion(Ocupable ocupable){
        ArrayList<Recurso> listaDeRequisitos = ocupable.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        Recurso gasAconsumir = listaDeRequisitos.get(1);

        if (!mineralesDelImperio.tienesMasQue(mineralAConsumir) || !gasDelImperio.tienesMasQue(gasAconsumir))
            throw new ErrorCantidadDeRecursoInsuficiente();
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

    public boolean partidaTerminada(){
        return (this.edificios.size() == 0);
    }

    public boolean tenesEstaPoblacion(int unaCantidad){

        return (unaCantidad == poblacion.obtenerPoblacion());
    }

    /**
     * Provisorio porque implementar algoritmo del mapa para obtener unidad como obtener Edificio
     */
    public ArrayList<Unidad> dameLaListaUnidades(){
        return unidades;
    }

    public abstract void prepararParaRevancha();
}
