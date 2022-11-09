package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Extractor extends Edificio {

    private Recurso gasVespeno;
    private VidaRegenerativa vida;

    public Extractor(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(750, 0.25);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        // TODO
    }

    public int getVida(){
        return this.vida.getVida();
    }

    @Override
    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }
}
