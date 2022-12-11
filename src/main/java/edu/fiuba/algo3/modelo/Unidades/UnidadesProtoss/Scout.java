package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaScout;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Scout extends UnidadProtoss {

    public Scout(){
        this.turnosDeConstruccion = 9;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.rangoDeAtaque = 4;
        int danioTerrestre = 8;
        int danioAereo = 14;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        int cantidadDeVida = 100;
        int cantidadDeEscudo = 80;
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 150;
        this.costoMineral = 300;
        this.identificador = "scout";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaScout.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Scout.class.equals(claseAAverificar);
    }
}
