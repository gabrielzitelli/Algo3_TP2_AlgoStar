package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;
import edu.fiuba.algo3.modelo.Tablero.NodoRecurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.excepciones.EdificioEnConstruccion;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class Extractor extends Edificio {

    private int maxEmpleados ;
    private int cantidadEmpleados;
    private Recurso gasVespeno;
    private VidaRegenerativa vida;

    private NodoRecurso recursoSobreElQueEsta;

    private LinkedList<Zangano> zanganoEmpleado;

    public Extractor(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.maxEmpleados = 3;
        this.cantidadEmpleados = 0;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
        // TODO usar inyeccion de dependencia con la vida
        this.vida = new VidaRegenerativa(750, 0.25);
        this.zanganoEmpleado = new LinkedList<>();
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
        this.extraer();
        // TODO
    }

    public void contratarZangano(Zangano nuevoZangano){
        //Probar la condicion
        IntStream rangoDeExistencia = IntStream.range(0, cantidadEmpleados + 1);
        if( rangoDeExistencia.noneMatch(each -> maxEmpleados == each))
            throw new EdificioEnConstruccion();
        zanganoEmpleado.add(nuevoZangano);
        cantidadEmpleados ++;

    }

    @Override
    public boolean esCompatible(Terreno terreno, NodoRecurso nodoRecurso) {
        this.recursoSobreElQueEsta = nodoRecurso;
        return nodoCompatible.esCompatible(terreno, nodoRecurso);
    }

    public void extraer(){
        for ( int i = 0; i < cantidadEmpleados ; i++) {
            recursoSobreElQueEsta.modificarRecurso( gasVespeno , 10);
        }
    }
}
