package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

// Permite actualizar el moho (actualmente solo utilizado en el Criadero)
public interface EstadoGeneradorDeMoho {

    EstadoGeneradorDeMoho actualizar(Coordenada coordenada);
}
