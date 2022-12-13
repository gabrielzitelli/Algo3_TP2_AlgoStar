package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.CasillaVacia;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;

public abstract class EdificioZerg extends Edificio {

    public boolean perteneceAImperio(Imperio imperio) {
        return imperio.getClass().equals(Zerg.class);
    }

    @Override
    public void verificarColocable(Casilla unaCasilla){
        super.verificarColocable(unaCasilla);
    }

    public boolean somosAliados(Unidad unaUnidad){
        return unaUnidad.perteneceAImperio(new Zerg());
    }

    @Override
    public void construirSobreCasillaOcupadaVerificacion(Ocupable ocupable, CasillaVacia copiaVaciaDeUnaCasilla){
        if(ocupable.esDeEsteTipo(Zangano.class))
            throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();

        copiaVaciaDeUnaCasilla.construirEdificioVerificacion(this);
    }
}
