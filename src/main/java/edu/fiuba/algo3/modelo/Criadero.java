package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Criadero extends Edificio {

    private Recurso minerales;
    private Imperio zergs;
    private final int maxLarvas = 3;
    private int cantidadLarvas;
    private NodoCompatible nodoCompatible;
    private VidaRegenerativa vida;

    public Criadero(NodoCompatible requisitos, Recurso _minerales, Imperio _zergs) {
        this.nodoCompatible = requisitos;
        this.minerales = _minerales;
        this.zergs = _zergs;
        cantidadLarvas = maxLarvas;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(500, 0.25);
        nodoCompatible = requisitos;
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 4;
    }

    public Zangano criarZangano() {
        this.estaActiva();
        
        if (cantidadLarvas == 0)
            throw new CriaderoSinLarvas();

        cantidadLarvas--;
        return new Zangano(new Coordenadas(0,0), minerales);
    }

    public void accionDeTurno() {
        turnosExistiendo ++;
        aumentarLarvas();
        this.vida.accionDeTurno();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }
    @Override
    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }
}

