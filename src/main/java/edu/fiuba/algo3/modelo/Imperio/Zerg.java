package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEstaUnidadYaEstaEvolucionando;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMutaliscoParaEvolucionar;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadEnEvolucion;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

import java.util.ArrayList;
import java.util.LinkedList;


public class Zerg extends Imperio {

    final LinkedList<GestorDeCrianza> gestoresDeEvoluciones = new LinkedList<>();
    final LinkedList<UnidadEnEvolucion> unidadesAEvolucionar = new LinkedList<>();

    public Zerg() {
        mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        unidades = new ArrayList<>();
        this.identificador = "zerg";
    }

    public void inicializarAsentamientoPrimerTurno() {
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaCriadero = new Coordenada(coordenadaBase.getCoordenadaX() - 2, coordenadaBase.getCoordenadaY());

        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificioSinVerificacionesMateriales(unCriadero, coordenadaCriadero);
        unCriadero.asignarRecursos(mineralesDelImperio, gasDelImperio);
        unCriadero.construirInmediatamente();
    }

    public void construirEdificio(FabricaEdificio fabricaEdificio, Coordenada coordenada) {
        fabricaEdificio.asignar(fabricasDisponibles, unidades, mineralesDelImperio, gasDelImperio, edificios);
        this.verificarZangano(coordenada);
        Edificio edificioACrear = fabricaEdificio.crear();
        this.construirEdificio(edificioACrear, coordenada);
        Logger.obtener().log("Se ha iniciado la construcción de un edificio " + edificioACrear.getClass().getSimpleName() +
                " en la casilla [X: " + coordenada.getCoordenadaX() + ", Y: " + coordenada.getCoordenadaY() + "].");
    }

    private void validarPreRequisitosDeEvolucionDeMutalisco(Unidad unidadEvolucionada, Unidad unidadAEvolucionar){
        if (!unidadAEvolucionar.esIgualA(new Mutalisco())) {
            throw new ErrorNoHayMutaliscoParaEvolucionar();
        }
        //comprobamos los materiales
        this.comprobarRequisitosMateriales(unidadEvolucionada);

        ((Mutalisco) unidadAEvolucionar).evolucionar();
        unidadesAEvolucionar.add(new UnidadEnEvolucion(unidadAEvolucionar, unidadEvolucionada));
    }

    public void evolucionarMutaliscoAGuardian(Unidad unidad){
        Unidad guardian = new Guardian();
        validarPreRequisitosDeEvolucionDeMutalisco(guardian, unidad);
    }

    public void evolucionarMutaliscoADevorador(Unidad unidad) {
        Unidad devorador = new Devorador();
        validarPreRequisitosDeEvolucionDeMutalisco(devorador, unidad);
    }

    @Override
    public void terminarTurno(){
        super.terminarTurno();
        for(GestorDeCrianza gestorDeEvoluciones : gestoresDeEvoluciones) {
            gestorDeEvoluciones.actualizar();
        }
        for(UnidadEnEvolucion unidadEnEvolucion: unidadesAEvolucionar) {
            unidadEnEvolucion.pasarTurno();

            if (unidadEnEvolucion.unidadYaEvoluciono()) {
                unidades.add(unidadEnEvolucion.obtenerUnidad());
                unidadesAEvolucionar.remove(unidadEnEvolucion);
            }
        }
    }

    private void verificarZangano(Coordenada coordenada) {
        Mapa elMapa = Mapa.obtener();
        if (elMapa.tieneEsteOcupable(new Zangano(), coordenada))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();

        ((Unidad) elMapa.obtenerOcupable(coordenada)).destruirUnidad();
        this.revisarDestruccionDeUnidades();
    }

    @Override
    public void prepararParaRevancha(){
        mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        unidades = new ArrayList<>();
    }

    public void estaAptoParaEvolucionarA(Unidad unidadEvolucionada, Unidad unidadAEvolucionar) {
        if (((Mutalisco) unidadAEvolucionar).yaEvoluciono())
            throw new ErrorEstaUnidadYaEstaEvolucionando();

        ArrayList<Recurso> listaDeRequisitos = unidadEvolucionada.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        Recurso gasAconsumir = listaDeRequisitos.get(1);

        if (!mineralesDelImperio.tienesMasQue(mineralAConsumir) || !gasDelImperio.tienesMasQue(gasAconsumir))
            throw new ErrorCantidadDeRecursoInsuficiente();
    }

    private void verificarZanganoSoloComprobacion(Coordenada coordenada){
        Mapa elMapa = Mapa.obtener();
        if (elMapa.tieneEsteOcupable(new Zangano(), coordenada))
            throw new ErrorEdificioNoSePuedeConstruirEnEstaCasilla();
    }

    public void verificarConstruccionDeEdificio(Edificio unEdificio, Coordenada coordenada){
        comprobarRequisitosMaterialesVerificacion(unEdificio);
        verificarZanganoSoloComprobacion(coordenada);
        Mapa.obtener().construirEdificioVerificacion(unEdificio, coordenada);
        this.comprobarRequisitos(unEdificio.obtenerRequisitosEdilicios());
    }
}
