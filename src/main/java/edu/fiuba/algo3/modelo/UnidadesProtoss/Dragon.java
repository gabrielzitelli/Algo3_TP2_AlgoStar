package edu.fiuba.algo3.modelo.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.Danio;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioDragon;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioZerling;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

import java.util.ArrayList;

public class Dragon implements UnidadProtoss{
    private int danioBasico = 20;
    private Danio danio = new DanioDragon(danioBasico);

    private Vida vida = new VidaConEscudo(100, 80);

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
