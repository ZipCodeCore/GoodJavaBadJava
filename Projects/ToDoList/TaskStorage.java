import java.io.*;
import java.util.ArrayList;

public class TaskStorage {

    private static final String FILE_PATH = "tasks.csv";
    private static final String HEADER = "id,description,completed";
    private static final String COUNTER_PREFIX = "#counter:";

    public void saveTasks(
        ArrayList<GoodToDoList.Task> tasks,
        int taskIdCounter
    ) {
        try (
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH)
            )
        ) {
            // Write the header
            writer.write(HEADER);
            writer.newLine();

            // Write the counter
            writer.write(COUNTER_PREFIX + taskIdCounter);
            writer.newLine();

            // Write each task
            for (GoodToDoList.Task task : tasks) {
                String line = String.format(
                    "%d,%s,%b",
                    task.getId(),
                    escapeCSV(task.getDescription()),
                    task.isCompleted()
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public TaskData loadTasks() {
        ArrayList<GoodToDoList.Task> tasks = new ArrayList<>();
        int taskIdCounter = 0;

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new TaskData(tasks, taskIdCounter);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Skip header
            String line = reader.readLine();
            if (line == null) {
                return new TaskData(tasks, taskIdCounter);
            }

            // Read counter
            line = reader.readLine();
            if (line != null && line.startsWith(COUNTER_PREFIX)) {
                taskIdCounter = Integer.parseInt(
                    line.substring(COUNTER_PREFIX.length())
                );
            }

            // Read tasks
            while ((line = reader.readLine()) != null) {
                GoodToDoList.Task task = parseTaskFromCSV(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return new TaskData(tasks, taskIdCounter);
    }

    private GoodToDoList.Task parseTaskFromCSV(String line) {
        try {
            String[] parts = line.split(",", 3);
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String description = unescapeCSV(parts[1]);
                boolean completed = Boolean.parseBoolean(parts[2]);

                // Create a new Task instance using reflection since Task is an inner class
                GoodToDoList todoList = new GoodToDoList();
                GoodToDoList.Task task = todoList.new Task(id, description);
                task.setCompleted(completed);
                return task;
            }
        } catch (Exception e) {
            System.err.println("Error parsing task: " + e.getMessage());
        }
        return null;
    }

    // Helper method to escape commas in the description
    private String escapeCSV(String value) {
        if (value == null) {
            return "";
        }
        // Replace " with "" and wrap in quotes if contains comma or quotes
        value = value.replace("\"", "\"\"");
        if (
            value.contains(",") || value.contains("\"") || value.contains("\n")
        ) {
            value = "\"" + value + "\"";
        }
        return value;
    }

    // Helper method to unescape CSV values
    private String unescapeCSV(String value) {
        if (value == null || value.length() == 0) {
            return "";
        }
        // Remove surrounding quotes if present
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
        }
        // Replace double quotes with single quotes
        return value.replace("\"\"", "\"");
    }

    public static class TaskData {

        private ArrayList<GoodToDoList.Task> tasks;
        private int taskIdCounter;

        public TaskData(ArrayList<GoodToDoList.Task> tasks, int taskIdCounter) {
            this.tasks = tasks;
            this.taskIdCounter = taskIdCounter;
        }

        public ArrayList<GoodToDoList.Task> getTasks() {
            return tasks;
        }

        public int getTaskIdCounter() {
            return taskIdCounter;
        }
    }
}
