package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Guardian extends UnidadZerg {

    private final int turnosDeContruccion = 4;
    private final int danioTerrestre = 25;
    private final int cantidadDeVida = 100;

    public Guardian() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioGuardian(danioTerrestre);
        this.vida = new VidaSimple(cantidadDeVida);
        this.rangoDeAtaque = 10;
        this.costoGas = 50;
        this.costoMineral = 100;
        this.identificador = "guardian";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaMutalisco.obtenerPoblacionNecesaria());
    }
}
