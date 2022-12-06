package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.DragonVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ZealotVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

public class AccesoVista extends OcupableVista {

    public AccesoVista() {
        this.tile = new Tile("edificios_protoss/32px/acceso.png");
        this.identificador = "acceso";
        this.info = "Acceso";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/accesoRaw.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        String escudoActual = obtenerAtributoDeString(stringOcupable, "escudoActual");
        String escudoMaxima = obtenerAtributoDeString(stringOcupable, "escudoMaximo");
        textoEscudo.setText(escudoActual + "/" + escudoMaxima);
    }

    public void manejarBotones(Button[] arrayBotones, Coordenada coordenada, String imperioDeJugadorActual){
        Button botonCrearZealot = arrayBotones[0];
        Button botonCrearDragon = arrayBotones[1];
        double anchoBoton = botonCrearZealot.getPrefWidth();
        double altoBoton = botonCrearZealot.getPrefWidth();

        crearBotonDeUnidad(botonCrearZealot, new ZealotVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearDragon, new DragonVista(), anchoBoton, altoBoton);
    }
}
