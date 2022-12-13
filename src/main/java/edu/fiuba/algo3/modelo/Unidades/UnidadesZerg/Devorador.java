package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioAereo;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesMutalisco;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaSimple;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;

public class Devorador extends UnidadZerg {

    public Devorador() {
        this.turnosDeConstruccion = 4;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.rangoDeAtaque = 5;
        int danioAereo = 15;
        this.danio = new DanioAereo(danioAereo);
        int cantidadDeVida = 200;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 50;
        this.costoMineral = 100;
        this.identificador = "devorador";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricasUnidadesMutalisco.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return !Devorador.class.equals(claseAAverificar);
    }
}
