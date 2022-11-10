package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Asimilador extends Edificio {


    private Recurso gasVespeno;
    private NodoRecurso recursoSobreElQueEsta;
    private VidaConEscudo vida;

    public Asimilador(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
        this.vida = new VidaConEscudo(450, 0.25, 450);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.vida.accionDeTurno();
        // TODO
    }

    @Override
    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        this.recursoSobreElQueEsta = nodoRecurso;
        return nodoCompatible.esCompatible(terreno, nodoRecurso);
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }
}
