package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Zerling extends UnidadZerg {

    private int danioTerrestre = 4;
    private DanioUnidad danio = new DanioZerling(danioTerrestre);
    private Vida vida = new VidaSimple(35);

    public Zerling(){
        superficieDondeSeMueve = new SuperficieTerrestre();
        this.turnosDeConstruccion = 2;
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
