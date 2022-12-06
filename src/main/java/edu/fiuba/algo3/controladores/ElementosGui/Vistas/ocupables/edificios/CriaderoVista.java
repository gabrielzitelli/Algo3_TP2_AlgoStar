package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.*;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.*;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.Objects;

public class CriaderoVista extends OcupableVista {

    Tile piso = new Tile("superficies/32px/moho_transparente.png");
    public CriaderoVista(){
        this.tile = new Tile("edificios_zerg/32px/criadero.png");
        this.identificador = "criadero";
        this.info = "Criadero";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/criaderoRaw.png")));
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        piso.render(gc, x, y);
        super.render(gc,x,y);
    }

    public void manejarBotones(Button[] arrayBotones, Coordenada coordenada, String imperioDeJugadorActual){
        if(!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        Criadero esteCriadero = (Criadero) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearZangano = arrayBotones[0];
        Button botonCrearZerling = arrayBotones[1];
        Button botonCrearHidralisco = arrayBotones[2];
        Button botonCrearMutalisco = arrayBotones[3];
        Button botonCrearAmoSupremo = arrayBotones[4];

        double anchoBoton = botonCrearZangano.getPrefWidth();
        double altoBoton = botonCrearZangano.getPrefWidth();

        crearBotonDeUnidad(botonCrearZangano, new ZanganoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearZerling, new ZerlingVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearHidralisco, new HidraliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearMutalisco, new MutaliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearAmoSupremo, new AmoSupremoVista(), anchoBoton, altoBoton);

        botonCrearZangano.setOnAction( event -> esteCriadero.crearUnidad(new FabricaZangano()));
        botonCrearZerling.setOnAction( event -> esteCriadero.crearUnidad(new FabricaZerling()));
        botonCrearHidralisco.setOnAction( event -> esteCriadero.crearUnidad(new FabricaHidralisco()));
        botonCrearMutalisco.setOnAction( event -> esteCriadero.crearUnidad(new FabricaMutalisco()));
        botonCrearAmoSupremo.setOnAction( event -> esteCriadero.crearUnidad(new FabricaAmoSupremo()));
    }
}
