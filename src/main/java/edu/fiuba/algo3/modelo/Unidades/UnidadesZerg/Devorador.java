package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioAereo;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Devorador extends UnidadZerg {
    private final int turnosDeContruccion = 4;
    private final int danioAereo = 15;
    private final int cantidadDeVida = 200;

    public Devorador() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.rangoDeAtaque = 5;
        this.danio = new DanioAereo(danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 50;
        this.costoMineral = 100;
        this.identificador = "devorador";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaMutalisco.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Devorador.class.equals(claseAAverificar);
    }
}
