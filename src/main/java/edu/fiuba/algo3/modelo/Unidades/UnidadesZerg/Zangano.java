package edu.fiuba.algo3.modelo.Unidades.UnidadesZerg;

import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZangano;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Recolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.MineralBruto;
import edu.fiuba.algo3.modelo.Mapa.SinMaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Atacante;
import edu.fiuba.algo3.modelo.Vida.VidaSimple;

public class Zangano extends UnidadZerg {

    private final Recolectable recolecta = new MineralRecolectable();

    public Zangano() {
        this.turnosDeConstruccion = 1;
        this.superficieDondeSeMueve = new SuperficieTerrestre();
        this.mineralDelImperio = null;
        int cantidadDeVida = 25;
        this.vida = new VidaSimple(cantidadDeVida);
        this.estadoPelea = new Atacante(rangoDeAtaque);
        this.costoGas = 0;
        this.costoMineral = 25;
        this.identificador = "zangano";
    }

    private void interaccionar(Casilla unaCasilla) {
        try {
            unaCasilla.tieneEsteRecoletable(recolecta);
            this.recursoARecolectar = unaCasilla.obtenerMaterial();
        } catch (RuntimeException ignore) {
            this.recursoARecolectar =  new SinMaterialBruto();
        }
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
        interaccionar(unaCasilla);
    }

    @Override
    public void moverA(Coordenada coordenadaDestino) {
        super.moverA(coordenadaDestino);
        interaccionar(Mapa.obtener().obtenerCasilla(coordenada));
    }

    @Override
    public void setDepositoRecurso( Mineral mineralImperio ) {
        this.mineralDelImperio = mineralImperio;
    }

    public void pasarTurno() {
        super.pasarTurno();
        this.extraer();
    }
    public void extraer(){
        if (recursoARecolectar != null) {
            if (recursoARecolectar.getClass().equals(MineralBruto.class))
                mineralDelImperio.depositar(recursoARecolectar.extraer(10));
        }
    }

    public void disminuirPoblacion(Suministro suministroImperio){
        suministroImperio.disminuirPoblacion(FabricaZangano.obtenerPoblacionNecesaria());
    }

    @Override
    public boolean esDeEsteTipo(Class claseAAverificar) {
        return Zangano.class.equals(claseAAverificar);
    }
}