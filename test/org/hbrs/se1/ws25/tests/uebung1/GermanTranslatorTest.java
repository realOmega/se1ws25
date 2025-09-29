package org.hbrs.se1.ws25.tests.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GermanTranslatorTest {

    @Test
    public void aTest() {
        GermanTranslator translator = new GermanTranslator();

        assertEquals("null" , translator.translateNumber(0));
    }

}