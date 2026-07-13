package de.zaid.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.zaid.taskmanager.model.Priority;
import de.zaid.taskmanager.model.Task;
import de.zaid.taskmanager.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void createTaskAssignsConsecutiveIds() {
        Task firstTask = taskManager.createTask(
                "First task", "", Priority.HIGH, LocalDate.of(2026, 7, 20));
        Task secondTask = taskManager.createTask(
                "Second task", "", Priority.LOW, LocalDate.of(2026, 7, 21));

        assertEquals(1, firstTask.getId());
        assertEquals(2, secondTask.getId());
    }

    @Test
    void getAllTasksReturnsActiveTasks() {
        Task firstTask = taskManager.createTask(
                "First task", "", Priority.HIGH, LocalDate.of(2026, 7, 20));
        Task secondTask = taskManager.createTask(
                "Second task", "", Priority.MEDIUM, LocalDate.of(2026, 7, 21));

        List<Task> tasks = taskManager.getAllTasks();

        assertEquals(List.of(firstTask, secondTask), tasks);
    }

    @Test
    void deleteTaskPerformsSoftDeleteAndHidesTask() {
        Task task = taskManager.createTask(
                "Delete task", "", Priority.LOW, LocalDate.of(2026, 7, 20));

        boolean deleted = taskManager.deleteTask(task.getId());

        assertTrue(deleted);
        assertEquals(TaskStatus.DELETED, task.getStatus());
        assertFalse(taskManager.getAllTasks().contains(task));
        assertNull(taskManager.findTaskById(task.getId()));
    }

    @Test
    void markTaskAsCompletedSetsCompletedStatus() {
        Task task = taskManager.createTask(
                "Complete task", "", Priority.MEDIUM, LocalDate.of(2026, 7, 20));

        boolean completed = taskManager.markTaskAsCompleted(task.getId());

        assertTrue(completed);
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    void getTasksSortedByPriorityOrdersHighBeforeMediumBeforeLow() {
        taskManager.createTask("Low", "", Priority.LOW, LocalDate.of(2026, 7, 20));
        taskManager.createTask("High", "", Priority.HIGH, LocalDate.of(2026, 7, 21));
        taskManager.createTask("Medium", "", Priority.MEDIUM, LocalDate.of(2026, 7, 22));

        List<Task> tasks = taskManager.getTasksSortedByPriority();

        assertEquals(Priority.HIGH, tasks.get(0).getPriority());
        assertEquals(Priority.MEDIUM, tasks.get(1).getPriority());
        assertEquals(Priority.LOW, tasks.get(2).getPriority());
    }

    @Test
    void getTasksSortedByDueDateOrdersEarliestDateFirst() {
        taskManager.createTask("Later", "", Priority.HIGH, LocalDate.of(2026, 8, 20));
        taskManager.createTask("Earliest", "", Priority.LOW, LocalDate.of(2026, 7, 10));
        taskManager.createTask("Middle", "", Priority.MEDIUM, LocalDate.of(2026, 7, 25));

        List<Task> tasks = taskManager.getTasksSortedByDueDate();

        assertEquals(LocalDate.of(2026, 7, 10), tasks.get(0).getDueDate());
        assertEquals(LocalDate.of(2026, 7, 25), tasks.get(1).getDueDate());
        assertEquals(LocalDate.of(2026, 8, 20), tasks.get(2).getDueDate());
    }
}
