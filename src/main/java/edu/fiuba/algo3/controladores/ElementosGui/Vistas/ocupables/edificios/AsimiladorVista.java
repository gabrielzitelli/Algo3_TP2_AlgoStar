package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.Objects;

public class AsimiladorVista extends OcupableVista {

    public AsimiladorVista() {
        this.tile = new Tile("edificios_protoss/32px/asimilador.png");
        this.identificador = "asimilador";
        this.info = "Asimilador";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/asimiladorRaw.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        String escudoActual = obtenerAtributoDeString(stringOcupable, "escudoActual");
        String escudoMaxima = obtenerAtributoDeString(stringOcupable, "escudoMaximo");
        textoEscudo.setText(escudoActual + "/" + escudoMaxima);
    }
}
