package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

import java.util.LinkedList;

public class NexoMineral extends Edificio {

    private Recurso minerales;
    private NodoRecurso recursoSobreElQueEsta;
    private VidaConEscudo vida;

    private LinkedList<Zangano> zanganosEmpleados;

    public NexoMineral(NodoCompatible requisitos, Recurso _minerales) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 4;
        nodoCompatible = requisitos;
        this.minerales = _minerales;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaConEscudo(250, 0.25, 250);
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
        this.extraer();
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

    public void extraer(){
        // recursoSobreElQueEsta.modificarRecurso(gasVespeno, ... );
    }
}
