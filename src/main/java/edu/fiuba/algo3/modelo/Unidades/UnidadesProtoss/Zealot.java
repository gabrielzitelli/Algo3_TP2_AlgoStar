package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioTerrestre;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZealot;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Visibilidad;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Visible;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Zealot extends UnidadProtoss {

    private Visibilidad estado;

    public Zealot(){
        this.turnosDeConstruccion = 4;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.rangoDeAtaque = 1;
        int danioTerrestre = 8;
        this.danio = new DanioTerrestre(danioTerrestre);
        int cantidadDeVida = 100;
        int cantidadDeEscudo = 60;
        this.vida = new VidaConEscudo(cantidadDeVida, cantidadDeEscudo);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        int muertesParaInvisibilidad = 3;
        estado = new Visible(this, muertesParaInvisibilidad);
        this.costoGas = 0;
        this.costoMineral = 100;
        this.identificador = "zealot";
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
        estado = estado.verificarVisibilidadDe(unaCasilla);
    }

    @Override
    public void atacar(Casilla casillaAAtacar) {
        super.atacar(casillaAAtacar);

        try {
            Mapa.obtener().moverUnidad(coordenada, casillaAAtacar.obtenerCoordenada());
        }
        catch (ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada e) {
            return;
        }

        estado = estado.aumentarContador();
        if (estado.esInvisible()) {
            this.identificador = "zealot_invisible";
        }
    }

    @Override
    public void recibirAtaque(Ataque unAtaque) {
        estado.recibirAtaque(unAtaque);
    }

    public void recibirAtaqueDefault(Ataque unAtaque) {
        super.recibirAtaque(unAtaque);
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaZealot.obtenerPoblacionNecesaria());
    }
}
