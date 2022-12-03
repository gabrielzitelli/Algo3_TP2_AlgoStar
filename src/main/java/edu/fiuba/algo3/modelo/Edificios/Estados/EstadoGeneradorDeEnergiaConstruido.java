package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Energia;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeEnergiaConstruido implements EstadoGeneradorDeEnergia {

    private Energia energia = new Energia();
    private Suministro poblacionImperio;

    public EstadoGeneradorDeEnergiaConstruido(Coordenada coordenada) {
        energia.energizar(coordenada);
    }

    @Override
    public EstadoGeneradorDeEnergia actualizar(Coordenada coordenada) {
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro) {
        this.poblacionImperio = suministroImperio;
    }

    @Override
    public void modificarSuministro(){}

    @Override
    public void desenergizar(Coordenada coordenada) {
        energia.desenergizar(coordenada);
    }

    @Override
    public String getEstado() {
        return " estado construido";
    }

    public void disminuirSuministro(int cantidadDisminucionSuministro){
        poblacionImperio.disminuirSuministro(cantidadDisminucionSuministro);
    }
}
