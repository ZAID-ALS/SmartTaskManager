# Verwendeter Prompt zur Projektplanung und Systemarchitektur

```text
Analysiere den aktuellen Smart Task Manager und erstelle nur einen konkreten Plan für die neue Anwendung.

Wichtig:
- Noch keine Dateien erstellen, ändern, verschieben oder löschen.
- Noch keinen Java-Code umbauen.
- Noch kein Frontend erstellen.
- Die Anwendung soll einfach und anfängerfreundlich bleiben.

Festes Ziel:
- Der bisherige Java-Konsolen-Code wird zu einer Spring-Boot-REST-API umgebaut.
- ConsoleUI und der alte Konsolenstart werden am Ende entfernt.
- Task, Priority, TaskStatus, TaskManager und InputValidator sollen möglichst wiederverwendet werden.
- Das Frontend wird später mit Next.js erstellt.
- Frontend und Backend laufen als zwei getrennte Prozesse.
- Die Kommunikation erfolgt über HTTP und JSON.
- Die Aufgaben werden zunächst nur im Arbeitsspeicher gespeichert, ohne Datenbank.
- Die bisherigen Funktionen bleiben:
  - Aufgabe erstellen
  - alle Aufgaben anzeigen
  - Aufgabe löschen
  - Aufgabe als erledigt markieren
  - nach Priorität sortieren
  - nach Fälligkeitsdatum sortieren
- Keine zusätzlichen Funktionen wie Login, Analytics oder Bearbeiten ergänzen.

Zeige mir:
1. die zwei Module der verteilten Anwendung,
2. den Ablauf vom Browser bis zur Aufgabenliste,
3. die geplanten Backend-Schichten,
4. die benötigten Java-Klassen,
5. die geplanten API-Endpunkte,
6. Beispiele für JSON-Anfrage und JSON-Antwort,
7. die endgültige Projektstruktur,
8. wo Separation of Concerns, Single Responsibility, geringe Kopplung und hohe Kohäsion erkennbar sind,
9. welche vorhandenen Dateien behalten, umgebaut oder gelöscht werden.

Gib nur den Plan aus. Führe keine Änderungen aus.
```
