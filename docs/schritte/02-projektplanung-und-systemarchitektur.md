# Projektplanung und Systemarchitektur

## Ziel

Die bestehende Java-Konsolenanwendung wird zu einer verteilten Webanwendung umgebaut. Das Frontend übernimmt später die Bedienung, während das Backend die Aufgaben verwaltet und über eine REST-API bereitstellt.

## Module

- Next.js-Frontend auf Port 3000
- Spring-Boot-Backend auf Port 8080

## Funktionsumfang

- Aufgabe erstellen
- Aufgaben anzeigen
- Aufgabe löschen
- Aufgabe abschließen
- nach Priorität sortieren
- nach Fälligkeitsdatum sortieren

Es werden keine zusätzlichen Funktionen wie Login, Analytics, Bearbeiten oder eine Datenbank ergänzt.

## Backend-Schichten

- Controller
- Service mit `TaskManager`
- Model
- DTO
- Validation
- zentrale Fehlerbehandlung

## Wiederverwendung

- `Task`, `Priority` und `TaskStatus` werden als fachliches Modell behalten und in die neue Paketstruktur verschoben.
- `TaskManager` wird zum Service umgebaut und verwaltet weiterhin die Aufgaben im Arbeitsspeicher.
- `InputValidator` wird für die Validierung der REST-Eingaben angepasst.
- `TaskManagerManualTest` wird später durch automatische JUnit-Tests ersetzt.
- `ConsoleUI` und der bisherige Konsolenstart in `Main` werden später gelöscht und durch den Spring-Boot-Start ersetzt.

## API-Endpunkte

- `POST /api/tasks`
- `GET /api/tasks`
- `GET /api/tasks?sort=priority`
- `GET /api/tasks?sort=dueDate`
- `PATCH /api/tasks/{id}/complete`
- `DELETE /api/tasks/{id}`

## Kursbegriffe

- **Separation of Concerns:** Das Next.js-Frontend ist für die Darstellung zuständig, das Spring-Boot-Backend für die Aufgabenverwaltung und die REST-API.
- **Single Responsibility Principle:** Jede Klasse erhält eine klar abgegrenzte Aufgabe, zum Beispiel verarbeitet der Controller HTTP-Anfragen und der `TaskManager` die Geschäftslogik.
- **Schichten:** Controller, Service, Model, DTO, Validation und Fehlerbehandlung trennen die verschiedenen Verantwortungsbereiche des Backends.
- **Komponenten:** Browser, Frontend, Controller, `TaskManager` und In-Memory-Aufgabenliste sind eigenständige Bausteine des Gesamtsystems.
- **Geringe Kopplung:** Frontend und Backend kennen nur die vereinbarte HTTP- und JSON-Schnittstelle und können getrennt entwickelt werden.
- **Hohe Kohäsion:** Zusammengehörige Aufgaben bleiben gebündelt, beispielsweise alle Aufgabenoperationen im `TaskManager`.

## Diagramm

[Systemarchitektur öffnen](../diagrams/systemarchitektur.md)

## Persönliche Entscheidung

Ich habe mich bewusst für zwei getrennte Anwendungen und eine Speicherung im Arbeitsspeicher entschieden. Dadurch bleibt das Projekt übersichtlich und ich kann den Aufbau und die Kommunikation selbst erklären.

## Links

- [Verwendeten Prompt öffnen](../prompts/02-projektplanung-und-systemarchitektur.md)
- [Zurück zur Haupt-README](../../README.md)
