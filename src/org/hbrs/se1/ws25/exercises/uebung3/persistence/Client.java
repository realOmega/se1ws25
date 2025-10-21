package org.hbrs.se1.ws25.exercises.uebung3.persistence;

import org.hbrs.se1.ws25.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws25.exercises.uebung2.Container;
import org.hbrs.se1.ws25.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws25.exercises.uebung2.Member;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        // Container-Instanz holen
        Container<Member> container = Container.getInstance();

        // Persistenz-Strategie setzen (nur in Main erlaubt!)
        PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
        container.setPersistenceStrategy(strategy);

        // Member-Objekte erzeugen und hinzufügen
        try {
            container.addMember(new ConcreteMember(1));
            container.addMember(new ConcreteMember(2));
            container.addMember(new ConcreteMember(3));
        } catch (ContainerException e) {
            System.err.println(e.getMessage());
        }

        // Liste auslesen
        List<Member> memberList = container.getCurrentList();

        // MemberView für Ausgabe nutzen
        MemberView view = new MemberView();
        view.dump(memberList);
    }
}
