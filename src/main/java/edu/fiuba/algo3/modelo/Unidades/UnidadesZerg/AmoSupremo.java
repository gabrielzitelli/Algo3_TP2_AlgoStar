package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioAmoSupremo;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class AmoSupremo extends UnidadZerg {

    private final int turnosDeContruccion = 5;
    private final int cantidadDeVida = 200;
    private final int radioDeRevelacion = 4;

    public AmoSupremo() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioAmoSupremo();
        this.vida = new VidaSimple(cantidadDeVida);
        this.costoGas = 0;
        this.costoMineral = 50;
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
        Mapa.obtener().revelar(coordenada, radioDeRevelacion);
    }
}
