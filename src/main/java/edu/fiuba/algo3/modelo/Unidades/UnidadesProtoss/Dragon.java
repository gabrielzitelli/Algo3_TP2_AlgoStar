package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;

public class Dragon extends UnidadProtoss {

    public Dragon() {
        this.turnosDeConstruccion = 6;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 4;
        int danioTerrestre = 20;
        int danioAereo = 20;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        int cantidadDeVida = 100;
        int cantidadDeEscudo = 80;
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 50;
        this.costoMineral = 125;
        this.identificador = "dragon";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaDragon.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Dragon.class.equals(claseAAverificar);
    }
}
