package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.Danio;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioGuardian;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioMutalisco;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

import java.util.ArrayList;

public class Guardian extends UnidadZerg{
    private int danioBasico = 25;
    private Danio danio = new DanioGuardian(danioBasico);

    private Vida vida = new VidaSimple(100);

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
