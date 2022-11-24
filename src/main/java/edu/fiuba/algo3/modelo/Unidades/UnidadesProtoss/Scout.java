package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class Scout extends UnidadProtoss {

    private final int turnosDeContruccion = 9;
    private final int danioTerrestre = 8;
    private final int danioAereo = 14;
    private final int cantidadDeVida = 100;
    private final int cantidadDeEscudo = 80;

    public Scout(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioScout(danioTerrestre, danioAereo);
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.rangoDeAtaque = 4;
    }
}
