package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class NexoMineralVista extends OcupableVista {

    public NexoMineralVista() {
        this.tile = new Tile("edificios_protoss/32px/nexo_mineral.png");
        this.identificador = "nexo_mineral";
        this.info = "Nexo Mineral";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/nexo_mineralRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/nexo_mineralRawConstruir.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }
}
