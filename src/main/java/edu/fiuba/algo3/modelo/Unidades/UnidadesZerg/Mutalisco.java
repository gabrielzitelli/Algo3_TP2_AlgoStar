package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.DanioMixto;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Mutalisco extends UnidadZerg {

    private boolean estadoEvolucion = false;

    public Mutalisco() {
        this.turnosDeConstruccion = 7;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.rangoDeAtaque = 3;
        int danioTerrestre = 9;
        int danioAereo = 9;
        this.danio = new DanioMixto(danioTerrestre, danioAereo);
        int cantidadDeVida = 120;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 100;
        this.costoMineral = 100;
        this.identificador = "mutalisco";
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaMutalisco.obtenerPoblacionNecesaria());
    }

    public void evolucionar(){
        estadoEvolucion = true;
    }

    public boolean yaEvoluciono() {
        return estadoEvolucion;
    }
    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Mutalisco.class.equals(claseAAverificar);
    }
}