package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioContratador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioContratadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioRecolector;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioRecolectorEnConstruccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEstaUnidadNosePuedeContratar;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.LinkedList;

public class Extractor extends Edificio {

    private EstadoEdificioRecolector estadoEdificioRecolector;
    private EstadoEdificioContratador estadoEdificioContratador;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private LinkedList<Unidad> zanganosEmpleados = new LinkedList<>();
    private int cantidadDeExtraccionUnitaria = 10;
    private int valorVital = 750;

    public Extractor(Recurso gasDelImperio){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new GasRecolectable();
        this.estadoMoho = new ConMoho();
        this.vida = new VidaRegenerativa(valorVital);
        this.gasDelImperio = gasDelImperio;
        this.estadoEdificioRecolector = new EstadoEdificioRecolectorEnConstruccion(turnoParaEstarConstruido);
        this.estadoEdificioContratador = new EstadoEdificioContratadorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estadoEdificioRecolector = estadoEdificioRecolector.actualizar();
        estadoEdificioContratador = estadoEdificioContratador.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer(){
        int cantidadAExtraer = zanganosEmpleados.size() * cantidadDeExtraccionUnitaria;
        estadoEdificioRecolector.extraer(gasDelImperio, volcanDeGas, cantidadAExtraer);
    }

    public void contratarZangano(Unidad zanganoAContratar){
        if (!zanganoAContratar.esIgualA(new Zangano()))
            throw new ErrorEstaUnidadNosePuedeContratar();

        estadoEdificioContratador.contratar(zanganoAContratar, zanganosEmpleados);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        super.verificarConstruccion(unaCasilla);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }

    @Override
    public void contratarUnidad(Unidad unidad){
        contratarZangano(unidad);
    }
}
