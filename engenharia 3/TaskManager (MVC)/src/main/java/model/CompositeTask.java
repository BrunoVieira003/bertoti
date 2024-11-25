package model;

import java.util.ArrayList;
import java.util.List;

public class CompositeTask extends Task {
    private List<Task> subTasks = new ArrayList<>();

    public CompositeTask(String name, int priority, String dueDate) {
        super(name, priority, dueDate);
    }

    public void addTask(Task task) {
        subTasks.add(task);
    }

    public void removeTask(Task task) {
        subTasks.remove(task);
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }
}
