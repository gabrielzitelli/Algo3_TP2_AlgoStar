package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class ReservaDeReproduccion extends Edificio {

    private VidaRegenerativa vida;

    public ReservaDeReproduccion(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 12;
        this.nodoCompatible = requisitos;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(1000, 0.25);
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

    @Override
    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }
}
