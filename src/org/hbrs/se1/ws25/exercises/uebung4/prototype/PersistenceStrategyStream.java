package org.hbrs.se1.ws25.exercises.uebung4.prototype;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream implements PersistenceStrategy {

    // Die Konstante für den Dateipfad ist von Container gelöscht und hier eingefügt
    final static String LOCATION = "allStories.ser";


    //Die alte store() Methode aus Container
    @Override
    public void save(List<UserStory> list) throws ContainerException {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(LOCATION);
            oos = new ObjectOutputStream(fos);

            // Speichert die übergebene 'list' anstelle von 'this.liste'
            oos.writeObject(list);
            System.out.println(list.size() + " UserStory wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            e.printStackTrace();
            // Wirft die Exception an den Controller weiter (F7)
            throw new ContainerException("Fehler beim Abspeichern: " + e.getMessage());
        } finally {
            // Streams sicher schließen
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                // Ignorieren beim Schließen, da der ursprüngliche Fehler wichtiger ist
            }
        }
    }


    //Die alte load() Methode aus Container
    @Override
    public List<UserStory> load() throws ContainerException {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(LOCATION);
            ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                // KORREKTUR: Gibt die geladene Liste zurück,
                // statt sie 'this.liste' zuzuweisen
                List<UserStory> loadedList = (List<UserStory>) obj;
                System.out.println("Es wurden " + loadedList.size() + " UserStory erfolgreich reingeladen!");
                return loadedList;
            } else {
                throw new ContainerException("Geladene Datei hat ein unerwartetes Format.");
            }
        }
        catch (IOException e) {
            // KORREKTUR (F7): "LOG..."-Meldung ersetzt durch
            // eine Exception, die der Controller (Input.java) fangen kann.
            throw new ContainerException("Datei nicht gefunden oder lesbar. Lade mit leerer Liste. (Fehler: " + e.getMessage() + ")");
        }
        catch (ClassNotFoundException e) {
            // KORREKTUR (F7): "LOG..."-Meldung ersetzt durch Exception.
            throw new ContainerException("Fehler beim Laden (Klasse nicht gefunden): " + e.getMessage());
        }
        finally {
            // Streams sicher schließen
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                // Ignorieren beim Schließen
            }
        }
    }
}
