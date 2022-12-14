package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Asimilador;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class AsimiladorVista extends EdificioVista {

    public AsimiladorVista() {
        this.tile = new Tile("edificios_protoss/32px/asimilador.png");
        this.identificador = "asimilador";
        this.info = "Asimilador";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/asimiladorRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/asimiladorRawConstruir.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Volcán de gas, suelo sin Moho";
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }

    public void actualizarEfectoSiEstaEnConstruccion(ImageView imageView, Coordenada coordenada){
        Asimilador esteAsimilador = (Asimilador) Mapa.obtener().obtenerOcupable(coordenada);

        if(esteAsimilador.toString().contains(" estado en_construccion"))
            aplicarEfectoBlancoYNegroImageView(imageView);
    }
}
