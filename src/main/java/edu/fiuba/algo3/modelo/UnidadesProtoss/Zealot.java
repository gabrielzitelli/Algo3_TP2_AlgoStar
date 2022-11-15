package edu.fiuba.algo3.modelo.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

import java.util.ArrayList;

public class Zealot extends UnidadProtoss {

    private int danioTerrestre = 8;

    private DanioUnidad danio = new DanioZealot(danioTerrestre);

    private Vida vida = new VidaConEscudo(100, 60);

    public Zealot(){
        this.turnosDeConstruccion = 4;
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
