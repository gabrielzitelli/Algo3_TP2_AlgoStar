package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEdificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

import java.util.ArrayList;
import java.util.LinkedList;

public class Protoss extends Imperio{

    public Protoss() {
        this.mineralesDelImperio = new Mineral(2000);
        this.gasDelImperio = new Gas(2000);
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

    public void construirEdificio(FabricaEdificio fabricaEdificio, Coordenada coordenada) {
        fabricaEdificio.asignar(fabricasDisponibles, unidades, mineralesDelImperio, gasDelImperio, edificios);

        this.construirEdificio(fabricaEdificio.crear(), coordenada);
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
