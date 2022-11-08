package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;

public class Criadero {

    private int maxLarvas = 3;
    private int cantidadLarvas;
    public Criadero(NodoCompatible requisitos, Recurso minerales) {
        cantidadLarvas = maxLarvas;
    }

    public Zangano criarZangano() {
        if (cantidadLarvas == 0) {
            throw new CriaderoSinLarvas();
        }
        Zangano zangano = new Zangano(new Coordenadas(0,0));
        cantidadLarvas--;
        return zangano;
    }

    public void accionDeTurno() {
        aumentarLarvas();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas) cantidadLarvas++;
    }
}
