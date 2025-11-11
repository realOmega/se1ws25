package org.hbrs.se1.ws25.exercises.uebung4.prototype;
import org.hbrs.se1.ws25.exercises.uebung4.prototype.UserStory;
import java.util.List;
import java.util.stream.Stream;

public class Output {

    public void printHelp() {
        showMessage("Folgende Befehle stehen zur Verfuegung:");
        showMessage("  enter - Neue User Story eingeben");
        showMessage("  store - Alle User Stories speichern (US 3)");
        showMessage("  load  - User Stories laden (US 3)");
        showMessage("  dump [projekt] - User Stories anzeigen, optional gefiltert (F5)");
        showMessage("  exit  - Programm beenden");
        showMessage("  help  - Diese Hilfe anzeigen");
    }


    // Gibt User Stories aus und filtert/sortiert mit Streams
    public void printUserStories(List<UserStory> stories, String filterProject) {
        if (stories.isEmpty()) {
            showMessage("Keine UserStories vorhanden.");
            return;
        }

        showMessage("Ausgabe der User Stories");

        // Baue den Stream auf (F5)
        Stream<UserStory> stream = stories.stream();

        // 1. Filtern (Anforderung F5 / "dump projekt...")
        if (filterProject != null && !filterProject.isEmpty()) {
            stream = stream.filter(story ->
                    filterProject.equalsIgnoreCase(story.getProject())
            );
        }

        // 2. Sortieren (Anforderung F4) und Ausgeben
        // .sorted() nutzt die compareTo-Methode aus deiner UserStory.java
        stream.sorted()
                .forEach(story ->
                        showMessage(story.toString()) // Nutzt deine toString-Methode
                );

        showMessage("---------------------------------");
    }


    // HILFSMETHODEN für saubere Konsolenausgabe

    public void showPrompt() {
        System.out.print("> ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.err.println("FEHLER: " + errorMessage);
    }
}
