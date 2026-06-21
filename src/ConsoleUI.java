import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final TaskManager taskManager;
    private final Scanner scanner;

    public ConsoleUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        System.out.println("================================");
        System.out.println("       SMART TASK MANAGER");
        System.out.println("================================");

        while (running) {
            showMenu();
            int choice = readNumber("Choose an option: ");

            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    showTasks(taskManager.getAllTasks());
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    markTaskAsCompleted();
                    break;
                case 5:
                    showTasks(taskManager.getTasksSortedByPriority());
                    break;
                case 6:
                    showTasks(taskManager.getTasksSortedByDueDate());
                    break;
                case 0:
                    running = false;
                    System.out.println("Program closed. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Please choose an option from 0 to 6.");
            }
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("---------- MAIN MENU -----------");
        System.out.println("1 - Create a task");
        System.out.println("2 - Show all tasks");
        System.out.println("3 - Delete a task");
        System.out.println("4 - Mark a task as completed");
        System.out.println("5 - Sort tasks by priority");
        System.out.println("6 - Sort tasks by due date");
        System.out.println("0 - Exit");
        System.out.println("--------------------------------");
    }

    private void createTask() {
        String title = readTitle();

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        Priority priority = readPriority();
        LocalDate dueDate = readDueDate();

        Task task = taskManager.createTask(title, description, priority, dueDate);
        System.out.println("Success: Task " + task.getId() + " was created.");
    }

    private String readTitle() {
        String title;
        do {
            System.out.print("Title: ");
            title = scanner.nextLine();
            if (!InputValidator.isValidTitle(title)) {
                System.out.println("Error: The title must not be empty.");
            }
        } while (!InputValidator.isValidTitle(title));

        return title.trim();
    }

    private Priority readPriority() {
        Priority priority = null;
        while (priority == null) {
            System.out.print("Priority (HIGH, MEDIUM, LOW): ");
            priority = InputValidator.parsePriority(scanner.nextLine());
            if (priority == null) {
                System.out.println("Error: Enter HIGH, MEDIUM, or LOW.");
            }
        }

        return priority;
    }

    private LocalDate readDueDate() {
        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Due date (YYYY-MM-DD): ");
            dueDate = InputValidator.parseDate(scanner.nextLine());
            if (dueDate == null) {
                System.out.println("Error: Enter a valid date in yyyy-MM-dd format.");
            }
        }

        return dueDate;
    }

    private void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available. Create a task using menu option 1.");
            return;
        }

        System.out.println();
        System.out.println("============ TASKS ============");
        for (Task task : tasks) {
            printTask(task);
            System.out.println("--------------------------------");
        }
    }

    private void printTask(Task task) {
        String description = task.getDescription().isEmpty()
                ? "No description"
                : task.getDescription();

        System.out.println("ID:          " + task.getId());
        System.out.println("Title:       " + task.getTitle());
        System.out.println("Description: " + description);
        System.out.println("Priority:    " + task.getPriority());
        System.out.println("Status:      " + task.getStatus());
        System.out.println("Due date:    " + task.getDueDate());
    }

    private void deleteTask() {
        int id = readTaskId();
        if (taskManager.deleteTask(id)) {
            System.out.println("Success: Task " + id + " was deleted.");
        } else {
            System.out.println("Error: No task with ID " + id + " was found.");
        }
    }

    private void markTaskAsCompleted() {
        int id = readTaskId();
        if (taskManager.markTaskAsCompleted(id)) {
            System.out.println("Success: Task " + id + " was marked as completed.");
        } else {
            System.out.println("Error: No task with ID " + id + " was found.");
        }
    }

    private int readNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private int readTaskId() {
        while (true) {
            int id = readNumber("Enter task ID: ");
            if (InputValidator.isValidTaskId(id)) {
                return id;
            }

            System.out.println("Error: Task ID must be a positive number.");
        }
    }
}
