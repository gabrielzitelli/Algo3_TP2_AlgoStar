package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Mutalisco extends UnidadZerg {

    private final int turnosDeContruccion = 7;
    private final int danioTerrestre = 9;
    private final int danioAereo = 9;
    private final int cantidadDeVida = 120;

    public Mutalisco() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioMutalisco(danioTerrestre, danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);

        this.rangoDeAtaque = 3;
        this.costoGas = 100;
        this.costoMineral = 100;
        this.identificador = "mutalisco";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaMutalisco.obtenerPoblacionNecesaria());
    }

}