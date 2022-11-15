package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.danioYAtaque.*;
import edu.fiuba.algo3.modelo.vida.Vida;
import edu.fiuba.algo3.modelo.vida.VidaSimple;

import java.util.ArrayList;

public class Mutalisco extends UnidadZerg {

    private final int costoGasEvolucion;
    private final int costoMineralEvolucion;

    private int danioTerrestre = 10;
    private int danioAereo = 10;

    private DanioUnidad danio = new DanioMutalisco(danioTerrestre, danioAereo);

    private Vida vida = new VidaSimple(120);

    public Mutalisco(){
        this.costoGasEvolucion = 100;
        this.costoMineralEvolucion = 50;
    }

    @Override
    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Recurso(costoMineralEvolucion));
        requisitosMateriales.add(new Recurso(costoGasEvolucion));
        return requisitosMateriales;
    }
    public Ataque atacar(){
        Ataque unAtaque = new Ataque(danio);
        return unAtaque;
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(unAtaque);
    }
}