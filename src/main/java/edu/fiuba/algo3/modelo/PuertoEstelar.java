package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

import java.util.ArrayList;

public class PuertoEstelar extends Edificio {

    private VidaConEscudo vida;

    public PuertoEstelar(NodoCompatible requisitos) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 10;
        this.nodoCompatible = requisitos;
        this.vida = new VidaConEscudo(600, 0.25, 600);
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Acceso(new NodoCompatible()));
        return requisitos;
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
