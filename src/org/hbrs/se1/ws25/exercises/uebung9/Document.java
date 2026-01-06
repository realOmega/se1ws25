package org.hbrs.se1.ws25.exercises.uebung9;

public abstract class Document {

    private int id;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public abstract long getSize();
}