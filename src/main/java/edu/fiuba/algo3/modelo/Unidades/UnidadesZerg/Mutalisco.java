package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.Vida;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Mutalisco extends UnidadZerg {

    private final int costoGasEvolucion;
    private final int costoMineralEvolucion;
    private final int danioTerrestre = 10;
    private final int danioAereo = 10;
    private DanioUnidad danio = new DanioMutalisco(danioTerrestre, danioAereo);
    private Vida vida = new VidaSimple(120);

    public Mutalisco(){
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.costoGasEvolucion = 100;
        this.costoMineralEvolucion = 50;
        this.turnosDeConstruccion = 7;
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Recurso(costoMineralEvolucion));
        requisitosMateriales.add(new Recurso(costoGasEvolucion));
        return requisitosMateriales;
    }

    public Ataque atacar(){
        return new Ataque(danio);
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(superficieDondeSeMueve.conseguirTipoDeAtaque(unAtaque));
    }
}