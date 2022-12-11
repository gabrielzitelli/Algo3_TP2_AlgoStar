package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaHidralisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaSimple;

public class Hidralisco extends UnidadZerg {

    public Hidralisco(){
        this.turnosDeConstruccion = 4;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 4;
        int danioTerrestre = 10;
        int danioAereo = 10;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        int cantidadDeVida = 80;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 25;
        this.costoMineral = 75;
        this.identificador = "hidralisco";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaHidralisco.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Hidralisco.class.equals(claseAAverificar);
    }
}
