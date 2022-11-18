package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public interface EstadoGeneradorDeMoho {

    EstadoGeneradorDeMoho actualizar(Coordenada coordenada);
}
