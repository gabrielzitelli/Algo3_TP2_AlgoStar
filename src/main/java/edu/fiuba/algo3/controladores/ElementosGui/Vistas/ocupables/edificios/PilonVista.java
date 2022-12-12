package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class PilonVista extends EdificioVista {

    public PilonVista() {
        this.tile = new Tile("edificios_protoss/32px/pilon.png");
        this.identificador = "pilon";
        this.info = "Pilon";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/pilonRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/pilonRawConstruir.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Suelo sin Moho";
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }
}
