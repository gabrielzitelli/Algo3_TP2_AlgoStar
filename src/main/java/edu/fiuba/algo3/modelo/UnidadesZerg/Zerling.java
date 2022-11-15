package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

public class Zerling extends UnidadZerg {

    private int danioTerrestre = 4;

    private DanioUnidad danio = new DanioZerling(danioTerrestre);

    private Vida vida = new VidaSimple(35);

    public Ataque atacar(){
        return new Ataque(danio);
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }
}
