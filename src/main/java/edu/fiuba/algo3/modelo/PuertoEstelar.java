package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class PuertoEstelar extends Edificio {

    private VidaConEscudo vida;

    public PuertoEstelar(NodoCompatible requisitos, Coordenadas ubicacion) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 10;
        this.nodoCompatible = requisitos;
        this.posicion = ubicacion;
        this.vida = new VidaConEscudo(600, 0.25, 600);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        // TODO
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }
}
