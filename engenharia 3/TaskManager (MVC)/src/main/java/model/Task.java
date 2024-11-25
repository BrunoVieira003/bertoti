package model;

public class Task {
    private String name;
    private int priority;
    private String dueDate; // Format: YYYY-MM-DD

    public Task(String name, int priority, String dueDate) {
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public String getDueDate() { return dueDate; }
}
