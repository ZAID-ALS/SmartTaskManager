# Verwendeter Prompt zum Next.js-Frontend

```text
Führe jetzt Schritt 05 – Next.js-Frontend aus.

Wichtig:
- Arbeite im aktuellen Projekt:
  C:\Users\zaid\Desktop\Smart Task Manager
- Erstelle und verändere ausschließlich Dateien unter frontend/.
- Verändere backend/, das alte src/, die Root-pom.xml und die Dokumentation nicht.
- Nichts stagen, committen oder pushen.
- Noch keine Verbindung zum Backend herstellen.
- Noch keine HTTP-Anfragen oder API-Funktionen implementieren.
- Keine Datenbank, Anmeldung, Bearbeitungsfunktion oder zusätzliche Funktionen ergänzen.

Nutze diese vorhandenen Dateien als visuelle Vorlage:

- design/stitch/DESIGN.md
- design/stitch/dashboard/code.html
- design/stitch/create-task/code.html
- docs/screenshots/stitch-dashboard-final.png
- docs/screenshots/stitch-create-task-final.png

Erstelle unter frontend/ eine einfache Next.js-Anwendung mit:

- TypeScript
- App Router
- ESLint
- normalem CSS ohne zusätzliche UI-Bibliothek
- npm als Paketmanager

Die Anwendung soll eine übersichtliche Seite für den Smart Task Manager enthalten.

## Oberfläche

Erstelle:

1. Einen Kopfbereich mit:
- Titel „Smart Task Manager“
- kurzer Unterzeile
- Schaltfläche „Neue Aufgabe“

2. Einen Bereich zum Erstellen einer Aufgabe mit:
- Titel
- Beschreibung
- Priorität HIGH, MEDIUM oder LOW
- Fälligkeitsdatum
- Schaltfläche „Aufgabe erstellen“

3. Einen Bereich für die Aufgabenliste mit:
- Sortierung nach Priorität
- Sortierung nach Fälligkeitsdatum
- Aufgabenkarten mit:
  - Titel
  - Beschreibung
  - Priorität
  - Status
  - Fälligkeitsdatum
  - Schaltfläche „Erledigt“
  - Schaltfläche „Löschen“

4. Verwende zunächst wenige statische Beispieldaten direkt im Frontend, damit das Design sichtbar geprüft werden kann.

## Struktur

Teile die Oberfläche in verständliche React-Komponenten auf, zum Beispiel:

frontend/src/
├── app/
│   ├── layout.tsx
│   ├── page.tsx
│   └── globals.css
└── components/
    ├── Header.tsx
    ├── CreateTaskForm.tsx
    ├── TaskList.tsx
    └── TaskCard.tsx

Eine ähnlich einfache und nachvollziehbare Struktur ist ebenfalls erlaubt.

## Vorgaben

- Orientierung am vorhandenen Google-Stitch-Design
- responsives Layout
- klare Abstände und gut lesbare Schrift
- keine unnötigen Animationen
- keine externen Bilder erforderlich
- keine API-Aufrufe
- keine komplexe Zustandsverwaltung
- keine zusätzlichen Seiten
- keine Funktionen erfinden, die nicht zum bestehenden Projekt gehören

Die Schaltflächen dürfen in diesem Schritt noch rein visuell sein. Die echte Kommunikation mit dem Spring-Boot-Backend folgt erst in Schritt 06.

Führe nach der Erstellung im Projektordner aus:

npm.cmd --prefix frontend run build

Wenn der Build erfolgreich ist, zeige:

- die erstellte Frontend-Struktur
- eine kurze Erklärung der Komponenten
- die vollständige Build-Zusammenfassung
- git status --short

Ändere sonst nichts.
```
