package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.EdificioProtoss.Asimilador;
import edu.fiuba.algo3.modelo.EdificioProtoss.Pilon;
import edu.fiuba.algo3.modelo.EdificioProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

import java.util.LinkedList;

public class Protoss extends Imperio{
    public Protoss() {
        this.gasDelImperio = new Recurso(0);
        this.mineralesDelImperio = new Recurso(0);
        this.edificios = new LinkedList<>();
    }

    public void construirPuertoEstelar(Coordenada coordenada) {
        this.comprobarRequisitos(PuertoEstelar.requisitos());
        PuertoEstelar puertoEstelar = new PuertoEstelar();
        this.construirEdificio(puertoEstelar, coordenada);
    }

    public void construirPilon(Coordenada coordenada) {
        Pilon pilon = new Pilon();
        this.construirEdificio(pilon, coordenada);
    }

    public void construirAcceso(Coordenada coordenada) {
        Acceso acceso = new Acceso();
        this.construirEdificio(acceso, coordenada);
    }
    public void construirAsimilador(Coordenada coordenada) {
        Asimilador asimilador = new Asimilador(gasDelImperio);
        this.construirEdificio(asimilador, coordenada);
    }
}
