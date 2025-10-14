Aufgabe 2

Welche Nachteile ergeben sich für ein solches Fehlerhandling gegenüber einer Lösung mit Exceptions?
- Aufruf kann den String Rückgabewert einfach ignorieren, bei exceptions muss der Fehler behandelt werden (checked exceptions)
——> Der Fehler könnte einfach unbemerkt bleiben
- Keine Trennung von Erfolg und Fehler. Bei einer Exception wäre klar: Exception = Fehler, Keine Exception = Erfolg --> man müsste also den String-Inhalt parsen um überhaupt zu prüfen ob es ein Fehler war
- Bei Exceptions ist die Fehlerbehandlung standardisiert 
- Normale Rückgabewerte sollten nicht mit Fehlermeldungen vermischt werden, kein robuster code
