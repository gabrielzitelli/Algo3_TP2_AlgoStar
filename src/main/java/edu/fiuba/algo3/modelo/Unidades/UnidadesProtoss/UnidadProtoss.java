package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.DanioUnidad;
import edu.fiuba.algo3.modelo.Ataque.DanioZealot;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.Vida;

public abstract class UnidadProtoss implements Unidad {

    protected int turnosDeConstruccion;
    protected Superficie superficieDondeSeMueve;
    protected DanioUnidad danio;
    protected Vida vida;

    public boolean estaConstruida() {
        return (turnosDeConstruccion == 0);
    }

    public void pasarTurno() {
        if (turnosDeConstruccion > 0)
            turnosDeConstruccion--;
    }

    public Ataque atacar(){
        return new Ataque(danio);
    }

    public void verificarSuperficie(Superficie superficie){
        if (!superficie.puedeMoverse(this.superficieDondeSeMueve))
            throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();
    }

    public void recibirAtaque(Ataque unAtaque){
        this.vida.aplicarAtaque(superficieDondeSeMueve.conseguirTipoDeAtaque(unAtaque));
    }

    public boolean esIgualA(Unidad unidad) {
        return this.getClass().equals(unidad.getClass());
    }
}
