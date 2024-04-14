import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

class Task {
    private int taskId;
    private String description;
    private Date dueDate;
    private boolean completed;


    public Task(int taskId, String description, Date dueDate) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    
    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }
}


class TaskManager {
    private ArrayList<Task> tasks;

   
    public TaskManager() {
        tasks = new ArrayList<>();
    }


    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully!");
    }


    public void markTaskCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.markCompleted();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }


    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }
        System.out.println("All Tasks:");
        for (Task task : tasks) {
            System.out.println("Task ID: " + task.getTaskId());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Due Date: " + new SimpleDateFormat("yyyy-MM-dd").format(task.getDueDate()));
            System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
            System.out.println();
        }
    }

  
    public void generateReportTasksDueToday() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Tasks Due Today:");
        for (Task task : tasks) {
            if (dateFormat.format(task.getDueDate()).equals(dateFormat.format(today))) {
                System.out.println("Task ID: " + task.getTaskId());
                System.out.println("Description: " + task.getDescription());
                System.out.println();
            }
        }
    }

    
    public int getTaskCount() {
        return tasks.size();
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Task Management App");
            System.out.println("1. Add New Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. Generate Report of Tasks Due Today");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Task Description: ");
                        String description = scanner.nextLine();
                        if (description.isEmpty()) {
                            throw new IllegalArgumentException("Task description cannot be empty!");
                        }
                        System.out.print("Enter Due Date (yyyy-MM-dd): ");
                        String dueDateString = scanner.nextLine();
                        try {
                            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                            if (dueDate.before(new Date())) {
                                throw new IllegalArgumentException("Due date must be in the future!");
                            }
                            Task newTask = new Task(taskManager.getTaskCount() + 1, description, dueDate);
                            taskManager.addTask(newTask);
                        } catch (Exception e) {
                            System.out.println("Invalid date format! Please enter date in yyyy-MM-dd format.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter Task ID to mark as completed: ");
                        int taskId = scanner.nextInt();
                        taskManager.markTaskCompleted(taskId);
                        break;
                    case 3:
                        taskManager.viewAllTasks();
                        break;
                    case 4:
                        taskManager.generateReportTasksDueToday();
                        break;
                    case 5:
                        System.out.println("Exiting Task Management App. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice! Please enter a number from 1 to 5.");
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }
}
