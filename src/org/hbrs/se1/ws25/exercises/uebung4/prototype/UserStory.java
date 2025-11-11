package org.hbrs.se1.ws25.exercises.uebung4.prototype;


public class UserStory implements java.io.Serializable, Comparable<UserStory> {
        // ToDo: Sind die Attribute der Klasse UserStory vollständig? Wie sieht es mit den
        //  Sichtbarkeiten aus? (F4)

        private String titel;
        private int id = 0;
        private double prio;
        private String project;

        private String akzeptanzkriterium;
        private double mehrwert;
        private double strafe;
        private double aufwand;
        private double risiko;

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public UserStory(int id, String titel, String akzeptanzkriterium,
                         double mehrwert, double strafe, double aufwand,
                         double risiko, String project) throws IllegalArgumentException {
            this.id = id;
            this.titel = titel;
            this.prio = prio;
            this.akzeptanzkriterium = akzeptanzkriterium;
            this.project = project;
            setKennzahlen(mehrwert, strafe, aufwand, risiko);

        }

        private void setKennzahlen(double mehrwert, double strafe, double aufwand, double risiko) {
            if (mehrwert < 1 || mehrwert > 5)
                throw new IllegalArgumentException("Mehrwert muss zwischen 1 und 5 liegen!");
            if (strafe < 1 || strafe > 5)
                throw new IllegalArgumentException("Strafe muss zwischen 1 und 5 liegen!");
            if (risiko < 1 || risiko > 5)
                throw new IllegalArgumentException("Risiko muss zwischen 1 und 5 liegen!");
            if (aufwand <= 0)
                throw new IllegalArgumentException("Aufwand muss größer als 0 sein!");

            this.mehrwert = mehrwert;
            this.strafe = strafe;
            this.aufwand = aufwand;
            this.risiko = risiko;

            berechnePrio();
        }

        private void berechnePrio() {
            this.prio = (mehrwert + strafe) / (aufwand + risiko);
        }

        public UserStory() {
        }

        public double getPrio() {
            return prio;
        }

        public void setPrio(double prio) {
            this.prio = prio;
        }

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public String getAkzeptanzkriterium() { return akzeptanzkriterium; }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(UserStory other) {
            // Absteigend nach Prio
            return Double.compare(other.prio, this.prio);
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Titel: " + titel + ", Projekt: " +  project + ", Prio: " + prio + " Mehrwert: " +
            mehrwert + ", Strafe: " + strafe + ", Aufwand: " +  aufwand + ", Risiko: " + risiko;
        }

    }




