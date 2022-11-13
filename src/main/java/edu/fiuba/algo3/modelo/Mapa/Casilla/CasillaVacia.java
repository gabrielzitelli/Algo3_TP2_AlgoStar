package edu.fiuba.algo3.modelo.Mapa.Casilla;

import edu.fiuba.algo3.modelo.Edificio;

public class CasillaVacia extends Casilla{

    public CasillaVacia(){
        estadoRecolectable = new NoRecolectable();
        estadoMoho = new SinMoho();
        estadoCarga = new SinCarga();
    }

    public Casilla construirEdificio(Edificio unEdificio){

        unEdificio.verificarConstruccion(this);

        return new CasillaOcupada();
    }
}
