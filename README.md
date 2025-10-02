Frage 1: Was ist der Vorteil einer separaten Test-Klasse?
Antwort. Es gibt verschiedene Vorteile:
- Code sieht sauberer aus
- Code für die Produktion und Code für den Test ist getrennt und unabhängig
voneinander
- Ein mal geschrieben kann ein Test mehrfach ausgeführt werden, z.B in
Zukunft nach
Änderungen am Code
- Ist übersichtlicher
- Einfachere Integration von Testframeworks wie Unit

Frage 2: Was ist bei einem Blackbox-Test der Sinn von Äquivalenzklassen?
- Äquivalenzklasse ist eine Menge von Eingabewerten, die vom System gleich
behandelt
Werden —> also: die Anzahl an nötigen Testfällen wird reduziert, nicht jede
Eingabe muss
getestet werden.
- Testet repräsentativ das Systemverhalten gültige/ungültige Eingaben


Frage 3: Warum ist ein Blackbox-Test mit JUnit auf der Klasse Client
nicht unmittelbar durchführbar?
- Methode display(int aNumber) gibt nur auf der Konsole aus
(System.out.println) und liefert
keinen return-Wert
- Blackbox-Tests prüfen normalerweise Funktionen über Rückgabewerte/
Zustandsänderungen
Mögliche Lösungen:
- code so umbauen, dass display einen String zurückgibt, der getestet werden
kann.
