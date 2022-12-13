package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Moho;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeMohoConstruido implements EstadoGeneradorDeMoho {

    final Moho moho = new Moho();

    public EstadoGeneradorDeMohoConstruido(Coordenada coordenada) {
        moho.expandir(coordenada);
    }

    @Override
    public EstadoGeneradorDeMoho actualizar(Coordenada coordenada) {
        moho.expandir(coordenada);
        return this;
    }
}
