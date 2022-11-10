package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Pilon extends Edificio {

    private VidaConEscudo vida;

    public Pilon(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 5;
        this.nodoCompatible = requisitos;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaConEscudo(300, 0.25, 300);
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
