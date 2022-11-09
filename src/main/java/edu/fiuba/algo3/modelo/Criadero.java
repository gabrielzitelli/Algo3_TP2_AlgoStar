package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Criadero extends Edificio {

    private final int maxLarvas = 3;
    private final int costoMineral = 50;
    private int cantidadLarvas;
    private NodoCompatible ubicacion;
    private VidaRegenerativa vida;

    public Criadero(NodoCompatible ubicacion, Recurso mineral) {
        mineral.consumir(costoMineral);
        cantidadLarvas = maxLarvas;
        this.ubicacion = ubicacion;
        this.vida = new VidaRegenerativa(500, 0.25);
    }

    public Zangano criarZangano() {
        if (cantidadLarvas == 0)
            throw new CriaderoSinLarvas();

        cantidadLarvas--;
        return new Zangano(new Coordenadas(0,0));
    }

    public void accionDeTurno() {
        aumentarLarvas();
        this.vida.accionDeTurno();
    }

    private void aumentarLarvas() {
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }

    public void recirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }
}
