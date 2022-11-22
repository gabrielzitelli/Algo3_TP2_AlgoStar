package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Ataque.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Visibilidad;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Visible;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Zealot extends UnidadProtoss {

    private final int turnosDeContruccion = 4;
    private final int danioTerrestre = 8;
    private final int cantidadDeVida = 100;
    private final int cantidadDeEscudo = 60;
    private Visibilidad estado;
    private int muertesParaInvisibilidad = 3;

    public Zealot(){
        this.turnosDeConstruccion = turnosDeContruccion;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.danio = new DanioZealot(danioTerrestre);
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.rangoDeAtaque = 1;
        estado = new Visible(vida, superficieDondeSeMueve, muertesParaInvisibilidad);
    }

    public void verificarSuperficie(Superficie superficie, Coordenada coordenada) {
        super.verificarSuperficie(superficie, coordenada);
        estado.establecerCoordenada(coordenada);
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
        estado = estado.aumentarContador();
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {
        estado.recibirAtaque(unAtaque);
    }
}
