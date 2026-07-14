# Verwendete Prompts für Cursor und Codex CLI

## Prompt 1 – Cursor-Analyse

```text
Analysiere ausschließlich die Barrierefreiheit der vorhandenen Status- und Fehlermeldungen im Frontend.

Prüfe besonders:

- frontend/src/app/page.tsx
- frontend/src/components/CreateTaskForm.tsx
- frontend/src/components/TaskList.tsx

Aufgabe:

1. Prüfe, wie Ladezustand, Erfolgsmeldungen und Fehlermeldungen aktuell dargestellt werden.
2. Ermittle eine einzige kleine und sinnvolle Verbesserung mit aria-live, role="status" oder role="alert".
3. Die sichtbaren Texte und das Design sollen unverändert bleiben.
4. Noch keine Dateien ändern.
5. Keine neuen Funktionen ergänzen.
6. Keine Dokumentation ändern.
7. Nichts stagen, committen oder pushen.

Gib nur deine Analyse und die kleinste empfohlene Änderung aus.
```

## Prompt 2 – Cursor-Codeänderung

```text
Führe jetzt ausschließlich die empfohlene kleine Barrierefreiheitsverbesserung aus.

Ändere nur:

frontend/src/components/TaskList.tsx

Ergänze beim sichtbaren Ladehinweis „Aufgaben werden geladen …“:

role="status"

Vorgaben:
- Keine weiteren Codeänderungen.
- Keine sichtbaren Texte verändern.
- Kein CSS verändern.
- Keine neuen Funktionen ergänzen.
- Keine Dokumentationsdateien oder Screenshots ändern.
- Nichts stagen, committen oder pushen.

Führe danach aus:

npm.cmd --prefix frontend run lint
npm.cmd --prefix frontend run build

Zeige anschließend:
- die genaue geänderte Codezeile,
- die Ergebnisse von Lint und Build,
- git diff -- frontend/src/components/TaskList.tsx
- git status --short
```

## Prompt 3 – Codex-CLI-Projektanalyse

```text
Analysiere das aktuelle Projekt Smart Task Manager, ohne Dateien zu ändern.

Prüfe den tatsächlichen Code in backend/ und frontend/ und erkläre verständlich:

1. Welche getrennten Module besitzt die Anwendung?
2. Warum ist sie eine verteilte Anwendung?
3. Wie läuft das Erstellen einer Aufgabe vollständig ab:
   CreateTaskForm -> page.tsx -> taskApi.ts -> HTTP POST -> TaskController -> TaskManager -> TaskResponse -> Frontend?
4. Welche Verantwortung haben:
   - TaskController
   - TaskManager
   - CreateTaskRequest
   - TaskResponse
   - GlobalExceptionHandler
   - WebConfig
   - page.tsx
   - taskApi.ts
   - TaskList
   - TaskCard
5. Wo sind Separation of Concerns, Single Responsibility Principle, geringe Kopplung und hohe Kohäsion erkennbar?
6. Wie werden Lade-, Leer-, Erfolgs- und Fehlerzustände behandelt?
7. Welche bewusste Einschränkung entsteht durch die In-Memory-Speicherung?
8. Nenne die wichtigsten Dateien, die deine Aussagen belegen.

Wichtig:
- Nur den tatsächlich vorhandenen Code analysieren.
- Keine Dateien erstellen, ändern oder löschen.
- Nichts stagen, committen oder pushen.
- Keine neuen Funktionen vorschlagen.
- Antworte strukturiert und verständlich auf Deutsch.
```
