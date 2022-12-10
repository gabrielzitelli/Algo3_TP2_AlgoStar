package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Hidralisco extends UnidadZerg {

    private final int turnosDeContruccion = 4;
    private final int danioTerrestre = 10;
    private final int danioAereo = 10;
    private final int cantidadDeVida = 80;

    public Hidralisco(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 4;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 25;
        this.costoMineral = 75;
        this.identificador = "hidralisco";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaHidralisco.obtenerPoblacionNecesaria());
    }
}
