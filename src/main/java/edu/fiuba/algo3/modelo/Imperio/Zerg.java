package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;

import java.util.ArrayList;
import java.util.LinkedList;


public class Zerg extends Imperio{

    public Zerg(){
        mineralesDelImperio = new Recurso(0);
        gasDelImperio = new Recurso(0);
        edificios = new LinkedList<>();
        listaDeFabricasDisponibles = new ArrayList<>();
        listaDeFabricasDisponibles.add(new FabricaZangano());
        unidades = new ArrayList<>();
    }

    public void construirCriadero(Coordenada coordenada){
        Criadero criadero = new Criadero();
        criadero.asignarListaDeUnidades(listaDeFabricasDisponibles);
        criadero.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificio(criadero, coordenada);
    }

    public void construirReservaDeReproduccion(Coordenada coordenada){
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        reserva.asignarListaDeUnidades(listaDeFabricasDisponibles);
        this.construirEdificio(reserva, coordenada);
    }

    public void construirExtractor(Coordenada coordenada){
        Extractor extractor = new Extractor(gasDelImperio);
        this.construirEdificio(extractor, coordenada);
    }

    public void construirGuarida(Coordenada coordenada){
        this.comprobarRequisitos(Guarida.requisitos());
        Guarida guarida = new Guarida();
        guarida.asignarListaDeUnidades(listaDeFabricasDisponibles);
        this.construirEdificio(guarida, coordenada);
    }

    public void construirEspiral(Coordenada coordenada){
        this.comprobarRequisitos(Espiral.requisitos());
        Espiral espiral = new Espiral();
        espiral.asignarListaDeUnidades(listaDeFabricasDisponibles);
        this.construirEdificio(espiral, coordenada);
    }

    public void evolucionarMutaliscoAGuardian(Coordenada coordenada){
        //TODO Esta raro pasarle el mutalisco aca
        Mutalisco mutalisco = new Mutalisco();
        this.construirUnidad(mutalisco, coordenada);
    }
}
