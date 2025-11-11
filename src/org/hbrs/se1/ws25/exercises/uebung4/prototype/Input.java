package org.hbrs.se1.ws25.exercises.uebung4.prototype;

import org.hbrs.se1.ws25.exercises.uebung4.prototype.PersistenceStrategy;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.PersistenceStrategyStream;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.Container;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.UserStory;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.Output;

import java.util.Scanner;

public class Input {

    private Container container = Container.getInstance();
    private Output ausgabe = new Output();
    private Scanner scanner = new Scanner(System.in);

    // Hält die Strategie (Anforderung US 3)
    private PersistenceStrategy strategy = new PersistenceStrategyStream();

    // Ersatz zum alten main() aus Container
    public static void main(String[] args) {
        Input controller = new Input();
        // Setzt die Strategie im Container (Anforderung US 3)
        controller.container.setPersistenceStrategy(controller.strategy);
        // Startet die Hauptschleife
        controller.startEingabe();
    }


    public void startEingabe() {
        ausgabe.showMessage("UserStory-Tool V2.0 (Refactored MVC)");

        // Automatisches Laden beim Start mit Fehlerbehandlung (F7)
        handleLoad();

        ausgabe.printHelp();

        while (true) {
            ausgabe.showPrompt();
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();

            // Korrektur: if/else if statt mehrerer 'if's da sonst Endlosschleife (in Übung besprochen)
            if (command.equals("exit")) {
                ausgabe.showMessage("Programm wird beendet.");
                break; // Schleife verlassen
            } else if (command.equals("help")) {
                ausgabe.printHelp();
            } else if (command.equals("dump")) {
                handleDump(parts);
            } else if (command.equals("enter")) {
                handleEnter();
            } else if (command.equals("store")) {
                handleStore();
            } else if (command.equals("load")) {
                handleLoad();
            } else if (!command.isEmpty()) {
                ausgabe.showError("Unbekannter Befehl. Tippe 'help' für eine Liste.");
            }
        }
        scanner.close();
    }


    private void handleEnter() {
        try {
            // Die Eingabelogik ist jetzt hier
            UserStory us = readUserStoryFromConsole(scanner);

            // Fängt Fehler aus dem UserStory-Konstruktor
            // und aus dem Container (doppelte ID) ab.
            container.addUserStory(us);
            ausgabe.showMessage("UserStory erfolgreich hinzugefügt (ID: " + us.getId() + ").");

        } catch (ContainerException | IllegalArgumentException e) {
            // Fängt alle Fehler ab: doppelte ID, ungültige Gloger-Werte, Tippfehler
            ausgabe.showError("Fehler bei der Eingabe: " + e.getMessage());
        }
    }

    // Pattern Chain of Responsibility: handle  Methoden für store(), load(), dump()

    private void handleStore() {
        try {
            container.store();
            ausgabe.showMessage("Speichern erfolgreich.");
        } catch (ContainerException e) {
            ausgabe.showError("Fehler beim Speichern: " + e.getMessage());
        }
    }


    private void handleLoad() {
        try {
            container.load();
            ausgabe.showMessage("Laden erfolgreich. " + container.size() + " Stories geladen.");
        } catch (ContainerException e) {
            ausgabe.showError("Fehler beim Laden: " + e.getMessage());
        }
    }


    private void handleDump(String[] parts) {
        String filterProject = null;
        if (parts.length > 1) {
            filterProject = parts[1];
            ausgabe.showMessage("Filtere nach Projekt: " + filterProject);
        }
        ausgabe.printUserStories(container.getCurrentList(), filterProject);
    }


    // Liest die Daten für eine UserStory von der Konsole.
    // Wirft Exceptions, wenn etwas schiefläuft (Zahlformat, Gloger-Validierung)
    private UserStory readUserStoryFromConsole(Scanner scanner)
            throws NumberFormatException, IllegalArgumentException {

        int id = readInt(scanner, "ID: ");
        ausgabe.showMessage("Titel: ");
        String titel = scanner.nextLine().trim();
        ausgabe.showMessage("Akzeptanzkriterium (F4): ");
        String kriterium = scanner.nextLine().trim();
        ausgabe.showMessage("Projekt: ");
        String projekt = scanner.nextLine().trim();

        // Liest die Gloger-Werte ein
        double mw = readDoubleSimple(scanner, "Mehrwert (1-5): ");
        double st = readDoubleSimple(scanner, "Strafe (1-5): ");
        double au = readDoubleSimple(scanner, "Aufwand (>0): ");
        double ri = readDoubleSimple(scanner, "Risiko (1-5): ");

        // Die Validierung (negative Zahlen etc.) passiert jetzt im
        // UserStory-Konstruktor, wie in UserStory implementiert.
        return new UserStory(id, titel, kriterium, mw, st, au, ri, projekt);
    }


    // Hilfsmethoden um einfacher integer und double einzulesen
    private int readInt(Scanner sc, String prompt) throws NumberFormatException {
        ausgabe.showMessage(prompt);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private double readDoubleSimple(Scanner sc, String prompt) throws NumberFormatException {
        ausgabe.showMessage(prompt);
        return Double.parseDouble(sc.nextLine().trim());
    }
}
