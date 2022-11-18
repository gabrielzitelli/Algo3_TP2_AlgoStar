package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeMohoEnConstruccion implements EstadoGeneradorDeMoho {

    private int turnoParaEstarConstruido;

    public EstadoGeneradorDeMohoEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoGeneradorDeMoho actualizar(Coordenada coordenada) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0)
            return new EstadoGeneradorDeMohoConstruido(coordenada);

        return this;
    }

}
