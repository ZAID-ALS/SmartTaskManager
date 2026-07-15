package de.zaid.taskmanager.exception;

// Wird ausgelöst, wenn eine Aufgaben-ID nicht gefunden wurde.
public class TaskNotFoundException extends RuntimeException {
    // Die ID wird direkt in die Fehlermeldung übernommen.
    public TaskNotFoundException(int id) {
        super("Task with ID " + id + " was not found");
    }
}
