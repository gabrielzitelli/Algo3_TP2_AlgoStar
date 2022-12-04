package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.*;

import java.util.ArrayList;

public abstract class OcupableVista extends Vista {

    private static ArrayList<Vista> ocupables = inicializarLista();

    private static ArrayList<Vista> inicializarLista(){
        ArrayList<Vista> ocupable = new ArrayList<>();
        iniciarConEdificios(ocupable);
        iniciarConUnidades(ocupable);
        return ocupable;
    }

    private static void iniciarConUnidades(ArrayList<Vista> ocupable) {
        ocupable.add(new SinOcuparVista());

        //Protoss
        ocupable.add(new DragonVista());
        ocupable.add(new ScoutVista());
        ocupable.add(new ZealotVista());
        ocupable.add(new ZealotInvisibleVista());

        //Zerg
        ocupable.add(new AmoSupremoVista());
        ocupable.add(new DevoradorVista());
        ocupable.add(new GuardianVista());
        ocupable.add(new HidraliscoVista());
        ocupable.add(new MutaliscoVista());
        ocupable.add(new ZanganoVista());
        ocupable.add(new ZerlingVista());
    }

    private static void iniciarConEdificios(ArrayList<Vista> ocupable) {
        //Protoss
        ocupable.add(new CriaderoVista());
        ocupable.add(new EspiralVista());
        ocupable.add(new ExtractorVista());
        ocupable.add(new ReservaVista());
        ocupable.add(new GuaridaVista());

        //Protoss
        ocupable.add(new AccesoVista());
        ocupable.add(new AsimiladorVista());
        ocupable.add(new NexoMineralVista());
        ocupable.add(new PilonVista());
        ocupable.add(new PuertoEstelarVista());
    }
    public static Vista obtenerOcupable(Object obtenerOcupable) {
        return obtenerVista((String)obtenerOcupable, ocupables);
    }
}
