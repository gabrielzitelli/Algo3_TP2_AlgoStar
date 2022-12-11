package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Zerling extends UnidadZerg {

    private final int turnosDeContruccion = 2;
    private final int danioTerrestre = 4;
    private final int cantidadDeVida = 35;

    public Zerling() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 1;
        this.danio = new DanioTerrestre(danioTerrestre);
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 0;
        this.costoMineral = 25;
        this.rangoDeAtaque = 1;
        this.identificador = "zerling";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaZerling.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Zerling.class.equals(claseAAverificar);
    }
}
