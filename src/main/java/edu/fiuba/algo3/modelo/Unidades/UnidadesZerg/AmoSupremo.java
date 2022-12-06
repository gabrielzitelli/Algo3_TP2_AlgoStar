package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaAmoSupremo;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class AmoSupremo extends UnidadZerg {

    private final int turnosDeContruccion = 5;
    private final int cantidadDeVida = 200;
    private final int radioDeRevelacion = 4;

    public AmoSupremo() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.vida = new VidaSimple(cantidadDeVida);
        this.costoGas = 0;
        this.costoMineral = 50;
        this.identificador = "amo_supremo";
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
        Mapa.obtener().revelar(coordenada, radioDeRevelacion);
    }

    @Override
    public void actualizarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
     }

    @Override
    public void moverA(Coordenada coordenadaDestino) {
        super.moverA(coordenadaDestino);
        Mapa.obtener().desRevelar(coordenada, radioDeRevelacion);
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaAmoSupremo.obtenerPoblacionNecesaria());
        suministroImperio.disminuirSuministro(FabricaAmoSupremo.obtenerSuministroAportado());
    }
}
