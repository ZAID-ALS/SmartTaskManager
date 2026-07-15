package de.zaid.taskmanager.model;

import java.time.LocalDate;

// Enthält die Daten einer einzelnen Aufgabe.
public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final Priority priority;
    private TaskStatus status;
    private final LocalDate dueDate;

    // Erstellt eine neue Aufgabe zuerst mit dem Status NEW.
    public Task(int id, String title, String description, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.NEW;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // Ändert den Status einer Aufgabe.
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

}
