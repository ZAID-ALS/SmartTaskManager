# Verwendeter Prompt zum Gesamtsystem und zu den Fehlerfällen

````text
Dokumentiere jetzt ausschließlich Schritt 07 – Gesamtsystem und Fehlerfälle.

Wichtig:
- Arbeite im aktuellen Projekt:
  C:\Users\zaid\Desktop\Smart Task Manager
- Ändere keinen Java-, TypeScript-, CSS- oder Konfigurationscode.
- Ändere backend/, frontend/, design/ und das alte src/ nicht.
- Ändere oder benenne keine Screenshots um.
- Erstelle keine weiteren Dateien.
- Stage, committe oder pushe nichts.
- Führe keine Build-, Test- oder Startbefehle aus.
- Dokumentiere nur tatsächlich vorhandene und geprüfte Funktionen.
- Erfinde keine zusätzlichen Fehlerfälle oder Funktionen.

Ändere ausschließlich diese beiden Dateien:

1. docs/prompts/07-gesamtsystem-und-fehlerfaelle.md
2. docs/schritte/07-gesamtsystem-und-fehlerfaelle.md

────────────────────────────────────────
DATEI 1
docs/prompts/07-gesamtsystem-und-fehlerfaelle.md
────────────────────────────────────────

Verwende als Überschrift:

# Verwendeter Prompt zum Gesamtsystem und zu den Fehlerfällen

Dokumentiere darunter den vollständigen tatsächlich verwendeten Prompt für Schritt 07 in einem Markdown-Codeblock mit der Sprache `text`.

Übernimm dafür den vollständigen Inhalt dieses aktuellen Prompts, beginnend mit:

Dokumentiere jetzt ausschließlich Schritt 07 – Gesamtsystem und Fehlerfälle.

und endend mit:

Ändere sonst nichts.

Kürze den Prompt nicht und ergänze keine Anforderungen, die nicht in diesem Prompt enthalten sind.

────────────────────────────────────────
DATEI 2
docs/schritte/07-gesamtsystem-und-fehlerfaelle.md
────────────────────────────────────────

Verwende diese Struktur:

# Gesamtsystem und Fehlerfälle

## Ziel

Erkläre kurz:

In diesem Schritt wurde das vollständige System als Zusammenspiel aus Next.js-Frontend und Spring-Boot-Backend geprüft. Neben dem erfolgreichen Normalbetrieb wurde auch untersucht, wie sich das Frontend verhält, wenn das Backend nicht erreichbar ist.

## Aufbau des Gesamtsystems

Dokumentiere:

- Browser als Benutzeroberfläche
- Next.js-Frontend auf Port 3000
- Spring-Boot-Backend auf Port 8080
- Kommunikation über HTTP und JSON
- Aufgabenverwaltung über den `TaskManager`
- Speicherung in einer In-Memory-Liste

Erkläre kurz:

Frontend und Backend sind zwei getrennte Anwendungen beziehungsweise Prozesse. Das Frontend kennt nur die REST-Schnittstelle und nicht die interne Umsetzung des Backends.

## Systemablauf

Zeige diesen Ablauf in einem Text-Codeblock:

```text
Benutzer
  -> Browser
  -> Next.js-Frontend auf Port 3000
  -> HTTP / JSON
  -> Spring-Boot-Backend auf Port 8080
  -> TaskController
  -> TaskManager
  -> In-Memory-Aufgabenliste
```

Verlinke anschließend:

- [Systemarchitektur öffnen](../diagrams/systemarchitektur.md)

## Start des Gesamtsystems

Dokumentiere die tatsächlich verwendeten Befehle.

### Backend starten

```powershell
mvn -f backend/pom.xml spring-boot:run
```

Erwarteter erfolgreicher Start:

```text
Tomcat started on port 8080
Started SmartTaskManagerApplication
```

### Frontend starten

In einem zweiten Terminal:

```powershell
npm.cmd --prefix frontend run dev
```

Erwarteter erfolgreicher Start:

```text
Local: http://localhost:3000
Ready
```

Dokumentiere:

- Backend muss auf Port 8080 laufen.
- Frontend muss auf Port 3000 laufen.
- Beide Prozesse müssen für den vollständigen Normalbetrieb gleichzeitig aktiv sein.

## Erfolgreicher Normalbetrieb

Dokumentiere den tatsächlich geprüften Ablauf:

1. Backend wurde gestartet.
2. Frontend wurde in einem zweiten Terminal gestartet.
3. Das Frontend lud die leere Aufgabenliste vom Backend.
4. Die Aufgabe „REST-API testen“ wurde im Frontend erstellt.
5. Das Formular wurde nach dem Erstellen geleert.
6. Eine Erfolgsmeldung wurde angezeigt.
7. Die neue Aufgabe erschien unmittelbar in der Aufgabenliste.
8. Die Aufgabe wurde als erledigt markiert.
9. Der Status wechselte von `OFFEN` zu `ERLEDIGT`.
10. Die Schaltfläche zum Abschließen wurde anschließend deaktiviert.
11. Die Aufgabe „Backend-Tests prüfen“ wurde als zweite Aufgabe erstellt.
12. Die Aufgaben wurden nach Fälligkeitsdatum sortiert.
13. Das Datum `18.07.2026` wurde vor dem Datum `20.07.2026` angezeigt.
14. Eine Testaufgabe wurde über das Frontend gelöscht.

Binde danach diese vorhandenen Screenshots ein:

![Erfolgreiche Frontend-Backend-Kommunikation](../screenshots/07-frontend-backend-kommunikation.png)

![Sortierung und erledigte Aufgabe](../screenshots/08-sortierung-und-erledigte-aufgabe.png)

## Geprüfter Fehlerfall: Backend nicht erreichbar

Dokumentiere den tatsächlich geprüften Fehlerfall:

1. Das Backend war ausgeschaltet.
2. Nur das Next.js-Frontend wurde gestartet.
3. Die Seite wurde im Browser neu geladen.
4. Das Frontend versuchte, die Aufgaben über die REST-API zu laden.
5. Die Verbindung zu Port 8080 konnte nicht hergestellt werden.
6. Die Anwendung stürzte nicht ab.
7. Stattdessen wurde eine verständliche Fehlermeldung angezeigt.
8. Das Formular und die restliche Oberfläche blieben weiterhin sichtbar.

Dokumentiere den exakt angezeigten Text:

```text
Die Aufgaben konnten nicht geladen werden. Ist das Backend auf Port 8080 gestartet?
```

Binde danach diesen Screenshot ein:

![Backend nicht erreichbar](../screenshots/09-backend-nicht-erreichbar.png)

## Bewertung des Fehlerverhaltens

Erkläre sachlich:

- Der technische Verbindungsfehler wird nicht ungefiltert angezeigt.
- Der Benutzer erhält eine verständliche Meldung.
- Die React-Anwendung bleibt weiterhin sichtbar und stürzt nicht ab.
- Der Fehlerzustand ist optisch von normalen Inhalten getrennt.
- Nach dem Start des Backends und einem erneuten Laden der Seite kann das Frontend wieder Daten abrufen.

Behaupte nicht, dass ein automatischer Wiederholungsversuch oder eine spezielle Wiederholen-Schaltfläche existiert.

## In-Memory-Speicherung

Erkläre kurz:

Die Aufgaben werden derzeit nur im Arbeitsspeicher des Backends gespeichert. Dadurch ist keine Datenbank erforderlich und das Projekt bleibt einfach. Beim Beenden oder Neustarten des Backends bleiben die Aufgaben jedoch nicht dauerhaft erhalten.

Beschreibe dies als bewusste Vereinfachung des aktuellen Projektstands und nicht als unbeabsichtigten Programmfehler.

## Bereits durchgeführte Qualitätsprüfungen

Dokumentiere die bereits erzielten Ergebnisse.

### Backend-Tests

```text
Tests run: 15
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

### Frontend-Lint

```text
Keine ESLint-Fehler oder Warnungen
```

### Frontend-Build

```text
Compiled successfully
Finished TypeScript
Generating static pages
Build erfolgreich
```

Erfinde keine neuen Testergebnisse und behaupte nicht, dass diese Prüfungen in Schritt 07 erneut ausgeführt wurden.

## Kursbegriffe

Erkläre die Begriffe jeweils kurz am aktuellen Projekt:

- **Verteilte Anwendung:** Frontend und Backend laufen als getrennte Prozesse und kommunizieren über HTTP.
- **Client-Server-Architektur:** Das Frontend sendet Anfragen und das Backend verarbeitet sie.
- **Separation of Concerns:** Benutzeroberfläche, API-Zugriff, HTTP-Verarbeitung und Geschäftslogik sind getrennt.
- **Single Responsibility Principle:** Komponenten und Klassen besitzen klar abgegrenzte Aufgaben.
- **Geringe Kopplung:** Das Frontend ist nur von der REST-Schnittstelle abhängig, nicht von internen Java-Klassen.
- **Hohe Kohäsion:** Zusammengehörige Funktionen befinden sich in den dafür vorgesehenen Komponenten, Services und API-Dateien.
- **Fehlerbehandlung:** Verbindungsfehler werden abgefangen und als verständlicher Zustand dargestellt.
- **Robustheit:** Die Oberfläche bleibt auch dann sichtbar, wenn das Backend vorübergehend nicht erreichbar ist.
- **Testbarkeit:** Backend-Geschäftslogik und HTTP-Endpunkte werden durch automatisierte Tests geprüft.

## Persönliche Erfahrung

Verwende diesen natürlichen und kurzen Text:

Beim Test des vollständigen Systems wurde deutlich, dass eine verteilte Anwendung von mehreren laufenden Prozessen abhängt. Wenn das Backend ausgeschaltet ist, kann das Frontend keine Aufgaben laden. Hilfreich war, dass die Seite dabei nicht abstürzte, sondern eine verständliche Meldung anzeigte. Dadurch ließ sich der Fehler schnell dem nicht gestarteten Backend zuordnen.

## Links

Füge diese Links ein:

- [Verwendeten Prompt öffnen](../prompts/07-gesamtsystem-und-fehlerfaelle.md)
- [Frontend-Backend-Kommunikation öffnen](06-frontend-backend-kommunikation.md)
- [REST-API und Tests öffnen](04-rest-api-und-tests.md)
- [Statisches Next.js-Frontend öffnen](05-nextjs-frontend.md)
- [Systemarchitektur öffnen](../diagrams/systemarchitektur.md)
- [Zurück zur Haupt-README](../../README.md)

────────────────────────────────────────
ABSCHLUSSPRÜFUNG
────────────────────────────────────────

Prüfe nach der Bearbeitung:

1. Es wurden ausschließlich diese beiden Dateien geändert:
   - docs/prompts/07-gesamtsystem-und-fehlerfaelle.md
   - docs/schritte/07-gesamtsystem-und-fehlerfaelle.md

2. Diese Screenshot-Pfade sind korrekt:
   - ../screenshots/07-frontend-backend-kommunikation.png
   - ../screenshots/08-sortierung-und-erledigte-aufgabe.png
   - ../screenshots/09-backend-nicht-erreichbar.png

3. Alle Markdown-Codeblöcke sind geschlossen.

4. Der Normalbetrieb und der Fehlerfall sind klar voneinander getrennt.

5. Es wurde nicht behauptet, dass nicht vorhandene Funktionen wie automatische Wiederholungsversuche existieren.

6. Die In-Memory-Speicherung wurde als bewusste Vereinfachung beschrieben.

7. Es wurden keine Ergebnisse oder Funktionen erfunden.

Zeige danach:

- den vollständigen Inhalt von docs/prompts/07-gesamtsystem-und-fehlerfaelle.md
- den vollständigen Inhalt von docs/schritte/07-gesamtsystem-und-fehlerfaelle.md
- die Ausgabe von git status --short

Ändere sonst nichts.
````
