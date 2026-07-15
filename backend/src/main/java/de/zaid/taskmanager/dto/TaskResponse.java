package de.zaid.taskmanager.dto;

import de.zaid.taskmanager.model.Priority;
import de.zaid.taskmanager.model.TaskStatus;
import java.time.LocalDate;

// Legt fest, welche Aufgabendaten an das Frontend gesendet werden.
public class TaskResponse {
    private final int id;
    private final String title;
    private final String description;
    private final Priority priority;
    private final TaskStatus status;
    private final LocalDate dueDate;

    // Baut die vollständige Antwort für eine Aufgabe auf.
    public TaskResponse(
            int id,
            String title,
            String description,
            Priority priority,
            TaskStatus status,
            LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
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
}
