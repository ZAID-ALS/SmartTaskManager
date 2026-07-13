package de.zaid.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super("Task with ID " + id + " was not found");
    }
}
