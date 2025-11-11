package org.hbrs.se1.ws25.exercises.uebung2;

public class ConcreteMember implements Member{
    int id;

    @Override
    public Integer getID() {
        return id;
    }

    public ConcreteMember(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member (ID =" + id + ") ";
    }

}
