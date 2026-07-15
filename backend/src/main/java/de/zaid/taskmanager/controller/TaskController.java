package de.zaid.taskmanager.controller;

import de.zaid.taskmanager.dto.CreateTaskRequest;
import de.zaid.taskmanager.dto.TaskResponse;
import de.zaid.taskmanager.exception.TaskNotFoundException;
import de.zaid.taskmanager.model.Task;
import de.zaid.taskmanager.service.TaskManager;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
// Nimmt HTTP-Anfragen vom Frontend entgegen und gibt Antworten zurück.
public class TaskController {
    private final TaskManager taskManager;

    // Spring übergibt dem Controller hier den TaskManager.
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @PostMapping
    // Erstellt eine neue Aufgabe und antwortet mit 201 Created.
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        Task task = taskManager.createTask(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getDueDate());

        return ResponseEntity
                .created(URI.create("/api/tasks/" + task.getId()))
                .body(toResponse(task));
    }

    @GetMapping
    // Gibt Aufgaben normal oder sortiert an das Frontend zurück.
    public ResponseEntity<List<TaskResponse>> getTasks(
            @RequestParam(required = false) String sort) {
        List<Task> tasks;

        if (sort == null) {
            tasks = taskManager.getAllTasks();
        } else if (sort.equals("priority")) {
            tasks = taskManager.getTasksSortedByPriority();
        } else if (sort.equals("dueDate")) {
            tasks = taskManager.getTasksSortedByDueDate();
        } else {
            throw new IllegalArgumentException("Unknown sort value: " + sort);
        }

        List<TaskResponse> response = tasks.stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/complete")
    // Markiert eine Aufgabe als erledigt.
    public ResponseEntity<TaskResponse> completeTask(@PathVariable int id) {
        if (!taskManager.markTaskAsCompleted(id)) {
            throw new TaskNotFoundException(id);
        }

        Task task = taskManager.findTaskById(id);
        return ResponseEntity.ok(toResponse(task));
    }

    @DeleteMapping("/{id}")
    // Löscht eine Aufgabe über ihren Status.
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (!taskManager.deleteTask(id)) {
            throw new TaskNotFoundException(id);
        }

        return ResponseEntity.noContent().build();
    }

    // Wandelt das interne Task-Objekt in eine API-Antwort um.
    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getStatus(),
                task.getDueDate());
    }
}
