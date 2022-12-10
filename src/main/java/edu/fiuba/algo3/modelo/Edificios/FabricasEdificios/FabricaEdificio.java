package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class FabricaEdificio {

    protected FabricasDisponibles fabricasDisponibles;
    protected ArrayList<Unidad> unidades;
    protected Mineral mineralesDelImperio;
    protected Gas gasDelImperio;
    protected LinkedList<Edificio> edificios;

    public void asignar(FabricasDisponibles fabricasDisponibles, ArrayList<Unidad> unidades,
                        Mineral mineralesDelImperio, Gas gasDelImperio, LinkedList<Edificio> edificios) {
        this.fabricasDisponibles = fabricasDisponibles;
        this.unidades = unidades;
        this.mineralesDelImperio = mineralesDelImperio;
        this.gasDelImperio = gasDelImperio;
        this.edificios = edificios;
    }

    public abstract Edificio crear();

    protected void comprobarRequisitos(ArrayList<Edificio> requisitos) {
        //Digase de los edificios que son prerequisitos de otro edificio
        int requisitosCumplidos = 0;
        for(Edificio requisito: requisitos){
            for(Edificio edificio: edificios) {
                if (edificio.getClass().equals(requisito.getClass())){
                    requisitosCumplidos++;
                    break;
                }
            }
        }

        if (requisitosCumplidos != requisitos.size())
            throw new ErrorNoSeCumplenLosPreRequisitosDelEdificio();
    }

}
