package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.Objects;

public class PilonVista extends OcupableVista {

    public PilonVista() {
        this.tile = new Tile("edificios_protoss/32px/pilon.png");
        this.identificador = "pilon";
        this.info = "Pilon";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/pilonRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/pilonRawConstruir.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        String escudoActual = obtenerAtributoDeString(stringOcupable, "escudoActual");
        String escudoMaxima = obtenerAtributoDeString(stringOcupable, "escudoMaximo");
        textoEscudo.setText(escudoActual + "/" + escudoMaxima);
    }
}
