package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class Zealot extends UnidadProtoss {

    private final int turnosDeContruccion = 4;
    private final int danioTerrestre = 8;
    private final int cantidadDeVida = 100;
    private final int cantidadDeEscudo = 60;

    public Zealot(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.danio = new DanioZealot(danioTerrestre);
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.rangoDeAtaque = 1;
    }

    @Override
    public void atacar(Casilla casillaAAtacar) {
        super.atacar(casillaAAtacar);
        try {
            Mapa.obtener().moverUnidad(coordenada, casillaAAtacar.obtenerCoordenada());
        }
        catch (ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada e) {
            return;
        }
        // TODO contador de invisibilidad +1
    }
}
