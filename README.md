# Smart Task Manager

## Projektbeschreibung

Der Smart Task Manager ist ein persönlicher To-do-Manager. Die Anwendung besitzt ein separates Next.js-Frontend und ein separates Spring-Boot-Backend. Benutzer können Aufgaben erstellen, anzeigen, als erledigt markieren, soft löschen sowie nach Priorität oder Fälligkeitsdatum sortieren.

Das Projekt wurde im Rahmen der Vibe-Coding-Aufgabe entwickelt. Planung, GUI-Prototyp, Backend, Frontend, Kommunikation, Gesamtsystem und Werkzeugnutzung wurden in acht kleinen, nachvollziehbaren Markdown-Schritten dokumentiert.

## Zuordnung zur Aufgabenstellung

| Teil | Anforderung | Umsetzung | Nachweis |
|---|---|---|---|
| Teil A – GUI | Eine GUI für die Projektidee erstellen und Prompts sowie Screenshots dokumentieren | Mit Google Stitch wurden ein Dashboard und eine Create-Task-Ansicht entworfen. Das Stitch-Design diente anschließend als Grundlage für das Next.js-Frontend. | [GUI-Prototyp mit Google Stitch](docs/schritte/01-gui-prototyp-mit-google-stitch.md), [verwendete Stitch-Prompts](docs/prompts/01-google-stitch.md) |
| Teil B – Pet-Projekt | Ein mittelgroßes persönliches Pet-Projekt umsetzen | Der Smart Task Manager wurde von der Planung über Backend und Frontend bis zur funktionierenden Kommunikation und Systemprüfung umgesetzt. | [Planung](docs/schritte/02-projektplanung-und-systemarchitektur.md), [Backend-Struktur](docs/schritte/03-java-backend-struktur.md), [REST-API](docs/schritte/04-rest-api-und-tests.md), [Frontend](docs/schritte/05-nextjs-frontend.md), [Kommunikation](docs/schritte/06-frontend-backend-kommunikation.md), [Gesamtsystem](docs/schritte/07-gesamtsystem-und-fehlerfaelle.md) |
| Teil C – Verteilte Anwendung und Werkzeuge | Eine verstandene verteilte Anwendung mit getrennten Modulen entwickeln sowie CLI und VS-Code-Clone nachweisen | Frontend und Backend laufen als getrennte Prozesse auf Port 3000 und 8080 und kommunizieren über HTTP und JSON. Cursor wurde für eine Codeänderung, die Codex CLI für eine Projektanalyse verwendet. | [Cursor und Codex CLI](docs/schritte/08-codex-cli-und-cursor.md), [Systemarchitektur](docs/diagrams/systemarchitektur.md) |

## Hauptfunktionen

- Aufgabe erstellen
- Aufgaben anzeigen
- Aufgabe als erledigt markieren
- Aufgabe soft löschen
- nach Priorität sortieren
- nach Fälligkeitsdatum sortieren
- Ladezustand anzeigen
- Leerzustand anzeigen
- Erfolgsmeldungen anzeigen
- verständliche Fehlermeldungen anzeigen
- Fehler anzeigen, wenn das Backend nicht erreichbar ist

Eine Suche, Bearbeitungsfunktion, Anmeldung, Datenbank oder Statistikfunktion ist nicht Bestandteil des Projekts.

## Verwendete Technologien

| Bereich | Technologie |
|---|---|
| GUI-Prototyp | Google Stitch |
| Frontend | Next.js 16, React 19, TypeScript und normales CSS |
| Backend | Java 21 und Spring Boot 4.1.0 |
| Backend-Build | Maven |
| Frontend-Build | npm |
| Schnittstelle | REST, HTTP und JSON |
| Tests | Spring Boot Test, JUnit und MockMvc |
| Werkzeuge | Codex in der VS-Code-Arbeitsumgebung, Cursor und OpenAI Codex CLI 0.144.1 |
| Versionsverwaltung | Git und GitHub |

## Verteilte Architektur

`frontend/` und `backend/` sind getrennte Module und werden als eigene Prozesse gestartet. Das Frontend läuft unter `http://localhost:3000`. Das Backend stellt seine REST-API unter `http://localhost:8080/api/tasks` bereit.

Die Kommunikation erfolgt durch HTTP-Anfragen und JSON-Antworten. Eine gezielte CORS-Konfiguration erlaubt dem lokalen Frontend den Zugriff auf `/api/**`. Das Frontend greift nicht direkt auf die interne Aufgabenliste oder Java-Klassen des Backends zu, sondern kennt ausschließlich den REST-Vertrag. Dadurch bleiben die beiden Module gering gekoppelt.

- [Systemarchitektur öffnen](docs/diagrams/systemarchitektur.md)

## Projektstruktur

```text
Smart Task Manager/
├── backend/             Spring-Boot-Backend und Tests
├── frontend/            Next.js-Frontend
├── design/stitch/       exportierter Google-Stitch-Prototyp
├── docs/
│   ├── diagrams/        Architektur
│   ├── prompts/         verwendete Prompts
│   ├── schritte/        Entwicklungsdokumentation
│   └── screenshots/     Nachweise
└── README.md
```

Die aktuelle Anwendung befindet sich vollständig in den getrennten Modulen `backend/` und `frontend/`.

## Voraussetzungen

- Java 21
- Maven
- Node.js
- npm
- Git

Cursor und Codex CLI wurden für den Werkzeugnachweis eingesetzt, sind zum normalen Start der Anwendung aber nicht erforderlich.

## Installation

### Repository klonen

```powershell
git clone https://github.com/ZAID-ALS/SmartTaskManager.git
cd "SmartTaskManager"
```

### Frontend-Abhängigkeiten installieren

```powershell
npm.cmd --prefix frontend install
```

Die Backend-Abhängigkeiten werden beim ersten Maven-Befehl automatisch geladen.

## Anwendung starten

Für den vollständigen Betrieb werden zwei getrennte Terminals benötigt.

### Terminal 1 – Backend

```powershell
mvn -f backend/pom.xml spring-boot:run
```

Backend-Adresse:

```text
http://localhost:8080
```

REST-API:

```text
http://localhost:8080/api/tasks
```

### Terminal 2 – Frontend

```powershell
npm.cmd --prefix frontend run dev
```

Frontend-Adresse:

```text
http://localhost:3000
```

Zuerst sollte das Backend und danach das Frontend gestartet werden. Wird das Backend beendet, bleibt die Oberfläche sichtbar und zeigt einen verständlichen Verbindungsfehler.

## REST-API

| Methode | Pfad | Funktion |
|---|---|---|
| `POST` | `/api/tasks` | Neue Aufgabe erstellen |
| `GET` | `/api/tasks` | Aktive Aufgaben abrufen |
| `GET` | `/api/tasks?sort=priority` | Nach Priorität sortiert abrufen |
| `GET` | `/api/tasks?sort=dueDate` | Nach Fälligkeitsdatum sortiert abrufen |
| `PATCH` | `/api/tasks/{id}/complete` | Aufgabe als erledigt markieren |
| `DELETE` | `/api/tasks/{id}` | Aufgabe soft löschen |

- Erfolgreiche Erstellung: `201 Created`
- Ungültige Eingaben oder Sortierwerte: `400 Bad Request`
- Unbekannte ID: `404 Not Found`
- Erfolgreiches Löschen: `204 No Content`

## Tests und Qualitätsprüfung

Die folgenden Ergebnisse wurden bereits während der Entwicklung erzielt und dokumentiert. Für diese README-Überarbeitung wurden die Befehle nicht erneut ausgeführt.

### Backend-Tests

```powershell
mvn -f backend/pom.xml test
```

Ergebnis:

```text
15 Tests erfolgreich
```

Davon prüfen neun Controller-Tests die HTTP-Endpunkte und sechs Service-Tests die Geschäftslogik des `TaskManager`.

### Frontend-Lint

```powershell
npm.cmd --prefix frontend run lint
```

Ergebnis:

```text
Keine ESLint-Fehler
```

### Frontend-Build

```powershell
npm.cmd --prefix frontend run build
```

Ergebnis:

```text
Compiled successfully
Generating static pages erfolgreich
```

## Nachweis des Codeverständnisses

Der Ablauf beim Erstellen einer Aufgabe führt durch klar getrennte Verantwortungsbereiche:

```text
CreateTaskForm
  -> onCreate
  -> handleCreate in page.tsx
  -> createTask in taskApi.ts
  -> POST /api/tasks
  -> CreateTaskRequest
  -> TaskController
  -> TaskManager
  -> In-Memory-Speicherung
  -> TaskResponse
  -> JSON-Antwort
  -> React-State in page.tsx
  -> TaskList und TaskCard
```

- `CreateTaskForm` ist für Eingaben und Formularzustand verantwortlich.
- `page.tsx` koordiniert den Anwendungszustand.
- `taskApi.ts` kapselt die HTTP-Kommunikation.
- `TaskController` verarbeitet REST-Anfragen.
- `TaskManager` enthält die Aufgabenlogik.
- `TaskResponse` bildet die externe Antwort.
- `TaskList` und `TaskCard` stellen die Daten dar.

Die Trennung zeigt **Separation of Concerns** und das **Single Responsibility Principle**. Frontend und Backend sind durch den REST-Vertrag gering gekoppelt. Zusammengehörige Aufgaben bleiben jeweils in Komponenten, API-Datei, Controller und Service gebündelt, wodurch eine hohe Kohäsion entsteht.

- [Ausführlicher Nachweis des Codeverständnisses](docs/schritte/08-codex-cli-und-cursor.md)

## Bewusste Einschränkung

Die Aufgaben werden im Backend nur in einer `ArrayList<Task>` gespeichert. Es gibt bewusst keine Datenbank. Beim Neustart des Backends gehen die Aufgaben verloren und die ID-Vergabe beginnt wieder bei 1. Soft-gelöschte Aufgaben bleiben bis zum Neustart intern gespeichert, werden bei normalen Abfragen jedoch herausgefiltert.

Diese In-Memory-Speicherung ist eine bewusste Vereinfachung, die das Projekt klein und vollständig verständlich hält.

## Werkzeugnachweis

Cursor wurde als VS-Code-Clone für eine tatsächliche Änderung in `TaskList.tsx` verwendet. Beim Ladehinweis wurde `role="status"` ergänzt. Anschließend waren ESLint und Frontend-Build erfolgreich.

Die OpenAI Codex CLI Version 0.144.1 wurde im echten Projektordner gestartet. Sie analysierte den tatsächlichen Frontend- und Backend-Code, ohne Dateien zu verändern.

- [Dokumentation zu Cursor und Codex CLI](docs/schritte/08-codex-cli-und-cursor.md)
- [Verwendete Werkzeug-Prompts](docs/prompts/08-codex-cli-und-cursor.md)
- [Codex CLI gestartet](docs/screenshots/10-codex-cli-gestartet.png)
- [Cursor-Codeänderung](docs/screenshots/11-cursor-barrierefreiheit.png)
- [Codex-CLI-Projektanalyse](docs/screenshots/12-codex-cli-projektanalyse.png)

## Zentrale Screenshots

- [Google-Stitch-Prompt](docs/screenshots/01-google-stitch-prompt.png)
- [Finales Stitch-Dashboard](docs/screenshots/stitch-dashboard-final.png)
- [Finaler Stitch-Create-Task-Screen](docs/screenshots/stitch-create-task-final.png)
- [Spring-Boot-Backend gestartet](docs/screenshots/02-spring-boot-backend-gestartet.png)
- [Backend-Tests erfolgreich](docs/screenshots/05-backend-tests-erfolgreich.png)
- [Frontend und Backend kommunizieren](docs/screenshots/07-frontend-backend-kommunikation.png)
- [Sortierung und erledigte Aufgabe](docs/screenshots/08-sortierung-und-erledigte-aufgabe.png)
- [Backend nicht erreichbar](docs/screenshots/09-backend-nicht-erreichbar.png)
- [Codex CLI gestartet](docs/screenshots/10-codex-cli-gestartet.png)
- [Cursor verwendet](docs/screenshots/11-cursor-barrierefreiheit.png)
- [Codex CLI analysiert das Projekt](docs/screenshots/12-codex-cli-projektanalyse.png)

## Entwicklungsdokumentation

1. [GUI-Prototyp mit Google Stitch](docs/schritte/01-gui-prototyp-mit-google-stitch.md)
2. [Projektplanung und Systemarchitektur](docs/schritte/02-projektplanung-und-systemarchitektur.md)
3. [Java-Backend-Struktur](docs/schritte/03-java-backend-struktur.md)
4. [REST-API und Tests](docs/schritte/04-rest-api-und-tests.md)
5. [Next.js-Frontend](docs/schritte/05-nextjs-frontend.md)
6. [Frontend-Backend-Kommunikation](docs/schritte/06-frontend-backend-kommunikation.md)
7. [Gesamtsystem und Fehlerfälle](docs/schritte/07-gesamtsystem-und-fehlerfaelle.md)
8. [Codex CLI und Cursor](docs/schritte/08-codex-cli-und-cursor.md)

## Verwendete Prompts

1. [Google-Stitch-Prompts](docs/prompts/01-google-stitch.md)
2. [Projektplanung und Systemarchitektur](docs/prompts/02-projektplanung-und-systemarchitektur.md)
3. [Java-Backend-Struktur](docs/prompts/03-java-backend-struktur.md)
4. [REST-API und Tests](docs/prompts/04-rest-api-und-tests.md)
5. [Next.js-Frontend](docs/prompts/05-nextjs-frontend.md)
6. [Frontend-Backend-Kommunikation](docs/prompts/06-frontend-backend-kommunikation.md)
7. [Gesamtsystem und Fehlerfälle](docs/prompts/07-gesamtsystem-und-fehlerfaelle.md)
8. [Cursor und Codex CLI](docs/prompts/08-codex-cli-und-cursor.md)

## Git-Branch

Die Entwicklung erfolgte schrittweise auf dem Branch:

```text
feature/vibe-coding
```
