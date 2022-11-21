package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.ArrayList;
import java.util.LinkedList;

public class Protoss extends Imperio{

    public Protoss() {
        this.gasDelImperio = new Gas(0);
        this.mineralesDelImperio = new Mineral(0);
        this.edificios = new LinkedList<>();
        this.fabricasDisponibles = new FabricasDisponibles();
        this.unidades = new ArrayList<>();
    }

    public void construirPuertoEstelar(Coordenada coordenada) {
        this.comprobarRequisitos(PuertoEstelar.requisitos());
        PuertoEstelar puertoEstelar = new PuertoEstelar();
        puertoEstelar.asignarListaDeUnidades(fabricasDisponibles);
        puertoEstelar.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificio(puertoEstelar, coordenada);
    }

    public void construirPilon(Coordenada coordenada) {
        Pilon pilon = new Pilon();
        this.construirEdificio(pilon, coordenada);
    }

    public void construirAcceso(Coordenada coordenada) {
        Acceso acceso = new Acceso();
        acceso.asignarListaDeUnidades(fabricasDisponibles);
        acceso.asignarListaDeUnidadesImperio(unidades);
        this.construirEdificio(acceso, coordenada);
    }

    public void construirNexoMineral(Coordenada coordenada) {
        NexoMineral nexoMineral = new NexoMineral(this.mineralesDelImperio);
        this.construirEdificio(nexoMineral, coordenada);
    }

    public void construirAsimilador(Coordenada coordenada) {
        Asimilador asimilador = new Asimilador(this.gasDelImperio);
        this.construirEdificio(asimilador, coordenada);
    }
}
