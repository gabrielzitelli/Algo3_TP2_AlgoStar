package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

// Permite actualizar la energia (actualmente solo utilizado en el Pilon)
public interface EstadoGeneradorDeEnergia {

    EstadoGeneradorDeEnergia actualizar(Coordenada coordenada);
    void desenergizar(Coordenada coordenada);
}
