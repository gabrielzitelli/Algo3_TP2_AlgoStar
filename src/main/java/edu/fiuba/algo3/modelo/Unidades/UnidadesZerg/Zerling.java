package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Zerling extends UnidadZerg {

    private final int turnosDeContruccion = 2;
    private final int danioTerrestre = 4;
    private final int cantidadDeVida = 35;

    public Zerling() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.danio = new DanioZerling(danioTerrestre);
        this.vida = new VidaSimple(cantidadDeVida);
        this.rangoDeAtaque = 1;
        this.costoGas = 0;
        this.costoMineral = 25;
    }
}
