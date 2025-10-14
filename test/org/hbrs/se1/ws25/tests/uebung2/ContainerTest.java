package org.hbrs.se1.ws25.tests.uebung2;

import org.hbrs.se1.ws25.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung2.Container;
import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    // 1
    @Test
    public void testEmptyContainer() {
        Container container = new Container();

        assertEquals(0, container.size(), "Container sollte leer sein");
    }
    // 2
    @Test
    public void testSingleMember() throws ContainerException {
        Container container = new Container();
        Member m1 = new ConcreteMember(1);

        container.addMember(m1);

        assertEquals(1, container.size(), "Container sollte 1 Objekt enthalten");
    }
    // 3
    @Test
    public void testAddMultipleMembers() throws ContainerException {
        // Äquivalenzklasse: Übergang zu mehreren Objekten
        Container container = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);
        Member m3 = new ConcreteMember(3);

        container.addMember(m1);
        container.addMember(m2);
        container.addMember(m3);

        // Assertion 3: Container hat 3 Objekte
        assertEquals(3, container.size(), "Container sollte 3 Objekte enthalten");
    }
    // 4 und 5
    @Test
    public void testAddDuplicateMember() {
        // Exception bei gleicher ID
        Container container = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(1); // Gleiche ID!

        try {
            container.addMember(m1);
            container.addMember(m2); // Sollte Exception werfen
            fail("ContainerException sollte geworfen werden");
        } catch (ContainerException e) {
            // Assertion 4: Exception wird geworfen
            assertTrue(e.getMessage().contains("bereits vorhanden"),
                    "Exception-Nachricht sollte 'bereits vorhanden' enthalten");
            // Assertion 5: Container hat immer noch nur 1 Objekt
            assertEquals(1, container.size(), "Container sollte nur 1 Objekt haben");
        }
    }
    // 6 und 7
    @Test
    public void testDeleteExistingMember() throws ContainerException {
        // Äquivalenzklasse: Übergang von 2 zu 1 Objekt
        Container container = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);

        container.addMember(m1);
        container.addMember(m2);

        String result = container.deleteMember(1);

        // Assertion 6: Löschen war erfolgreich
        assertTrue(result.contains("erfolgreich") || result.contains("gelöscht"),
                "Erfolgsmeldung sollte zurückgegeben werden");

        // Assertion 7: Container hat jetzt 1 Objekt
        assertEquals(1, container.size(), "Container sollte 1 Objekt enthalten");
    }

    // 8 und 9
    @Test
    public void testDeleteNonExistingMember() throws ContainerException {
        // Nicht-existierende ID löschen
        Container container = new Container();
        Member m1 = new ConcreteMember(1);

        container.addMember(m1);

        String result = container.deleteMember(42); //42 (Die Antwort)

        // Assertion 8: Fehlermeldung wird zurückgegeben
        assertTrue(result.contains("nicht gefunden") || result.contains("nicht"),
                "Fehlermeldung sollte zurückgegeben werden");

        // Assertion 9: Container hat immer noch 1 Objekt
        assertEquals(1, container.size(), "Container sollte noch 1 Objekt haben");
    }
    // 10 und 11
    @Test
    public void testDeleteFromEmptyContainer() {

        Container container = new Container();

        String result = container.deleteMember(1);

        // Assertion 10: Fehlermeldung wird zurückgegeben
        assertTrue(result.contains("nicht gefunden") || result.contains("nicht"),
                "Fehlermeldung bei leerem Container");

        // Assertion 11: Container ist immer noch leer
        assertEquals(0, container.size(), "Container sollte leer bleiben");
    }
    // 12
    @Test
    public void testDeleteAllMembers() throws ContainerException {
        // Äquivalenzklasse: Übergang von mehreren Objekten zu leer
        Container container = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);

        container.addMember(m1);
        container.addMember(m2);

        container.deleteMember(1);
        container.deleteMember(2);

        // Assertion 12: Container ist wieder leer
        assertEquals(0, container.size(), "Container sollte leer sein");
    }

}
