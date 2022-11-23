package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Mutalisco extends UnidadZerg {

    private final int turnosDeContruccion = 7;
    private final int danioTerrestre = 10;
    private final int danioAereo = 10;
    private final int cantidadDeVida = 120;

    private final int costoGasEvolucion;
    private final int costoMineralEvolucion;

    public Mutalisco() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioMutalisco(danioTerrestre, danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);

        this.costoGasEvolucion = 100;
        this.costoMineralEvolucion = 50;

        this.rangoDeAtaque = 3;
    }

    @Override
    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Mineral(costoMineralEvolucion));
        requisitosMateriales.add(new Gas(costoGasEvolucion));
        return requisitosMateriales;
    }
}