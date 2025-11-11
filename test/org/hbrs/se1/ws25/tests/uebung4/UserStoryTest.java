package org.hbrs.se1.ws25.tests.uebung4;

import org.hbrs.se1.ws25.exercises.uebung4.prototype.UserStory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Test
public class UserStoryTest {
    @Test
    void testPrioBerechnung() {
        // Testet die Prio-Formel (Mehrwert + Strafe) / (Aufwand + Risiko)
        UserStory us = new UserStory(1, "Test", "...", 5, 5, 3, 2, "P1");
        // (5+5) / (3+2) = 10 / 5 = 2.0
        assertEquals(2.0, us.getPrio());
    }

    @Test
    void testPrioBerechnungMitNull() {
        // Testet den Divisor-Schutz
        // (5+5) / (0+0)
        // Sie wirft eine Exception, da aufwand > 0 sein muss
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStory(1, "Test", "...", 5, 5, 0, 1, "P1");
        });
    }

    @Test
    void testGlogerValidierungNegativAufwand() {
        // Testet die Anforderung aus UserStory.java
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStory(1, "Test", "...", 5, 5, 0, 2, "P1");
        }, "Aufwand muss größer als 0 sein!");
    }

    @Test
    void testGlogerValidierungNegativMehrwert() {
        // Testet die Anforderung aus UserStory.java
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStory(1, "Test", "...", 9, 5, 2, 2, "P1");
        }, "Mehrwert muss zwischen 1 und 5 liegen!");
    }

    @Test
    void testCompareToSorting() {
        // Testet die compareTo-Methode
        UserStory usLow = new UserStory(1, "Niedrig", "...", 1, 1, 2, 2, "P1"); // Prio = 0.5
        UserStory usHigh = new UserStory(2, "Hoch", "...", 5, 5, 2, 2, "P1"); // Prio = 2.5
        UserStory usMid = new UserStory(3, "Mittel", "...", 3, 3, 2, 2, "P1"); // Prio = 1.5

        List<UserStory> list = new ArrayList<>();
        list.add(usLow);
        list.add(usHigh);
        list.add(usMid);

        // Sortieren (nutzt UserStory.compareTo)
        Collections.sort(list);

        // Erwartet: Hoch (2.5), Mittel (1.5), Niedrig (0.5) (da absteigend sortiert)
        assertEquals(2, list.get(0).getId()); // Hoch
        assertEquals(3, list.get(1).getId()); // Mittel
        assertEquals(1, list.get(2).getId()); // Niedrig
    }
}
