package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Acceso extends Edificio {

    private VidaConEscudo vida;

    public Acceso(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 8;
        this.nodoCompatible = requisitos;
        this.vida = new VidaConEscudo(500, 0.25, 500);
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
