# Verwendeter Prompt zur Java-Backend-Struktur

```text
Führe jetzt Schritt 03 – Java-Backend-Struktur aus.

Wichtig:
- Arbeite im aktuellen Projekt:
  C:\Users\zaid\Desktop\Smart Task Manager
- Verändere oder lösche nichts im bisherigen Ordner src/.
- Verändere die vorhandene Root-pom.xml nicht.
- Verändere keine Dokumentationsdateien.
- Erstelle keinen Controller, keine REST-Endpunkte und keine DTOs.
- Erstelle kein Frontend.
- Nichts stagen, committen oder pushen.
- Keine Datenbank, Security oder zusätzlichen Funktionen ergänzen.

1. Erstelle ein neues Spring-Boot-Maven-Modul:

backend/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── de/zaid/taskmanager/
        │       ├── SmartTaskManagerApplication.java
        │       ├── model/
        │       │   ├── Task.java
        │       │   ├── Priority.java
        │       │   └── TaskStatus.java
        │       ├── service/
        │       │   └── TaskManager.java
        │       └── validation/
        │           └── InputValidator.java
        └── resources/
            └── application.properties

2. Konfiguriere backend/pom.xml mit:
- Spring Boot 4.1.0
- Java 21
- artifactId smart-task-manager-backend
- spring-boot-starter-web
- spring-boot-maven-plugin
- Maven-Standardstruktur

3. Kopiere diese vorhandenen Klassen in das neue Backend:
- src/Task.java
- src/Priority.java
- src/TaskStatus.java
- src/TaskManager.java
- src/InputValidator.java

Die ursprünglichen Dateien unter src/ müssen unverändert erhalten bleiben.

4. Passe in den kopierten Dateien nur Folgendes an:
- package-Deklarationen
- notwendige Imports
- TaskManager mit @Service kennzeichnen

Die fachliche Logik darf nicht verändert werden.

Insbesondere unverändert lassen:
- Felder und Konstruktor von Task
- Enum-Werte
- In-Memory-Aufgabenliste
- fortlaufende ID-Vergabe
- Soft-Delete über TaskStatus.DELETED
- Sortierreihenfolge
- alle vorhandenen öffentlichen Methoden und Rückgabewerte
- alle vier Methoden von InputValidator

5. Erstelle SmartTaskManagerApplication mit:
- @SpringBootApplication
- main-Methode
- SpringApplication.run(...)

6. Schreibe in application.properties:

spring.application.name=smart-task-manager-backend
server.port=8080

7. Führe danach aus:

mvn -f backend/pom.xml clean package

Wenn der Build erfolgreich ist, führe anschließend aus:

mvn -f backend/pom.xml clean

Dadurch sollen erzeugte Build-Dateien wieder entfernt werden.

8. Zeige danach:
- alle neu erstellten Dateien unter backend/
- eine kurze Erklärung jeder neuen beziehungsweise kopierten Klasse
- die vollständige Build-Zusammenfassung
- git status --short

Ändere sonst nichts.
```
