package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Espiral extends Edificio {

    private VidaRegenerativa vida;
    public Espiral(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 10;
        this.nodoCompatible = requisitos;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(1350, 0.25);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        // TODO
    }

    public int getVida(){
        return this.vida.getVida();
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }
}
