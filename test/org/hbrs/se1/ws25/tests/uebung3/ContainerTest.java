package org.hbrs.se1.ws25.tests.uebung3;

import org.hbrs.se1.ws25.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung2.Container;
import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    // private Container container;
    private Container<Member> container;

    @BeforeEach
    void setUp() {
        // Hier muss der Container einmalig als Singleton instanziiert werden.
        container = Container.getInstance();
        container.deleteAllMembers(); // Container vor jedem Test leeren
    }

    @Test
    void testMongoDBNotImplementedSolution() {
        // Set a strategy, which has not been implemented
        // container.setPersistenceStrategie( new PersistenceStrategyMongoDB<Member>() );

        // Testing store
        // Hinweis: Beim Aufruf der Methoden store() oder load() muss eine Exception vom Typ
        // PersistenceException zurückgegeben werden. Der ExceptionType lautet ImplementationNotAvailable
        // Die Message (abrufbar mit der Methode e.getMessage() ) muss einen eindeutigen Text haben, z.B.:
        // assertEquals( e.getMessage() , "MongoDB is not implemented!"  );
        try {
            container.store();
            fail("Es sollte eine UnsupportedOperationException geworfen werden!");
        } catch (UnsupportedOperationException e) {
            assertEquals("Not implemented!", e.getMessage());
        } catch (PersistenceException e) {
            // Falls PersistenceException geworfen wird
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    e.getExceptionTypeType());
            assertTrue(e.getMessage().contains("MongoDB") ||
                    e.getMessage().contains("not implemented"));
        }
    }

    @Test
    void testNoStrategeySet() {
        //
            // container.setPersistenceStrategie(null);
            // container.store();
        // } catch (PersistenceException e) {
            // Bitte auch hier die Message und den ExceptionType prüfen.
        // }
        try {
            container.setPersistenceStrategy(null);
            container.store();
            fail("Es sollte eine PersistenceException geworfen werden!");
        } catch (PersistenceException e) {
            // Bitte auch hier die Message und den ExceptionType prüfen.
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet,
                    e.getExceptionTypeType());
            assertTrue(e.getMessage().contains("Keine Persistenzstrategie") ||
                    e.getMessage().contains("NoStrategyIsSet"));
        }
    }

    @Test
    void testWrongLocationOfFile() {
        // try {
           //  PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            // FileStreams do not like directories, so try this out ;-)
            // strat.setLocation("/Users/saschaalda/tmp");
            // container.setPersistenceStrategie( strat );
            // container.store();

        // } catch (PersistenceException e) {
            // Auch hier diverse Assertions bringen, um die Nachricht und den ExceptionType zu testen.
        // }

        try {
            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            strat.setLocation("/Users/saschaalda/tmp/");
            container.setPersistenceStrategy(strat);

            // Member hinzufügen, damit etwas zu speichern ist
            container.addMember(new ConcreteMember(100));

            container.store();
            fail("Es sollte eine PersistenceException geworfen werden!");

        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable,
                    e.getExceptionTypeType());
            assertNotNull(e.getMessage());
        } catch (ContainerException e) {
            fail("addMember sollte keine Exception werfen: " + e.getMessage());
        }
    }

    @Test
    void testStoreDeleteAndLoad() {
        // Testen Sie folgenden RoundTrip:
        // 1. Lösche alle Member-Objekte (Sicher ist sicher! Dazu die Methode deleteAllMember implementieren!)
        // 2. Setzen der Strategie
        // 3. Hinzufügen eines Member-Objekts
        // 4. Abspeichern
        // 5. Löschen des Member-Objekts
        // 6. Laden
        // Die Zustandsänderungen mittels der size() bitte stets testen!
        try {
            container.deleteAllMembers();
            assertEquals(0, container.size());

            PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
            strat.setLocation("test_members.ser");
            container.setPersistenceStrategy(strat);

            Member testMember = new ConcreteMember(42);
            container.addMember(testMember);
            assertEquals(1, container.size());

            container.store();
            assertEquals(1, container.size());

            String deleteResult = container.deleteMember(42);
            assertTrue(deleteResult.contains("erfolgreich") || deleteResult.contains("entfernt"));
            assertEquals(0, container.size());

            container.load();
            assertEquals(1, container.size());
            assertEquals(42, container.getCurrentList().get(0).getID());

        } catch (ContainerException | PersistenceException e) {
            fail("Es sollte keine Exception geworfen werden: " + e.getMessage());
        }
    }


}