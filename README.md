Manuelle Akzeptanztests (End-to-End)
--
Testfälle: enter
----
Testfall: Gültige Eingabe (Positiv)
- Aktion: enter -> ID: 1, Titel: Test, Gloger-Werte: 3, 3, 2, 3
- Erwartung: "UserStory erfolgreich hinzugefügt (ID: 1)."

Testfall: Doppelte ID (Negativ)
- Aktion: enter -> ID: 1 (erneut)
- Erwartung: "FEHLER: Fehler beim Hinzufügen: ID bereits vorhanden!"

Testfall: Ungültiger Gloger-Wert (Aufwand)
- Aktion: enter -> ID: 2, Aufwand: 0
- Erwartung: "FEHLER: Fehler bei der Erstellung: Aufwand muss größer als 0 sein!"

Testfall: Ungültiger Gloger-Wert (Mehrwert)
- Aktion: enter -> ID: 2, Mehrwert: 9
- Erwartung: "FEHLER: Fehler bei der Erstellung: Mehrwert muss zwischen 1 und 5 liegen!"

Testfall: Falsches Zahlenformat (Negativ)
- Aktion: enter -> ID: abc
- Erwartung: "FEHLER: Fehler bei der Eingabe: For input string: "abc""

Testfälle: dump
----
Voraussetzung: 3 Stories sind im System: ID 1 [Prio 1.2, P1], ID 2 [Prio 2.0, P2], ID 3 [Prio 1.0, P1]

Testfall: dump ohne Filter (Sortierung)
- Aktion: dump
- Erwartung: Zeigt alle 3 Stories. Reihenfolge muss sein: ID 2 (Prio 2.0), ID 1 (Prio 1.2), ID 3 (Prio 1.0).

Testfall: dump mit Filter (Positiv)
- Aktion: dump P1
- Erwartung: Zeigt nur ID 1 und ID 3. Reihenfolge muss sein: ID 1 (Prio 1.2), ID 3 (Prio 1.0).

Testfall: Filter findet nichts (Negativ)
- Aktion: dump NonExistent
- Erwartung: "Keine UserStories vorhanden."

Testfall: dump bei leerer Liste (Grenzwert)
- Aktion: App starten (ohne load) -> dump
- Erwartung: "Keine UserStories vorhanden."

Testfälle: store / load
----
Testfall: Persistenz (Positiv)
- Aktion: enter (ID 1) -> store -> App neu starten -> load -> dump
- Erwartung: "Speichern erfolgreich." -> "Laden erfolgreich." -> dump zeigt Story ID 1.

Testfall: Laden ohne Datei (Negativ)
- Aktion: allStories.ser löschen -> App starten -> load
- Erwartung: "FEHLER: Fehler beim Laden: Datei nicht gefunden..."
