package org.hbrs.se1.ws25.exercises.uebung2;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws25.exercises.uebung3.persistence.PersistenceStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container<T extends Member> implements Serializable {

    private static Container<?> instance;
    private List<T> members;
    private PersistenceStrategy<T> persistenceStrategy;

    private Container() {
        members = new ArrayList<>();
    }
    public static synchronized <T extends Member> Container<T> getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return (Container<T>) instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<T> strategy) {
        this.persistenceStrategy = strategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Keine Persistenzstrategie gesetzt!");
        }
        persistenceStrategy.save(members);
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Keine Persistenzstrategie gesetzt!");
        }
        List<T> loadedMembers = persistenceStrategy.load();
        if (loadedMembers != null) {
            members = loadedMembers; // alte Liste überschreiben
        }
    }

    public void addMember(T member) throws ContainerException {
        for (T existingMember : members) {
            if (Objects.equals(existingMember.getID(), member.getID())) {
                throw new ContainerException(
                        "Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        this.members.add(member);
    }

    public String deleteMember(Integer id) {
        for (int i = 0; i < members.size(); i++) {
            if (Objects.equals(members.get(i).getID(), id)) {
                this.members.remove(i);
                return "Member mit der ID " + id + " wurde erfolgreich entfernt.";
            }
        }
        return "Member mit ID " + id + " nicht gefunden!";
    }

    public void deleteAllMembers() {
        members.clear();
    }


    public List<T> getCurrentList() {
        return new ArrayList<>(members); // Defensive Kopie zurückgeben
    }


    public int size() {
        return this.members.size();
    }


}
