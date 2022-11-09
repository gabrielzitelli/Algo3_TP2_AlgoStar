package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.EdificioEnConstruccion;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class Extractor extends Edificio {

    private int maxEmpleados ;
    private int cantidadEmpleados;
    private Recurso gasVespeno;

    private LinkedList<Zangano> zanganoEmpleado;

    public Extractor(NodoCompatible requisitos, Recurso _gasVespeno) {
        this.turnosExistiendo = 0;
        this.turnosDeConstruccion = 6;
        this.maxEmpleados = 3;
        this.cantidadEmpleados = 0;
        this.nodoCompatible = requisitos;
        this.gasVespeno = _gasVespeno;
        this.zanganoEmpleado = new LinkedList<>();
    }

    @Override
    public void accionDeTurno() {
        turnosExistiendo ++;
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

    public void extraer(){
        for ( int i = 0; i < cantidadEmpleados ; i++) {
            zanganoEmpleado.get(i).extraerMineral();
        }
    }
}
