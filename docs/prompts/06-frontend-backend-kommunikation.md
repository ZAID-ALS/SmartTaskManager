# Verwendeter Prompt zur Frontend-Backend-Kommunikation

````text
Führe jetzt Schritt 06 – Frontend-Backend-Kommunikation aus.

Wichtig:
- Arbeite nur in `frontend/` und, falls für CORS notwendig, in `backend/`.
- Verändere das alte `src/`, die Root-`pom.xml`, `design/` und die Dokumentation nicht.
- Verändere keine vorhandenen Screenshots.
- Nichts stagen, committen oder pushen.
- Keine Datenbank, Anmeldung, Bearbeitungsfunktion oder zusätzlichen Endpunkte ergänzen.
- Die vorhandene Gestaltung des Frontends soll möglichst erhalten bleiben.
- Die vorhandene REST-API und Geschäftslogik dürfen nicht neu geschrieben werden.

## Ziel

Das Next.js-Frontend soll die vorhandene Spring-Boot-REST-API unter:

```text
http://localhost:8080/api/tasks
```

verwenden.

Das Frontend läuft unter:

```text
http://localhost:3000
```

## 1. Aktuellen Code prüfen

Prüfe zuerst den tatsächlichen aktuellen Aufbau von:

- `frontend/src/app/page.tsx`
- `frontend/src/components/CreateTaskForm.tsx`
- `frontend/src/components/TaskList.tsx`
- `frontend/src/components/TaskCard.tsx`
- `backend/src/main/java/de/zaid/taskmanager/controller/TaskController.java`
- vorhandene Backend-Konfiguration

Passe die Umsetzung an den tatsächlichen Code an.

## 2. CORS im Backend

Prüfe, ob Zugriffe von `http://localhost:3000` bereits erlaubt sind.

Falls nicht, erstelle:

```text
backend/src/main/java/de/zaid/taskmanager/config/WebConfig.java
```

Erlaube für `/api/**` ausschließlich die lokale Frontend-Adresse:

```text
http://localhost:3000
```

Erlaube die benötigten Methoden:

- GET
- POST
- PATCH
- DELETE
- OPTIONS

Keine globale Freigabe mit `*` verwenden.

## 3. API-Typen und API-Zugriff im Frontend

Erstelle eine einfache, verständliche Struktur, zum Beispiel:

```text
frontend/src/
├── lib/
│   └── taskApi.ts
└── types/
    └── task.ts
```

Eine ähnlich einfache Struktur ist erlaubt.

Definiere passende TypeScript-Typen für:

- `Task`
- `CreateTaskInput`
- `Priority`
- `TaskStatus`

Implementiere klar getrennte API-Funktionen für:

- Aufgaben laden
- Aufgabe erstellen
- Aufgabe abschließen
- Aufgabe löschen

Verwende als Basis-URL:

```text
http://localhost:8080
```

Die URL darf über diese öffentliche Umgebungsvariable überschreibbar sein:

```text
NEXT_PUBLIC_API_URL
```

Falls keine Umgebungsvariable gesetzt ist, soll `http://localhost:8080` verwendet werden.

Erstelle bei Bedarf eine Datei:

```text
frontend/.env.example
```

mit:

```text
NEXT_PUBLIC_API_URL=http://localhost:8080
```

Keine echte `.env`-Datei mit privaten Daten erstellen.

## 4. Statische Daten ersetzen

Entferne die statischen Beispielaufgaben aus dem tatsächlichen Seitenablauf.

Beim Laden der Seite sollen die Aufgaben mit:

```text
GET /api/tasks
```

vom Backend geladen werden.

Die Oberfläche soll folgende Zustände verständlich anzeigen:

- Aufgaben werden geladen
- keine Aufgaben vorhanden
- Backend nicht erreichbar oder anderer Fehler

Keine komplexe Zustandsverwaltung verwenden. React-State und `useEffect` reichen aus.

## 5. Aufgabe erstellen

Verbinde das vorhandene Formular mit:

```text
POST /api/tasks
```

Nach erfolgreichem Erstellen:

- neue Aufgabe in der Liste anzeigen oder Liste erneut laden
- Formularfelder leeren
- verständliche Erfolgsmeldung anzeigen

Bei ungültigen Eingaben oder API-Fehlern:

- verständliche Fehlermeldung anzeigen
- Seite darf nicht abstürzen

Die Request-Daten müssen genau diese Felder enthalten:

```text
title
description
priority
dueDate
```

## 6. Sortierung

Verbinde die beiden vorhandenen Sortierschaltflächen:

```text
GET /api/tasks?sort=priority
GET /api/tasks?sort=dueDate
```

Die aktive Sortierung soll in der Oberfläche erkennbar sein.

Keine eigene abweichende Sortierlogik im Frontend erstellen. Verwende die Backend-Endpunkte.

## 7. Aufgabe abschließen

Verbinde „Erledigt“ mit:

```text
PATCH /api/tasks/{id}/complete
```

Nach erfolgreichem Abschluss:

- Status in der Oberfläche auf `COMPLETED` aktualisieren
- Schaltfläche bei bereits erledigten Aufgaben sinnvoll deaktivieren oder ausblenden

## 8. Aufgabe löschen

Verbinde „Löschen“ mit:

```text
DELETE /api/tasks/{id}
```

Nach erfolgreichem Löschen:

- Aufgabe aus der sichtbaren Liste entfernen oder Liste erneut laden

Keine zusätzliche Bearbeitungs- oder Bestätigungsfunktion erfinden.

## 9. Komponenten und Verantwortungen

Achte auf klare Verantwortungen:

- API-Aufrufe in einer eigenen API-Datei
- TypeScript-Typen in einer eigenen Typ-Datei
- Formular für Eingaben und Absenden
- Aufgabenliste für Darstellung und Sortierung
- Aufgabenkarte für Aktionen einer Aufgabe
- übergeordnete Client-Komponente oder Seite für Zustand und Koordination

Vermeide eine einzelne sehr große Komponente.

## 10. Prüfung

Führe anschließend aus:

```text
mvn -f backend/pom.xml test
```

Danach:

```text
npm.cmd --prefix frontend run lint
```

Danach:

```text
npm.cmd --prefix frontend run build
```

Wenn alles erfolgreich ist, führe aus:

```text
mvn -f backend/pom.xml clean
```

## 11. Ausgabe

Zeige danach:

- alle neu erstellten und geänderten Dateien
- eine kurze Erklärung der Kommunikation zwischen Frontend und Backend
- Ergebnis der 15 Backend-Tests
- ESLint-Ergebnis
- vollständige Frontend-Build-Zusammenfassung
- `git status --short`

Ändere sonst nichts.
````
