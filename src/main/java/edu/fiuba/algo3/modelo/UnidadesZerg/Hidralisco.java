package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

public class Hidralisco extends UnidadZerg {

    private int danioTerrestre = 10;
    private int danioAereo = 10;
    private DanioUnidad danio = new DanioHidralisco(danioTerrestre, danioAereo);

    private Vida vida = new VidaSimple(80);

    public Ataque atacar(){
        Ataque unAtaque = new Ataque(danio);
        return unAtaque;
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }
}
