package model;

import model.strategies.SortStrategy;

import java.util.ArrayList;
import java.util.List;

public class TaskModel {
    private List<Task> tasks = new ArrayList<>();
    private List<TaskObserver> observers = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers("'" + task.getName() + "' added to task list");
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        notifyObservers("'" + task.getName() + "' removed from task list");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (TaskObserver observer : observers) {
            observer.update(message);
        }
    }

    public void sortTasks(SortStrategy strategy) {
        strategy.sort(tasks);
        notifyObservers("");
    }
}
