package org.hbrs.se1.ws25.exercises.uebung9;

public class TestClient {

    public static void main(String[] args ) {
        // Text aus Aufgabenstellung/Datei

        // doc2
        String text2 = "Die Klausur im Fach SE findet bald im März 2026 statt!";
        TextDocument doc2 = new TextDocument(text2, Encoding.UTF16);
        doc2.setId(2);

        // doc5
        String text5 = "Software Engineering I ist eine Vorlesung in den Studiengaengen BWI und BCSP!";
        TextDocument doc5 = new TextDocument(text5, Encoding.UTF32);
        doc5.setId(5);

        // doc4
        GraficDocument doc4 = new GraficDocument("localhost:8080");
        doc4.setId(4);

        // doc3: Hat doc4 und doc5 als Inhalt
        ComplexDocument doc3 = new ComplexDocument();
        doc3.setId(3);
        doc3.addDocument(doc4);
        doc3.addDocument(doc5);

        // doc0: Hat doc2 und doc3 als Inhalt
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setId(0);
        doc0.addDocument(doc2);
        doc0.addDocument(doc3);

        System.out.println("Roboburgerking");
        // Rekursion
        long totalBytes = doc0.getSize();
        System.out.println("Total Bytes : " + totalBytes);
    }
}
