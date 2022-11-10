package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.excepciones.EdificioEnConstruccion;

import java.util.LinkedList;

public class NexoMineral extends Edificio {

    private Recurso minerales;
    private VidaConEscudo vida;
    private NodoRecurso nodoMinerales;
    private int unidadesPorTurno = 10;

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

        try {
            this.estaActiva();
            minerales.depositar(nodoMinerales.extraer(unidadesPorTurno));
        }
        catch(EdificioEnConstruccion e){
        }
        turnosExistiendo ++;
        this.vida.accionDeTurno();
    }

    @Override
    public void esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        super.esCompatible(terreno, nodoRecurso);
        nodoMinerales = nodoRecurso;
    }

    public void recibirDanio(int danio){
        this.vida.aplicarDanio(danio);
    }

    public int getVida(){
        return vida.getVida();
    }
}
