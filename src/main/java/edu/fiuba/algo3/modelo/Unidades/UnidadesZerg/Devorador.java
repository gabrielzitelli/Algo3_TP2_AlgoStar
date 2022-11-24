package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioDevorador;
import edu.fiuba.algo3.modelo.Ataque.DanioGuardian;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

import java.util.ArrayList;

public class Devorador extends UnidadZerg {
    private final int turnosDeContruccion = 4;
    private final int danioAereo = 15;
    private final int cantidadDeVida = 200;
    private final int costoMineralEvolucion = 100;
    private final int costoGasEvolucion = 50;

    public Devorador() {
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieAerea();
        this.danio = new DanioDevorador(danioAereo);
        this.vida = new VidaSimple(cantidadDeVida);
        this.rangoDeAtaque = 5;
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Mineral(costoMineralEvolucion));
        requisitosMateriales.add(new Gas(costoGasEvolucion));
        return requisitosMateriales;
    }
}
