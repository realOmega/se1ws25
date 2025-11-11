package org.hbrs.se1.ws25.exercises.uebung1.control;


public class TranslatorFactory {

    public static Translator createGermanTranslator() {
        GermanTranslator translator = new GermanTranslator();
        String date = "Okt/2025";

        translator.setDate(date);
        return translator;
    }
}
