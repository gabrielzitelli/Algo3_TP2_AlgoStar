package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Edificio_Zerg.Extractor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExtractorTest {

    //El Zangano se mete bajo tierra y aparece en el gas

    @Test
    public void test01PuedoCrearUnExtractor(){
        Extractor unExtractor = new Extractor();

        assertNotNull(unExtractor);
    }
}
