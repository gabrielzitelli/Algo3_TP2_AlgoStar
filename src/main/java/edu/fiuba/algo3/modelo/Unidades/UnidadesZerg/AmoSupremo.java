package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaAmoSupremo;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaSimple;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;

public class AmoSupremo extends UnidadZerg {

    private final int radioDeRevelacion = 4;

    public AmoSupremo() {
        this.turnosDeConstruccion = 5;
        this.superficieDondeSeMueve = new SuperficieAerea();
        int cantidadDeVida = 200;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 0;
        this.costoMineral = 50;
        this.identificador = "amo_supremo";
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        if (unaCasilla.puedeMoverse(superficieDondeSeMueve))
            throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();

        Mapa elMapa = Mapa.obtener();
        if (coordenada != null)
            elMapa.desRevelar(coordenada, radioDeRevelacion);

        this.coordenada = unaCasilla.obtenerCoordenada();
        elMapa.revelar(coordenada, radioDeRevelacion);
    }

    @Override
    public void actualizarColocable(Casilla unaCasilla) {}

    @Override
    public void destruirUnidad() {
        super.destruirUnidad();
        Mapa.obtener().desRevelar(coordenada, radioDeRevelacion);
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaAmoSupremo.obtenerPoblacionNecesaria());
        suministroImperio.disminuirSuministro(FabricaAmoSupremo.obtenerSuministroAportado());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return !AmoSupremo.class.equals(claseAAverificar);
    }
}
