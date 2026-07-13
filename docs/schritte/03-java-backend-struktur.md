# Java-Backend-Struktur mit Spring Boot

## Ziel

Das bestehende Java-Konsolenprojekt wurde zunächst parallel um ein neues Spring-Boot-Backend ergänzt. Die vorhandene Geschäftslogik und die Modellklassen wurden wiederverwendet.

## Erstellte Struktur

```text
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
```

## Wiederverwendete Klassen

- `Task` modelliert eine Aufgabe mit ID, Titel, Beschreibung, Priorität, Status und Fälligkeitsdatum.
- `Priority` stellt die drei Prioritäten `HIGH`, `MEDIUM` und `LOW` bereit.
- `TaskStatus` beschreibt den aktuellen Zustand einer Aufgabe und unterstützt weiterhin das Soft-Delete über `DELETED`.
- `TaskManager` enthält die Geschäftslogik und verwaltet die Aufgaben weiterhin im Arbeitsspeicher.
- `InputValidator` prüft Titel und IDs und verarbeitet Prioritäts- und Datumsangaben.

## Neue Klasse

`SmartTaskManagerApplication` ist die neue Spring-Boot-Startklasse. Sie startet das Backend über `SpringApplication.run(...)`.

## Anpassungen

- Paketdeklarationen ergänzt
- notwendige Imports ergänzt
- `TaskManager` mit `@Service` gekennzeichnet
- fachliche Logik nicht verändert
- altes `src/` noch nicht gelöscht

## Maven und Spring Boot

- Java 21
- Maven
- Spring Boot 4.1.0
- `spring-boot-starter-web`
- Backend-Port 8080

## Build-Prüfung

Dieser Befehl wurde erfolgreich ausgeführt:

```text
mvn -f backend/pom.xml clean package
```

Ergebnis:

```text
BUILD SUCCESS
```

Danach wurde der erzeugte `target`-Ordner mit diesem Befehl entfernt:

```text
mvn -f backend/pom.xml clean
```

## Kursbegriffe

- **Wiederverwendung:** Die vorhandenen Modell-, Service- und Validierungsklassen wurden in die neue Backend-Struktur übernommen, ohne ihre fachliche Logik neu zu schreiben.
- **Separation of Concerns:** Das neue Backend ist vom bisherigen Konsolenprogramm getrennt und bildet eine eigene Anwendung.
- **Single Responsibility Principle:** Jede Klasse behält eine klar abgegrenzte Aufgabe, etwa Modellierung, Geschäftslogik, Validierung oder Anwendungsstart.
- **Hohe Kohäsion:** Fachlich zusammengehörige Klassen liegen gemeinsam in den Paketen `model`, `service` und `validation`.
- **Schichtenstruktur:** Modell, Service und Validierung sind bereits getrennt organisiert und bilden die Grundlage für die späteren API-Schichten.

## Persönliche Erfahrung

Die vorhandenen Klassen konnten größtenteils wiederverwendet werden. Dadurch musste die Geschäftslogik nicht neu geschrieben werden. Neu waren vor allem die Paketstruktur, die Spring-Boot-Startklasse und die Maven-Konfiguration des Backends.

## Links

- [Verwendeten Prompt öffnen](../prompts/03-java-backend-struktur.md)
- [Systemarchitektur öffnen](../diagrams/systemarchitektur.md)
- [Zurück zur Haupt-README](../../README.md)
