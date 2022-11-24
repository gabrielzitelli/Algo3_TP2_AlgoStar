package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class EstadoGeneradorDeEnergiaEnConstruccion implements EstadoGeneradorDeEnergia {

    private int turnoParaEstarConstruido;

    private Suministro poblacionImperio;

    private int aumentoSuministro;

    public EstadoGeneradorDeEnergiaEnConstruccion(int turnoParaEstarConstruido) {
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }

    @Override
    public EstadoGeneradorDeEnergia actualizar(Coordenada coordenada) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            this.modificarSuministro();
            EstadoGeneradorDeEnergiaConstruido nuevoEstado = new EstadoGeneradorDeEnergiaConstruido(coordenada);
            nuevoEstado.marcarSuministro(poblacionImperio, 0);
            return nuevoEstado;
        }
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro){
        poblacionImperio = suministroImperio;
        aumentoSuministro = cantidadAumentoSuministro;
    }

    @Override
    public void modificarSuministro(){
        poblacionImperio.aumentarSuministro( aumentoSuministro );
    }

    public void desenergizar(Coordenada coordenada) {
    }

    public void disminuirSuministro(int cantidadDisminucionSuministro){
    }
}
