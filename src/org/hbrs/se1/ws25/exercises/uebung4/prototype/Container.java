package org.hbrs.se1.ws25.exercises.uebung4.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2025, Version 1.2
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (VORHER: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Wie bewerten Sie diese Strategie? Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities)
 * sind in einer Klasse, d.h. Container?
 * ToDo: Wie bewerten Sie diese Entscheidung? Was wäre ein sinnvolle Aufteilung (F2, F6)
 * 
 */

public class Container {

    // Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
    private List<UserStory> liste = null;

    // Singleton-Implementierung (F1 - Eager und thread-safe laut Übungsstunde)
    private static Container instance = new Container();

    // Referenz auf die Speicher-Strategie (Anforderung US 3)
    private PersistenceStrategy strategy = null;

    // LOCATION habe ich entfernt und in PersistenceStrategyStream eingefügt

    // Hier einfach Singleton Instance locker flockig
    public static Container getInstance() {
        return instance;
    }

    // Konstruktor musste laut Übungsstunde privat sein
    private Container(){
        liste = new ArrayList<UserStory>();
    }


    // Diese Methoden habe ich alle gelöscht und woanders eingefügt damit wir kein God Object mehr hier haben:
    // main() ist jetz in Input Klasse
    // startEingabe() ist jetzt auch in Input
    // startAusgabe() ist in Output Klasse


    // StrategyMethodPatetrn
    public void setPersistenceStrategy(PersistenceStrategy strategy) {
        this.strategy = strategy;
    }


    // ObjectOutputStream ist jetzt in PersistenceStrategyStream

    public void store() throws ContainerException {
        if (strategy == null) {
            throw new ContainerException("Keine Speicherstrategie (PersistenceStrategy) gesetzt!");
        }
        strategy.save(this.liste);
    }

    // Lädt die Liste mit Strategie, wirft exception wenn keine Strategie existiert
    public void load() throws ContainerException {
        if (strategy == null) {
            throw new ContainerException("Keine Ladestrategie (PersistenceStrategy) gesetzt!");
        }
        // Empfängt die geladene Liste von der Strategie
        // Korrektur: Wirft Exception statt "LOG" auszugeben
        this.liste = strategy.load();
    }


    // Methode zum hinzufügen einer UserStory, prüft vorher mit ID ob schon existiert mit contains()
    public void addUserStory ( UserStory userStory ) throws ContainerException {
        if ( contains(userStory) == true ) {
            ContainerException ex = new ContainerException("ID bereits vorhanden!");
            ex.addID(userStory.getId()); // Nutzt die Methode aus ContainerException
            throw ex;
        }
        liste.add(userStory);
    }

    // prüft ob ID schon vorhanden

    private boolean contains( UserStory userStory) {
        int ID = userStory.getId();
        for ( UserStory userStory1 : liste) {
            if ( userStory1.getId() == ID ) {
                return true;
            }
        }
        return false;
    }

    // Anzahl an userStory Objekten
    public int size() {
        return liste.size();
    }

    // Rückgabe der aktuellen Liste
    public List<UserStory> getCurrentList() {
        return this.liste;
    }

    // Methode um eine bestimmte UserStory anhand der ID zurückzugeben
    // (falls später notwendig sonst einfach nice-to-have)
    private UserStory getUserStory(int id) {
        for ( UserStory userStory : liste) {
            if (id == userStory.getId() ){
                return userStory;
            }
        }
        return null;
    }
}
