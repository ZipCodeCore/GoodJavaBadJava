// Level-2

import java.util.ArrayList;
import java.util.Scanner;

// This is a simple, useless, ToDo list application.
// It is a simple console application that allows the user to add tasks, list tasks, mark tasks as completed, and remove tasks.
// The tasks are stored in an ArrayList of Task objects.
// And are LOST when the program is closed.
// The Task class is a simple class that represents a task with an ID, description, and completion status.

// The main method of the GoodToDoList class is the entry point of the application.

// You need to make this app useful by adding a backend storage mechanism to store tasks between sessions. (Session being a running of the program.)
// You can use a file, a database, or any other storage mechanism you prefer.
// You need to modify the application to load tasks from storage when it starts and save tasks to storage when it exits.
// You can use any serialization/deserialization mechanism you prefer.
// You can use any file format or database you prefer.

// You can also add more features to the application, such as editing tasks, sorting tasks, filtering tasks, etc.
// You can also improve the user interface by adding more options and better error handling.

public class GoodToDoList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskIdCounter = 0;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GoodToDoList app = new GoodToDoList();
        app.runInteractive();
    }

    public GoodToDoList() {
        // Load tasks from storage
    }

    private void runInteractive() {
        String result = ""; // to store the result of the operation

        while (true) {
            System.out.println("To-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            result = ""; // Reset the result

            switch (choice) {
                case 1:
                    result = this.addTask();
                    break;
                case 2:
                    result = listTasks();
                    break;
                case 3:
                    result = markTaskAsCompleted();
                    break;
                case 4:
                    result = removeTask();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if (result.length() > 0) {
                System.out.println(result);
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getTaskId(String prompt) {
        String choice = this.getInput(prompt);
        int taskId = -1;
        try {
            taskId = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            taskId = -1;
        }
        return taskId;
    }

    private String addTask() {
        String description = this.getInput("Enter the task description: ");

        Task task = new Task(taskIdCounter, description);
        taskList.add(task);
        taskIdCounter++;
        return "Task added successfully!";
    }

    private String listTasks() {
        StringBuffer sb = new StringBuffer();
        if (taskList.isEmpty()) {
            sb.append("No tasks to display.");
        } else {
            sb.append("Tasks:\n");
            for (Task task : taskList) {
            sb.append(task).append("\n");
            }
        }
        return sb.toString();
    }

    private String markTaskAsCompleted() {
        int taskId = this.getTaskId("Enter the task ID to mark as completed: ");
        if (taskId == -1) {
            return "Invalid task ID.";
        }

        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.setCompleted(true);
                return "Task marked as completed.";
            }
        }
        return "Task not found.";
    }

    private String removeTask() {
        int taskId = this.getTaskId("Enter the task ID to remove: ");
        if (taskId == -1) {
            System.out.println();
            return "Invalid task ID.";
        }

        for (Task task : taskList) {
            if (task.getId() == taskId) {
                taskList.remove(task);
                System.out.println();
                return "Task removed.";
            }
        }
        return "Task not found.";
    }


// this is known as a Bean, a Java Bean is a simple Java class that follows some conventions:
// It must have a no-arg constructor.
// It must have private fields.
// It must have getter and setter methods for the fields.
// It must implement the Serializable interface (optional).
    class Task {
        private int id;
        private String description;
        private boolean completed;

        public Task(int id, String description) {
            this.id = id;
            this.description = description;
            this.completed = false;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return id + ". [" + (completed ? "X" : " ") + "] " + description;
        }
    }

}