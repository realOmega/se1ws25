package org.hbrs.se1.ws25.tests.uebung4;

import org.hbrs.se1.ws25.exercises.uebung4.prototype.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.Container;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    private Container container;
    private UserStory us1;

    @BeforeEach
    void setUp() {
        // 'container' ist ein Singleton, aber wir holen die Instanz
        container = Container.getInstance();
        // Wir können nicht garantieren, dass er leer ist, wenn Tests parallel laufen.
        // Für echte Tests bräuchte man einen 'reset()'-Mechanismus.
        // Für diese Übung testen wir die Methoden direkt.

        us1 = new UserStory(1, "Test 1", "...", 1, 1, 1, 1, "P1");
    }

    @Test
    void testSingletonInstance() {
        // Testet die Singleton-Anforderung (F1)
        Container c1 = Container.getInstance();
        Container c2 = Container.getInstance();
        assertSame(c1, c2, "Container.getInstance() muss dieselbe Instanz zurückliefern.");
    }

    @Test
    void testAddUserStoryDuplicateID() {
        // Testet die Anforderung aus Container.java

        // Annahme: ID 500 ist noch nicht im Singleton
        UserStory us500 = new UserStory(500, "Test 500", "...", 1, 1, 1, 1, "P1");

        assertDoesNotThrow(() -> {
            container.addUserStory(us500); // Erster Versuch (sollte klappen)
        });

        // Zweiter Versuch (sollte fehlschlagen)
        assertThrows(ContainerException.class, () -> {
            container.addUserStory(us500);
        }, "Das Hinzufügen einer doppelten ID muss eine ContainerException werfen.");
    }
}
