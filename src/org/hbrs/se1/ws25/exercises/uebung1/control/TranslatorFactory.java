package org.hbrs.se1.ws25.exercises.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TranslatorFactory {
    public static Translator createGermanTranslator() {
        GermanTranslator translator = new GermanTranslator();
        String date = DateTimeFormatter.ofPattern("MMM,yyyy") //dynamisches Datum, aber auch
                        .format(LocalDate.now());            // in simpel möglich: String "Okt/2025"

        translator.setDate(date);
        return translator;
    }
}
