package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

import java.util.ArrayList;
import java.util.LinkedList;

public class Protoss extends Imperio{

    public Protoss() {
        this.mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        this.gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        this.edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        this.unidades = new ArrayList<>();
        this.identificador = "protoss";
    }

    public void inicializarAsentamientoPrimerTurno(){
        Mapa elMapa = Mapa.obtener();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAcceso = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());
        Coordenada coordenadaPilon = new Coordenada(coordenadaBase.getCoordenadaX() -3, coordenadaBase.getCoordenadaY()-1);

        Pilon unPilon = new Pilon();
        this.construirEdificioSinVerificacionesMateriales(unPilon, coordenadaPilon);
        unPilon.construirInmediatamente();

        Acceso unAcceso = new Acceso();
        unAcceso.asignarListaDeUnidades(fabricasDisponibles);
        unAcceso.asignarListaDeUnidadesImperio(unidades);
        unAcceso.asignarRecursos(mineralesDelImperio, gasDelImperio);
        this.construirEdificioSinVerificacionesMateriales(unAcceso, coordenadaAcceso);
        unAcceso.construirInmediatamente();
    }

    public void construirPuertoEstelar(Coordenada coordenada) {
        this.comprobarRequisitos(PuertoEstelar.requisitos());
        PuertoEstelar puertoEstelar = new PuertoEstelar();
        puertoEstelar.asignarListaDeUnidades(fabricasDisponibles);
        puertoEstelar.asignarListaDeUnidadesImperio(unidades);
        puertoEstelar.asignarRecursos(mineralesDelImperio, gasDelImperio);
        this.comprobarRequisitosMateriales(puertoEstelar);
        this.construirEdificio(puertoEstelar, coordenada);
    }

    public void construirPilon(Coordenada coordenada) {
        Pilon pilon = new Pilon();
        this.comprobarRequisitosMateriales(pilon);
        this.construirEdificio(pilon, coordenada);
    }

    public void construirAcceso(Coordenada coordenada) {
        Acceso acceso = new Acceso();
        acceso.asignarListaDeUnidades(fabricasDisponibles);
        acceso.asignarListaDeUnidadesImperio(unidades);
        acceso.asignarRecursos(mineralesDelImperio, gasDelImperio);
        this.comprobarRequisitosMateriales(acceso);
        this.construirEdificio(acceso, coordenada);
    }

    public void construirNexoMineral(Coordenada coordenada) {
        NexoMineral nexoMineral = new NexoMineral(this.mineralesDelImperio);
        this.comprobarRequisitosMateriales(nexoMineral);
        this.construirEdificio(nexoMineral, coordenada);
    }

    public void construirAsimilador(Coordenada coordenada) {
        Asimilador asimilador = new Asimilador(this.gasDelImperio);
        this.comprobarRequisitosMateriales(asimilador);
        this.construirEdificio(asimilador, coordenada);
    }

    @Override
    public void prepararParaRevancha(){
        this.mineralesDelImperio = new Mineral(cantidadInicialDeMineral);
        this.gasDelImperio = new Gas(0);
        this.poblacion = new Suministro(0);
        this.edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        this.unidades = new ArrayList<>();
    }

    public void verificarConstruccionDeEdificio(Edificio unEdificio, Coordenada coordenada){
        comprobarRequisitosMaterialesVerificacion(unEdificio);
        Mapa.obtener().construirEdificioVerificacion(unEdificio, coordenada);
    }
}
