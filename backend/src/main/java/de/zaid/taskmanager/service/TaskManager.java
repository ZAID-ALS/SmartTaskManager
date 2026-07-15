package de.zaid.taskmanager.service;

import de.zaid.taskmanager.model.Priority;
import de.zaid.taskmanager.model.Task;
import de.zaid.taskmanager.model.TaskStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
// Enthält die Aufgabenlogik und speichert Aufgaben im Arbeitsspeicher.
public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    // Erstellt und speichert eine Aufgabe mit der nächsten freien ID.
    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) {
        Task task = new Task(nextId, title, description, priority, dueDate);
        tasks.add(task);
        nextId++;
        return task;
    }

    // Gibt alle Aufgaben zurück, die nicht soft gelöscht wurden.
    public List<Task> getAllTasks() {
        ArrayList<Task> activeTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() != TaskStatus.DELETED) {
                activeTasks.add(task);
            }
        }

        return activeTasks;
    }

    // Sucht eine aktive Aufgabe anhand ihrer ID.
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id && task.getStatus() != TaskStatus.DELETED) {
                return task;
            }
        }

        return null;
    }

    // Setzt den Status auf DELETED, statt die Aufgabe zu entfernen.
    public boolean deleteTask(int id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        task.setStatus(TaskStatus.DELETED);
        return true;
    }

    // Setzt den Status einer Aufgabe auf COMPLETED.
    public boolean markTaskAsCompleted(int id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        task.setStatus(TaskStatus.COMPLETED);
        return true;
    }

    // Sortiert eine neue Liste nach der Priorität.
    public List<Task> getTasksSortedByPriority() {
        ArrayList<Task> sortedTasks = new ArrayList<>(getAllTasks());
        sortedTasks.sort(Comparator.comparing(Task::getPriority));
        return sortedTasks;
    }

    // Sortiert eine neue Liste nach dem frühesten Fälligkeitsdatum.
    public List<Task> getTasksSortedByDueDate() {
        ArrayList<Task> sortedTasks = new ArrayList<>(getAllTasks());
        sortedTasks.sort(Comparator.comparing(Task::getDueDate));
        return sortedTasks;
    }
}
