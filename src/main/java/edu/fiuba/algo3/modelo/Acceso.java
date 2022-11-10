package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.EdificioNoEnergizado;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Acceso extends Edificio {

    private VidaConEscudo vida;

    private Tablero tablero;

    public Acceso(NodoCompatible requisitos, Coordenadas ubicacion) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 8;
        this.nodoCompatible = requisitos;
        this.posicion = ubicacion;
        this.vida = new VidaConEscudo(500, 0.25, 500);
    }

    @Override
    public void accionDeTurno() {
        if (!tablero.estaEnergizado(posicion)){
            throw new EdificioNoEnergizado();
        }
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        // TODO
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }

    public void asignarTablero(Tablero tablero){
        this.tablero = tablero;
    }
}
