package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Pilon extends Edificio {

    private Tablero tablero;
    private VidaConEscudo vida;
    private int rangoPilon = 3;

    public Pilon(Tablero _tablero, NodoCompatible requisitos, Coordenadas ubicacion) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 5;
        this.tablero = _tablero;
        this.nodoCompatible = requisitos;
        this.posicion = ubicacion;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaConEscudo(300, 0.25, 300);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        actualizarEnergia();
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }

    public void actualizarEnergia() {
        tablero.cargarTerreno(posicion);
    }
}
