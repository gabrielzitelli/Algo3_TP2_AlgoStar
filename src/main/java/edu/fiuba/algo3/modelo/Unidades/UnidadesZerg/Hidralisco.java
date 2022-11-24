package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Hidralisco extends UnidadZerg {

    private final int turnosDeContruccion = 4;
    private final int danioTerrestre = 10;
    private final int danioAereo = 10;
    private final int cantidadDeVida = 80;

    public Hidralisco(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.danio = new DanioHidralisco(danioTerrestre, danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);
        this.rangoDeAtaque = 4;
        this.costoGas = 25;
        this.costoMineral = 75;
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaHidralisco.obtenerPoblacionNecesaria());
    }
}
