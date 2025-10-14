package org.hbrs.se1.ws25.exercises.uebung2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    protected List<Member> members;

    public Container() {
        members = new ArrayList<>();
    }

    public void addMember(Member member) throws ContainerException {
        for (Member existingMember : members) {
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

    public void dump() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public int size() {
        return this.members.size();
    }
}
