package de.zaid.taskmanager.validation;

import de.zaid.taskmanager.model.Priority;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class InputValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    private InputValidator() {
    }

    public static boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

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

    public static boolean isValidTaskId(int id) {
        return id > 0;
    }
}
