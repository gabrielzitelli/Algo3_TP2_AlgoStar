package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioAmoSupremo;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class AmoSupremo extends UnidadZerg {

    private final int turnosDeContruccion = 5;
    private final int cantidadDeVida = 200;

    public AmoSupremo() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioAmoSupremo();
        this.vida = new VidaSimple(cantidadDeVida);
    }

    @Override
    public void pasarTurno() {
        super.pasarTurno();


    }
}
