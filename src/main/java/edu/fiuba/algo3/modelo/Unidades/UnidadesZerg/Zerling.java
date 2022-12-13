package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaSimple;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;

public class Zerling extends UnidadZerg {

    public Zerling() {
        this.turnosDeConstruccion = 2;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 1;
        int danioTerrestre = 4;
        this.danio = new DanioTerrestre(danioTerrestre);
        int cantidadDeVida = 35;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 0;
        this.costoMineral = 25;
        this.identificador = "zerling";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaZerling.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return !Zerling.class.equals(claseAAverificar);
    }
}
