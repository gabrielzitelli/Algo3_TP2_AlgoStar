package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class Scout extends UnidadProtoss {

    private int danioTerrestre = 8;
    private int danioAereo = 14;
    private DanioUnidad danio = new DanioScout(danioTerrestre, danioAereo);
    private Vida vida = new VidaConEscudo(150, 100);

    public Scout(){
        this.turnosDeConstruccion = 9;
        superficieDondeSeMueve = new SuperficieAerea();
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
