package de.zaid.taskmanager.validation;

import de.zaid.taskmanager.model.Priority;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

// Prüft und verarbeitet einfache Eingabewerte.
public class InputValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    // Der private Konstruktor verhindert, dass die Hilfsklasse erzeugt wird.
    private InputValidator() {
    }

    // Prüft, ob ein Titel vorhanden ist.
    public static boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    // Wandelt eine Texteingabe in eine Priorität um.
    public static Priority parsePriority(String input) {
        if (input == null) {
            return null;
        }

        try {
            return Priority.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    // Wandelt ein Datum im Format JJJJ-MM-TT um.
    public static LocalDate parseDate(String input) {
        if (input == null) {
            return null;
        }

        try {
            return LocalDate.parse(input.trim(), DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            return null;
        }
    }

    // Eine gültige Aufgaben-ID muss größer als null sein.
    public static boolean isValidTaskId(int id) {
        return id > 0;
    }
}
