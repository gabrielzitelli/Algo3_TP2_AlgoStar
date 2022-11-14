package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Recolectable;

public class Zangano extends UnidadZerg {

    private Recolectable recolecta = new MineralRecolectable();

    public Zangano(){
        //FALTA CREAR UNA VIDA
        this.mineralDelImperio = null;
    }

    @Override
    public void interaccionar(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(recolecta);
        //CAMBIAR EL NOMBRE DEL ERROR
        this.recursoARecolectar = unaCasilla.obtenerMaterial();

    }

    @Override
    public void setDepositoRecurso( Recurso recursoImperio ) {
        this.mineralDelImperio = recursoImperio;
    }

    public void extraer(){
        mineralDelImperio.depositar(recursoARecolectar.extraer(10));
    }

}