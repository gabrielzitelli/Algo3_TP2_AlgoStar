package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Guardian extends UnidadZerg {

    public Guardian() {
        this.turnosDeConstruccion = 4;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.rangoDeAtaque = 10;
        int danioTerrestre = 25;
        this.danio = new DanioTerrestre(danioTerrestre);
        int cantidadDeVida = 100;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 50;
        this.costoMineral = 100;
        this.identificador = "guardian";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaMutalisco.obtenerPoblacionNecesaria());
    }
}
