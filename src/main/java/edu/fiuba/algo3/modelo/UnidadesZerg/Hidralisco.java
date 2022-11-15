package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

import java.util.ArrayList;

public class Hidralisco extends UnidadZerg {

    private int danioTerrestre = 10;
    private int danioAereo = 10;
    private DanioUnidad danio = new DanioHidralisco(danioTerrestre, danioAereo);

    private Vida vida = new VidaSimple(80);

    public Hidralisco(){
        superficieDondeSeMueve.add(new SuperficieTerrestre());
    }

    public Ataque atacar(){
        Ataque unAtaque = new Ataque(danio);
        return unAtaque;
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        return requisitosMateriales;
    }
}
