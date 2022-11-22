package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Energia;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeEnergiaConstruido implements EstadoGeneradorDeEnergia {

    private Energia energia = new Energia();

    public EstadoGeneradorDeEnergiaConstruido(Coordenada coordenada) {
        energia.energizar(coordenada);
    }

    @Override
    public EstadoGeneradorDeEnergia actualizar(Coordenada coordenada) {
        return this;
    }
    @Override
    public void desenergizar(Coordenada coordenada) {
        energia.desenergizar(coordenada);
    }
}
