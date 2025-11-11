package org.hbrs.se1.ws25.exercises.uebung4.prototype;
import java.util.List;

//Interface für das Persistence Strategy Pattern (Anforderung US 3)
//Definiert, was jede Speicherstrategie können muss.
public interface PersistenceStrategy {

    //Speichert eine Liste von UserStory Objekten
    void save(List<UserStory> list) throws ContainerException;

    //Lädt eine Liste von UserStory Objekten
    List<UserStory> load() throws ContainerException;
}
