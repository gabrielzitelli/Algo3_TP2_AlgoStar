package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoContratador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoContratadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolector;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolectorEnConstruccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEstaUnidadNoSePuedeContratar;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.LinkedList;

public class Extractor extends EdificioZerg {

    private EstadoRecolector estadoRecolector;
    private EstadoContratador estadoContratador;
    private final int turnoParaEstarConstruido = 6;
    private final Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private final LinkedList<Unidad> zanganosEmpleados = new LinkedList<>();
    private final int cantidadDeExtraccionUnitaria = 10;
    private final int valorVital = 750;

    public Extractor(Recurso gasDelImperio){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new GasRecolectable();
        this.estadoMohoRequerido = new ConMoho();
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();
        this.gasDelImperio = gasDelImperio;
        this.estadoRecolector = new EstadoRecolectorEnConstruccion(turnoParaEstarConstruido);
        this.estadoContratador = new EstadoContratadorEnConstruccion(turnoParaEstarConstruido);
        this.identificador = "extractor";
    }

    public void pasarTurno(){
        estadoRecolector = estadoRecolector.actualizar();
        estadoContratador = estadoContratador.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer(){
        int cantidadAExtraer = zanganosEmpleados.size() * cantidadDeExtraccionUnitaria;
        estadoRecolector.extraer(gasDelImperio, volcanDeGas, cantidadAExtraer);
    }

    public void contratarZangano(Unidad zanganoAContratar){
        if (!zanganoAContratar.esIgualA(new Zangano()))
            throw new ErrorEstaUnidadNoSePuedeContratar();

        estadoContratador.contratar(zanganoAContratar, zanganosEmpleados);
    }

    public void descontratarZangano() {
        estadoContratador.desContratar(zanganosEmpleados);
    }

    public void verificarColocable(Casilla unaCasilla){
        super.verificarColocable(unaCasilla);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }

    @Override
    public void contratarUnidad(Unidad unidad){
        contratarZangano(unidad);
    }

    @Override
    protected String obtenerEstado() {
        return estadoRecolector.getEstado();
    }

    public String toString() {
        String info = super.toString();
        info += " cantidad_unidades " + zanganosEmpleados.size();
        return info;
    }
}
