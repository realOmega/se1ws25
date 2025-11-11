package org.hbrs.se1.ws25.tests.uebung1;

import org.hbrs.se1.ws25.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws25.exercises.uebung1.control.TranslatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GermanTranslatorTest {

    private Translator translator;
    @BeforeEach
    public void setup(){
        translator = TranslatorFactory.createGermanTranslator();
    }

    //Positive Tests:

    @Test
    public void testNumber_1() {
        assertEquals("eins", translator.translateNumber(1));
    }

    @Test
    public void testNumber_5() {
        assertEquals("fünf", translator.translateNumber(5));
    }

    @Test
    public void testNumber_10() {
        assertEquals("zehn", translator.translateNumber(10));
    }

    //Negative Tests:
    @Test
    public void testNumber_0() {
        String result = translator.translateNumber(0);
        assertTrue(result.contains("Übersetzung der Zahl 0 nicht möglich"));
    }

    @Test
    public void testNumber_Minus5() {
        String result = translator.translateNumber(-5);
        assertTrue(result.contains("Übersetzung der Zahl -5 nicht möglich"));
    }

    @Test
    public void testNumber_15() {
        String result = translator.translateNumber(15);
        assertTrue(result.contains("Übersetzung der Zahl 15 nicht möglich"));
    }


}
