package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class AsimiladorVista extends OcupableVista {

    public AsimiladorVista() {
        this.tile = new Tile("edificios_protoss/32px/asimilador.png");
        this.identificador = "asimilador";
        this.info = "Asimilador";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/asimiladorRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/asimiladorRawConstruir.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }
}
