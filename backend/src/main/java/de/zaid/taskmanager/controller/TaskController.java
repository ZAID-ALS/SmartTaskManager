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
public class TaskController {
    private final TaskManager taskManager;

    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @PostMapping
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
    public ResponseEntity<TaskResponse> completeTask(@PathVariable int id) {
        if (!taskManager.markTaskAsCompleted(id)) {
            throw new TaskNotFoundException(id);
        }

        Task task = taskManager.findTaskById(id);
        return ResponseEntity.ok(toResponse(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if (!taskManager.deleteTask(id)) {
            throw new TaskNotFoundException(id);
        }

        return ResponseEntity.noContent().build();
    }

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
