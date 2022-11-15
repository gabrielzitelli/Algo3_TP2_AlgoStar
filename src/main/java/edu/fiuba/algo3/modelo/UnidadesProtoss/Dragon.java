package edu.fiuba.algo3.modelo.UnidadesProtoss;

import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

public class Dragon implements UnidadProtoss {

    private int danioTerrestre = 20;
    private int danioAereo = 20;

    private DanioUnidad danio = new DanioDragon(danioTerrestre, danioAereo);

    private Vida vida = new VidaConEscudo(100, 80);

    public Ataque atacar(){
        Ataque unAtaque = new Ataque(danio);
        return unAtaque;
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }
}
