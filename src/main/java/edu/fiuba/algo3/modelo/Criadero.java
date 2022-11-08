package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;

public class Criadero extends Edificio {

    private final int maxLarvas = 3;
    private final int costoMineral = 50;
    private int cantidadLarvas;
    private NodoCompatible ubicacion;

    public Criadero(NodoCompatible ubicacion, Recurso mineral) {
        mineral.consumir(costoMineral);
        cantidadLarvas = maxLarvas;
        this.ubicacion = ubicacion;
    }

    public Zangano criarZangano() {
        if (cantidadLarvas == 0)
            throw new CriaderoSinLarvas();

        cantidadLarvas--;
        return new Zangano(new Coordenadas(0,0));
    }

    public void accionDeTurno() {
        aumentarLarvas();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }
}
