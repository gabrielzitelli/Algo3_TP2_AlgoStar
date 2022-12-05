package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Recolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Zangano extends UnidadZerg {

    private final int turnosDeContruccion = 1;
    private final int cantidadDeVida = 25;
    private Recolectable recolecta = new MineralRecolectable();

    public Zangano(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.mineralDelImperio = null;
        this.vida = new VidaSimple(cantidadDeVida);
        this.rangoDeAtaque = 0;
        this.costoGas = 0;
        this.costoMineral = 25;
        this.identificador = "zangano";
    }

    @Override
    public void interaccionar(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(recolecta);
        this.recursoARecolectar = unaCasilla.obtenerMaterial();
    }

    @Override
    public void setDepositoRecurso( Recurso recursoImperio ) {
        this.mineralDelImperio = recursoImperio;
    }

    public void extraer(){
        mineralDelImperio.depositar(recursoARecolectar.extraer(10));
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaZangano.obtenerPoblacionNecesaria());
    }
}