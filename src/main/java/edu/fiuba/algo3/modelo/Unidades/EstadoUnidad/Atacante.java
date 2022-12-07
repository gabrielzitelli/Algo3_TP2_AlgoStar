package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;

public class Atacante implements Atacar{

    @Override
    public void atacar(Casilla casillaAAtacar, TipoDanio unDanio){
        casillaAAtacar.recibirAtaque(new Ataque(unDanio));
    }
}
