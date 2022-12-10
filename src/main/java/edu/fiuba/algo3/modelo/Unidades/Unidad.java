package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacar;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Caminadora;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Caminar;
import edu.fiuba.algo3.modelo.Vida.Vida;

import java.util.ArrayList;

public abstract class Unidad implements Ocupable {

     protected Superficie superficieDondeSeMueve;
     protected Coordenada coordenada;
     protected int turnosDeConstruccion;
     protected int rangoDeAtaque;
     protected TipoDanio danio;
     protected Vida vida;
     protected int costoMineral = 0;
     protected int costoGas = 0;
     protected String identificador;
     protected int radioQuePuedeCaminar = 5;

     protected boolean estadoMuerta = false;

     protected Atacar estadoPelea;

     private Caminar estadoCaminar = new Caminadora(radioQuePuedeCaminar);


     public abstract boolean perteneceAImperio(Imperio imperio);

     protected abstract void verificarFuegoAliado(Casilla casillaAAtacar);

     public void verificarColocable(Casilla unaCasilla) {
          if (!unaCasilla.puedeMoverse(this.superficieDondeSeMueve))
               throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();

          this.coordenada = unaCasilla.obtenerCoordenada();
     }

     public void actualizarColocable(Casilla unaCasilla) {
          verificarColocable(unaCasilla);
     }

     public boolean estaConstruida() {
          return (turnosDeConstruccion == 0);
     }

     public void pasarTurno() {
          if (turnosDeConstruccion > 0)
               turnosDeConstruccion--;

          estadoPelea = estadoPelea.pasarTurno();
          estadoCaminar = estadoCaminar.pasarTurno();
     }

     public void atacar(Casilla casillaAAtacar) {
          verificarFuegoAliado(casillaAAtacar);
          estadoPelea = estadoPelea.atacar(coordenada, casillaAAtacar, danio);
     }

     public void recibirAtaque(Ataque unAtaque) {
          try {
               vida.aplicarAtaque(superficieDondeSeMueve.conseguirTipoDeAtaque(unAtaque));
          }
          catch (ErrorVidaLlegoACero e) {
               destruirUnidad();
          }
     }

     protected void destruirUnidad() {
          Mapa elMapa = Mapa.obtener();
          elMapa.quitarOcupable(coordenada);
          this.estadoMuerta = true;
     }

     public void moverA(Coordenada coordenadaDestino) {
          estadoCaminar = estadoCaminar.caminar(this.coordenada, coordenadaDestino);
     }

     public boolean esIgualA(Unidad unidad) {
          return this.getClass().equals(unidad.getClass());
     }

     public ArrayList<Recurso> requisitosMateriales() {
          ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
          requisitosMateriales.add(new Mineral(costoMineral));
          requisitosMateriales.add(new Gas(costoGas));
          return requisitosMateriales;
     }

     public boolean estaMuerta() {
          return this.estadoMuerta;
     }

     public abstract void disminuirPoblacion(Suministro suministroImperio);

     public Coordenada obtenerCoordenada(){
          return this.coordenada;
     }
     public String toString() {
          String info = "ocupable " + identificador;
          info += this.vida.toString();
          info += " costoMineral " + this.costoMineral + " costoGas " + this.costoGas;
          info += " estado_caminar " + estadoCaminar.toString();
          info += " estado_atacar " + estadoPelea.toString();
          info += " rango_ataque " + rangoDeAtaque;
          return info;
     }

     public boolean esDeEsteTipo(Class claseAAverificar){
          return Unidad.class.equals(claseAAverificar);
     }
}
