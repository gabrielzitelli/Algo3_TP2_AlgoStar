package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Ataque.DanioUnidad;
import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
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


     public void verificarColocable(Casilla unaCasilla) {
          if (!unaCasilla.puedeMoverse(this.superficieDondeSeMueve))
               throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();

          this.coordenada = unaCasilla.obtenerCoordenada();
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
     }

     public void atacar(Casilla casillaAAtacar) {
          casillaAAtacar.recibirAtaque(new Ataque(danio));
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
}
