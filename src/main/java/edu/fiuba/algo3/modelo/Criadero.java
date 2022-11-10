package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Criadero extends Edificio {

    private Tablero tablero;
    private Zergs zergs;
    private Coordenadas posicion;
    private final int maxLarvas = 3;
    private int cantidadLarvas;
    private VidaRegenerativa vida;
    private int contadorTurnosMoho = 0;
    private int turnosDeAumento = 2;
    private int radioDeExpancion = 5;

    public Criadero(Tablero _tablero, NodoCompatible requisitos, Coordenadas _posicion, Zergs _zergs) {
        this.tablero = _tablero;
        this.zergs = _zergs;
        this.posicion = _posicion;
        cantidadLarvas = maxLarvas;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(500, 0.25);
        nodoCompatible = requisitos;
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 4;
    }

    public Zangano criarZangano() {
        this.estaActiva();
        
        if (cantidadLarvas == 0)
            throw new CriaderoSinLarvas();

        cantidadLarvas--;
        return zergs.reclutarZangano(this.posicion);
    }

    public void accionDeTurno() {
        turnosExistiendo ++;
        aumentarLarvas();
        this.vida.accionDeTurno();
        aumentarMoho();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }
    @Override
    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }

    private void aumentarMoho() {
        contadorTurnosMoho ++;
        if (contadorTurnosMoho >= turnosDeAumento) {
            tablero.actualizarTerreno(this.posicion, radioDeExpancion, new Moho());
            radioDeExpancion ++;
            contadorTurnosMoho = 0;
        }
    }
}
