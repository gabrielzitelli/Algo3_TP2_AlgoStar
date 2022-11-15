package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.danioYAtaque.Danio;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioZerling;

public class Zerling extends UnidadZerg {

    private int danioBasico = 4;
    private Danio danio = new DanioZerling(danioBasico);

    public void atacar(Coordenada coordenadaDeAtaque){
        Mapa elMapa = Mapa.obtener();

        elMapa.atacar(danio, coordenadaDeAtaque);
    }
}
