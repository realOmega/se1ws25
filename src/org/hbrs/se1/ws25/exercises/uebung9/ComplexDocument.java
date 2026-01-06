package org.hbrs.se1.ws25.exercises.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends Document {

    private List<Document> children = new ArrayList<>();

    public void addDocument(Document d) {
        this.children.add(d);
    }
    public void removeDocument(Document d) {
        this.children.remove(d);
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        // Durch alle Kinder (egal welcher Typ) iterieren und aufsummieren
        for (Document d : children) {
            totalSize += d.getSize();
        }
        return  totalSize;
    }
}
