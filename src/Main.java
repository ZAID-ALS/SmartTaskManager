public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ConsoleUI consoleUI = new ConsoleUI(taskManager);
        consoleUI.start();
    }
}
