package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricaDragon;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Dragon extends UnidadProtoss {

    private final int turnosDeContruccion = 6;
    private final int danioTerrestre = 20;
    private final int danioAereo = 20;
    private final int cantidadDeVida = 100;
    private final int cantidadDeEscudo = 80;

    public Dragon() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 4;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 50;
        this.costoMineral = 125;
        this.identificador = "dragon";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaDragon.obtenerPoblacionNecesaria());
    }
}
