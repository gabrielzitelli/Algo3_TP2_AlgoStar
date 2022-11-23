package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMutaliscoParaEvolucionar;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

import java.util.ArrayList;
import java.util.LinkedList;


public class Zerg extends Imperio{

    GestorDeCrianza gestorDeEvoluciones = new GestorDeCrianza();

    public Zerg(){
        mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        unidades = new ArrayList<>();
    }

    public void inicializarAsentamientoPrimerTurno(){
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaCriadero = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificioSinVerificacionesMateriales(unCriadero, coordenadaCriadero);
        unCriadero.construirInmediatamente();
    }

    public void construirCriadero(Coordenada coordenada){
        Criadero criadero = new Criadero();
        criadero.asignarListaDeUnidades(fabricasDisponibles);
        criadero.asignarListaDeUnidadesImperio(unidades);
        criadero.asignarRecursos(mineralesDelImperio, gasDelImperio);
        this.construirEdificio(criadero, coordenada);
    }

    public void construirReservaDeReproduccion(Coordenada coordenada){
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        reserva.asignarListaDeUnidades(fabricasDisponibles);
        this.construirEdificio(reserva, coordenada);
    }

    public void construirExtractor(Coordenada coordenada){
        Extractor extractor = new Extractor(gasDelImperio);
        this.construirEdificio(extractor, coordenada);
    }

    public void construirGuarida(Coordenada coordenada){
        this.comprobarRequisitos(Guarida.requisitos());
        Guarida guarida = new Guarida();
        guarida.asignarListaDeUnidades(fabricasDisponibles);
        this.construirEdificio(guarida, coordenada);
    }

    public void construirEspiral(Coordenada coordenada){
        this.comprobarRequisitos(Espiral.requisitos());
        Espiral espiral = new Espiral();
        espiral.asignarListaDeUnidades(fabricasDisponibles);
        this.construirEdificio(espiral, coordenada);
    }

    private void validarPreRequisitosDeEvolucionDeMutalisco(Unidad unaUnidad){
        //revisamos que tengamos al mutalisco
        if (!this.tieneUnidad(new Mutalisco())) {
            throw new ErrorNoHayMutaliscoParaEvolucionar();
        }
        //comprobamos los materiales
        this.comprobarRequisitosMateriales(unaUnidad);
    }
    public void evolucionarMutaliscoAGuardian(){
        Unidad guardian = new Guardian();
        validarPreRequisitosDeEvolucionDeMutalisco(guardian);
        gestorDeEvoluciones.agregarUnidad(guardian, unidades);
    }
    public void evolucionarMutaliscoADevorador() {
        Unidad devorador = new Devorador();
        //TODO falta sacar al mutalisco viejo que evoluciono
        //TODO deberia tener fabrica de devorador
        validarPreRequisitosDeEvolucionDeMutalisco(devorador);
        gestorDeEvoluciones.agregarUnidad(devorador , unidades);
    }
    @Override
    public void terminarTurno(){
        super.terminarTurno();
        gestorDeEvoluciones.actualizar();
    }

}
