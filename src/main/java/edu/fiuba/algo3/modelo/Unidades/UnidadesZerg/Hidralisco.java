package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Hidralisco extends UnidadZerg {

    private int danioTerrestre = 10;
    private int danioAereo = 10;
    private DanioUnidad danio = new DanioHidralisco(danioTerrestre, danioAereo);
    private Vida vida = new VidaSimple(80);

    public Hidralisco(){
        superficieDondeSeMueve = new SuperficieTerrestre();
        this.turnosDeConstruccion = 4;
    }

    public Ataque atacar(){
        return new Ataque(danio);
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(superficieDondeSeMueve.conseguirTipoDeAtaque(unAtaque));
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        return requisitosMateriales;
    }
}
