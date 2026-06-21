import java.time.LocalDate;
import java.util.List;

public class TaskManagerManualTest {
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("=== TaskManager Manual Tests ===");

        testAddTask();
        testDeleteExistingTask();
        testDeleteNonExistingTask();
        testCompleteTask();
        testSortByPriority();
        testSortByDueDate();

        System.out.println();
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
    }

    private static void testAddTask() {
        TaskManager manager = new TaskManager();
        Task task = manager.createTask(
                "Study Java",
                "Review classes and objects",
                Priority.HIGH,
                LocalDate.of(2026, 7, 10));

        boolean result = manager.getAllTasks().size() == 1
                && manager.findTaskById(task.getId()) == task;

        printResult("Add task", result);
    }

    private static void testDeleteExistingTask() {
        TaskManager manager = new TaskManager();
        Task task = manager.createTask(
                "Delete test",
                "",
                Priority.LOW,
                LocalDate.of(2026, 8, 1));

        boolean deleted = manager.deleteTask(task.getId());
        boolean result = deleted && manager.findTaskById(task.getId()) == null;

        printResult("Delete task with existing ID", result);
    }

    private static void testDeleteNonExistingTask() {
        TaskManager manager = new TaskManager();
        boolean result = !manager.deleteTask(999);

        printResult("Delete task with non-existing ID", result);
    }

    private static void testCompleteTask() {
        TaskManager manager = new TaskManager();
        Task task = manager.createTask(
                "Complete test",
                "",
                Priority.MEDIUM,
                LocalDate.of(2026, 7, 20));

        boolean completed = manager.markTaskAsCompleted(task.getId());
        boolean result = completed && task.getStatus() == TaskStatus.COMPLETED;

        printResult("Complete task", result);
    }

    private static void testSortByPriority() {
        TaskManager manager = new TaskManager();
        manager.createTask("Low task", "", Priority.LOW, LocalDate.of(2026, 7, 1));
        manager.createTask("High task", "", Priority.HIGH, LocalDate.of(2026, 7, 2));
        manager.createTask("Medium task", "", Priority.MEDIUM, LocalDate.of(2026, 7, 3));

        List<Task> tasks = manager.getTasksSortedByPriority();
        boolean result = tasks.get(0).getPriority() == Priority.HIGH
                && tasks.get(1).getPriority() == Priority.MEDIUM
                && tasks.get(2).getPriority() == Priority.LOW;

        printResult("Sort tasks by priority", result);
    }

    private static void testSortByDueDate() {
        TaskManager manager = new TaskManager();
        manager.createTask("Later task", "", Priority.HIGH, LocalDate.of(2026, 9, 10));
        manager.createTask("First task", "", Priority.LOW, LocalDate.of(2026, 7, 5));
        manager.createTask("Middle task", "", Priority.MEDIUM, LocalDate.of(2026, 8, 15));

        List<Task> tasks = manager.getTasksSortedByDueDate();
        boolean result = tasks.get(0).getTitle().equals("First task")
                && tasks.get(1).getTitle().equals("Middle task")
                && tasks.get(2).getTitle().equals("Later task");

        printResult("Sort tasks by due date", result);
    }

    private static void printResult(String testName, boolean passed) {
        if (passed) {
            System.out.println("PASS: " + testName);
            passedTests++;
        } else {
            System.out.println("FAIL: " + testName);
            failedTests++;
        }
    }
}
