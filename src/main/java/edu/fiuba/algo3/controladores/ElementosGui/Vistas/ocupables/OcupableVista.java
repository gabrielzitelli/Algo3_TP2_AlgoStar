package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieAereaVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieTerrestreVista;
import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Unidades.SinOcupar;

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
    public static Vista obtenerOcupable(Ocupable obtenerOcupable) {
        return obtenerVista(obtenerOcupable, ocupables);
    }
}
