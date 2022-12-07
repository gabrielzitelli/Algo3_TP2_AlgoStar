package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaScout;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Scout extends UnidadProtoss {

    private final int turnosDeContruccion = 9;
    private final int danioTerrestre = 8;
    private final int danioAereo = 14;
    private final int cantidadDeVida = 100;
    private final int cantidadDeEscudo = 80;

    public Scout(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.rangoDeAtaque = 4;
        this.costoGas = 150;
        this.costoMineral = 300;
        this.identificador = "scout";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaScout.obtenerPoblacionNecesaria());
    }
}
