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
public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) {
        Task task = new Task(nextId, title, description, priority, dueDate);
        tasks.add(task);
        nextId++;
        return task;
    }

    public List<Task> getAllTasks() {
        ArrayList<Task> activeTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() != TaskStatus.DELETED) {
                activeTasks.add(task);
            }
        }

        return activeTasks;
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id && task.getStatus() != TaskStatus.DELETED) {
                return task;
            }
        }

        return null;
    }

    public boolean deleteTask(int id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        task.setStatus(TaskStatus.DELETED);
        return true;
    }

    public boolean markTaskAsCompleted(int id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        task.setStatus(TaskStatus.COMPLETED);
        return true;
    }

    public List<Task> getTasksSortedByPriority() {
        ArrayList<Task> sortedTasks = new ArrayList<>(getAllTasks());
        sortedTasks.sort(Comparator.comparing(Task::getPriority));
        return sortedTasks;
    }

    public List<Task> getTasksSortedByDueDate() {
        ArrayList<Task> sortedTasks = new ArrayList<>(getAllTasks());
        sortedTasks.sort(Comparator.comparing(Task::getDueDate));
        return sortedTasks;
    }
}
