package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.DanioUnidad;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

public class NoAtacante implements Atacar{

    @Override
    public void atacar(Casilla casillaAAtacar, DanioUnidad unDanio){
        //NADA
    }
}