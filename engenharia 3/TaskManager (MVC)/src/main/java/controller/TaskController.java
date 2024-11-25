package controller;

import model.Task;
import model.TaskModel;
import model.strategies.SortStrategy;

public class TaskController {
    private final TaskModel model;

    public TaskController(TaskModel model) {
        this.model = model;
    }

    public void addTask(Task task) {
        model.addTask(task);
    }

    public void removeTask(Task task) {
        model.removeTask(task);
    }

    public void sortTasks(SortStrategy strategy) {
        model.sortTasks(strategy);
    }
}
