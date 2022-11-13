package edu.fiuba.algo3.modelo.Imperio;

import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;

import java.util.LinkedList;

public class Zerg {

    private Recurso mineralesDelImperio;
    private Recurso gasDelImperio;

    private LinkedList<Criadero> criaderos = new LinkedList<>();

    public Zerg(){
        mineralesDelImperio = new Recurso(0);
        gasDelImperio = new Recurso(0);
    }


}
