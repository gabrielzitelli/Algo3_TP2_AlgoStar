package edu.fiuba.algo3.modelo.UnidadesProtoss;

import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

public class Scout implements UnidadProtoss{

    private int danioTerrestre = 8;
    private int danioAereo = 14;

    private DanioUnidad danio = new DanioScout(danioTerrestre, danioAereo);

    private Vida vida = new VidaConEscudo(150, 100);

    public Ataque atacar(){
        Ataque unAtaque = new Ataque(danio);
        return unAtaque;
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }
}
