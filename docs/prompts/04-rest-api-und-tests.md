# Verwendete Prompts zur REST-API und zu den automatisierten Tests

## Prompt 1 – REST-API erstellen

```text
Erweitere jetzt das Spring-Boot-Backend um die REST-API.

Wichtig:
- Arbeite nur im Ordner backend/.
- Verändere das alte src/ und die Root-pom.xml nicht.
- Verändere noch keine Dokumentationsdateien.
- Erstelle noch keine Tests.
- Erstelle kein Frontend.
- Keine Datenbank, Security, Anmeldung oder zusätzlichen Funktionen.
- Nichts stagen, committen oder pushen.
- Die vorhandene Geschäftslogik im TaskManager soll erhalten bleiben.

Erstelle diese Pakete und Klassen:

backend/src/main/java/de/zaid/taskmanager/
├── controller/
│   └── TaskController.java
├── dto/
│   ├── CreateTaskRequest.java
│   ├── TaskResponse.java
│   └── ApiError.java
└── exception/
    ├── TaskNotFoundException.java
    └── GlobalExceptionHandler.java

Ergänze in backend/pom.xml nur dann notwendige Abhängigkeiten, wenn sie für Bean Validation benötigt werden.

Implementiere ausschließlich diese Endpunkte:

1. POST /api/tasks
- erstellt eine Aufgabe
- HTTP-Status 201 Created
- Request-Felder:
  - title
  - description
  - priority
  - dueDate
- title darf nicht leer sein
- priority und dueDate müssen vorhanden und gültig sein
- Antwort als TaskResponse

2. GET /api/tasks
- gibt alle nicht gelöschten Aufgaben zurück
- HTTP-Status 200 OK
- ohne sort-Parameter normale Reihenfolge
- ?sort=priority verwendet getTasksSortedByPriority()
- ?sort=dueDate verwendet getTasksSortedByDueDate()
- unbekannter sort-Wert führt zu 400 Bad Request

3. PATCH /api/tasks/{id}/complete
- verwendet markTaskAsCompleted(id)
- HTTP-Status 200 OK
- gibt die aktualisierte Aufgabe als TaskResponse zurück
- unbekannte ID führt zu 404 Not Found

4. DELETE /api/tasks/{id}
- verwendet deleteTask(id)
- HTTP-Status 204 No Content
- unbekannte ID führt zu 404 Not Found

Vorgaben:

- TaskController soll nur HTTP-Anfragen verarbeiten und TaskManager verwenden.
- Keine Geschäftslogik in den Controller verschieben.
- Keine Task-Objekte direkt als API-Antwort zurückgeben; TaskResponse verwenden.
- Verwende eine private Mapping-Methode von Task zu TaskResponse oder eine ähnlich einfache Lösung.
- CreateTaskRequest soll Bean Validation verwenden.
- ApiError soll mindestens Zeitstempel, Status, Fehler und Nachricht enthalten.
- GlobalExceptionHandler soll mindestens behandeln:
  - TaskNotFoundException -> 404
  - ungültige Request-Daten -> 400
  - ungültiger sort-Wert -> 400
- Bestehende öffentliche Methoden von TaskManager nicht entfernen oder umbenennen.
- Keine Editierfunktion und keine weiteren Endpunkte ergänzen.

Führe danach aus:

mvn -f backend/pom.xml clean package

Wenn der Build erfolgreich ist, anschließend:

mvn -f backend/pom.xml clean

Zeige danach:
- alle neu erstellten und geänderten Dateien
- eine kurze Erklärung der API-Struktur
- die vollständige Build-Zusammenfassung
- git status --short

Ändere sonst nichts.
```

## Prompt 2 – Automatisierte Tests erstellen

```text
Ergänze jetzt ausschließlich automatisierte Tests für das bestehende Spring-Boot-Backend.

Wichtig:
- Arbeite nur im Ordner backend/.
- Verändere das alte src/, die Root-pom.xml und die Dokumentation nicht.
- Verändere die vorhandene REST-API und Geschäftslogik nur, wenn ein Test einen echten Fehler nachweist.
- Keine neuen Funktionen oder Endpunkte ergänzen.
- Kein Frontend erstellen.
- Nichts stagen, committen oder pushen.

1. Prüfe zuerst die aktuelle backend/pom.xml.

Ergänze nur die notwendige Test-Abhängigkeit für Spring Boot, JUnit und MockMvc, falls sie noch fehlt.

2. Erstelle:

backend/src/test/java/de/zaid/taskmanager/service/TaskManagerTest.java

Teste mindestens:

- createTask erstellt eine Aufgabe mit fortlaufender ID.
- getAllTasks gibt aktive Aufgaben zurück.
- deleteTask führt ein Soft-Delete aus und die Aufgabe erscheint danach nicht mehr.
- markTaskAsCompleted setzt den Status auf COMPLETED.
- getTasksSortedByPriority sortiert HIGH vor MEDIUM vor LOW.
- getTasksSortedByDueDate sortiert nach dem frühesten Datum.

3. Erstelle:

backend/src/test/java/de/zaid/taskmanager/controller/TaskControllerTest.java

Teste mindestens:

- POST /api/tasks liefert 201 Created.
- GET /api/tasks liefert 200 OK.
- GET /api/tasks?sort=priority funktioniert.
- GET /api/tasks?sort=dueDate funktioniert.
- PATCH /api/tasks/{id}/complete liefert 200 OK.
- DELETE /api/tasks/{id} liefert 204 No Content.
- ungültige Request-Daten liefern 400 Bad Request.
- unbekannter sort-Wert liefert 400 Bad Request.
- unbekannte ID liefert 404 Not Found.

Verwende für Controller-Tests MockMvc und die für die aktuell verwendete Spring-Boot-Version passenden Test-Annotationen.

4. Die Tests sollen:
- verständliche Methodennamen besitzen,
- keine Datenbank verwenden,
- unabhängig voneinander laufen,
- keine unnötigen Bibliotheken verwenden.

5. Führe danach aus:

mvn -f backend/pom.xml test

Zeige:
- Anzahl der ausgeführten Tests,
- Anzahl der Fehler und Fehlschläge,
- BUILD SUCCESS oder BUILD FAILURE.

Wenn alle Tests erfolgreich sind, führe anschließend aus:

mvn -f backend/pom.xml clean

6. Zeige zum Schluss:
- alle neu erstellten und geänderten Dateien,
- eine kurze Erklärung der Tests,
- die vollständige Test-Zusammenfassung,
- git status --short.

Ändere sonst nichts.
```
