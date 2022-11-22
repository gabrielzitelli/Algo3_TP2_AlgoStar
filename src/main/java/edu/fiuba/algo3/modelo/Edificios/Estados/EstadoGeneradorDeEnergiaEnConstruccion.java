package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeEnergiaEnConstruccion implements EstadoGeneradorDeEnergia {

    private int turnoParaEstarConstruido;

    public EstadoGeneradorDeEnergiaEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoGeneradorDeEnergia actualizar(Coordenada coordenada) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoGeneradorDeEnergiaConstruido(coordenada);

        return this;
    }

    @Override
    public void desenergizar(Coordenada coordenada) {

    }
}
