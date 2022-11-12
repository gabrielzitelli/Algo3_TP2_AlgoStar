package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Edificio_Zerg.Extractor;
import edu.fiuba.algo3.modelo.Edificio_Zerg.FabricaZangano;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeExtraerSinZanganoAsignado;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExtractorTest {

    //El Zangano se mete bajo tierra y aparece en el gas

    @Test
    public void test01PuedoCrearUnExtractor(){

        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

        assertNotNull(unExtractor);
    }

    @Test
    public void test02NoPuedoExtraerElGasDeUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.extraer());
    }

    @Test
    public void test03NoPuedoContratarUnZanganoEnUnExtractorQueNoEstaConstruidoEn5Turnos(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

        for(int i = 0; i < 5; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorEdificioNoEstaConstruido.class, () -> unExtractor.contratarZangano(new Zangano()));
    }

    @Test
    public void test04PuedoContratarUnZanganoEnUnExtractorQueEstaConstruidoEn6Turnos(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

        //Construyo el Extractor
        for(int i = 0; i < 6; i++)
            unExtractor.pasarTurno();

        assertDoesNotThrow(() -> unExtractor.contratarZangano(new Zangano()));
    }

   @Test
    public void test05NoPuedoExtraerElGasDeUnExtractorQueEstaConstruidoEn6TurnosSinAsignarUnZangano(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

       //Construyo el Extractor
       for(int i = 0; i < 6; i++)
            unExtractor.pasarTurno();

        assertThrows(ErrorNoSePuedeExtraerSinZanganoAsignado.class ,() -> unExtractor.extraer());
    }

    @Test
    public void test06PuedoExtraerElGasDeUnExtractorQueEstaConstruidoEn6TurnosConUnZanganoAsignado(){
        Recurso gasDelImperio = new Recurso(0);
        GasBruto volcanDeGas = new GasBruto();

        Extractor unExtractor = new Extractor(gasDelImperio, volcanDeGas);

        //Construyo el Extractor
        for(int i = 0; i < 6; i++)
            unExtractor.pasarTurno();
        unExtractor.contratarZangano(new Zangano());

        assertDoesNotThrow(() -> unExtractor.extraer());
    }
}
