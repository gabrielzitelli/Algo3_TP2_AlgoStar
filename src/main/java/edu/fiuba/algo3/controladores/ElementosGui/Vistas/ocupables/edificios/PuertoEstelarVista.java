package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.DragonVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ScoutVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ZealotVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZealot;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.Objects;

public class PuertoEstelarVista extends OcupableVista {

    public PuertoEstelarVista() {
        this.tile = new Tile("edificios_protoss/32px/puerto_estelar.png");
        this.identificador = "puerto_estelar";
        this.info = "Puerto estelar";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/puerto_estelarRaw.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        String escudoActual = obtenerAtributoDeString(stringOcupable, "escudoActual");
        String escudoMaxima = obtenerAtributoDeString(stringOcupable, "escudoMaximo");
        textoEscudo.setText(escudoActual + "/" + escudoMaxima);
    }

    public void manejarBotones(Button[] arrayBotones, Coordenada coordenada, String imperioDeJugadorActual){
        if(!imperioDeJugadorActual.equalsIgnoreCase("protoss"))
            return;

        PuertoEstelar estePuerto = (PuertoEstelar) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearScout = arrayBotones[0];

        double anchoBoton = botonCrearScout.getPrefWidth();
        double altoBoton = botonCrearScout.getPrefWidth();

        crearBotonDeUnidad(botonCrearScout, new ScoutVista(), anchoBoton, altoBoton);

        botonCrearScout.setOnAction( event -> estePuerto.crearUnidad(new FabricaScout()));

        prepararHabilitacionDeBoton(botonCrearScout, new FabricaZealot(), estePuerto);
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, PuertoEstelar estePuerto){
        try{
            estePuerto.estaAptaUnidadParaConstruir(unaFabrica);
        } catch (RuntimeException e){
            boton.setDisable(true);
        }
    }
}
