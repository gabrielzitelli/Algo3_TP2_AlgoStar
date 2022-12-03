package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.DanioUnidad;
import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Vida.Vida;

import java.util.ArrayList;

public abstract class Unidad implements Ocupable {

     protected Superficie superficieDondeSeMueve;
     protected Coordenada coordenada;
     protected int turnosDeConstruccion;
     protected int rangoDeAtaque;
     protected DanioUnidad danio;
     protected Vida vida;
     protected int costoMineral;
     protected int costoGas;

     protected boolean estadoMuerta = false;
     protected boolean yaAtaco = false;
     protected boolean yaCamino = false;

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

          yaAtaco = false;
          yaCamino = false;
     }

     public void atacar(Casilla casillaAAtacar) {
          if(!yaAtaco){
               casillaAAtacar.recibirAtaque(new Ataque(danio));
               yaAtaco = true;
          }
          else{
               throw new ErrorYaAtaco();
          }
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
          elMapa.quitarUnidad(coordenada);
          this.estadoMuerta = true;
     }

     public void moverA(Coordenada coordenadaDestino) {
          Mapa elMapa = Mapa.obtener();
          if (yaCamino){
               throw new ErrorYaCaminoLaUnidadEnEsteTurno();
          }
          if ( estaDentroDelRadio(coordenadaDestino) ){
               elMapa.moverUnidad(this.coordenada, coordenadaDestino);
          }
          else{
               throw new ErrorNoSePuedeCaminarHastaEsaDistancia();
          }

     }

     public boolean estaDentroDelRadio(Coordenada coordenadaFinal){
          int xCoordenadaFinal, yCoordenadaFinal;
          int xCoordenadaActual, yCoordenadaActual;
          xCoordenadaFinal = coordenadaFinal.getCoordenadaX();
          yCoordenadaFinal = coordenadaFinal.getCoordenadaY();
          xCoordenadaActual = this.coordenada.getCoordenadaX();
          yCoordenadaActual = this.coordenada.getCoordenadaY();

          double circunf;
          circunf = (Math.pow((xCoordenadaFinal-xCoordenadaActual),2) + Math.pow((yCoordenadaFinal-yCoordenadaActual),2) );

          int rangoDeCaminar = 5;

          double radio = Math.pow(rangoDeCaminar,2);
          return (circunf <= radio);


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
     public Object obtenerEstado(){
          return null;
     }
}
