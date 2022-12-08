package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.TipoDanio;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.*;
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

     protected boolean estadoMuerta = false;

     private Atacar estadoPelea = new Atacante();

     private Caminar estadoCaminar = new Caminadora();



     public abstract boolean perteneceAImperio(Imperio imperio);

     public void verificarColocable(Casilla unaCasilla) {
          if (!unaCasilla.puedeMoverse(this.superficieDondeSeMueve))
               throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();

          this.coordenada = unaCasilla.obtenerCoordenada();
     }

     public void actualizarColocable(Casilla unaCasilla) {
          verificarColocable(unaCasilla);
     }

     public int rangoDeAtaque() {
          return rangoDeAtaque;
     }

     public boolean estaConstruida() {
          return (turnosDeConstruccion == 0);
     }

     public void pasarTurno() {
          if (turnosDeConstruccion > 0)
               turnosDeConstruccion--;

          estadoPelea = new Atacante();
          estadoCaminar = new Caminadora();

     }

     public void interaccionar(Casilla unaCasilla){
     }

     public void atacar(Casilla casillaAAtacar) {
          estadoPelea.atacar(casillaAAtacar, danio);
          estadoPelea = new NoAtacante();
     }

     public boolean fuegoAliado(Unidad unaUnidad, Casilla casillaAAtacar){
          return casillaAAtacar.fuegoCompaniero(unaUnidad);
     }
     public abstract boolean somosAliados(Unidad unaUnidad);


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
          elMapa.quitarUnidad(coordenada);
          this.estadoMuerta = true;
     }

     public void moverA(Coordenada coordenadaDestino) {
          estadoCaminar.caminar(this.coordenada, coordenadaDestino);
          this.interaccionar(Mapa.obtener().obtenerCasilla(coordenada));
          estadoCaminar = new NoCaminadora();
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
          return info;
     }

     public boolean esDeEsteTipo(Class claseAAverificar){
          return Unidad.class.equals(claseAAverificar);
     }
}
