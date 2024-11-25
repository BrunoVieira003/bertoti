package model.strategies;

import model.Task;

import java.util.List;

public interface SortStrategy {
    void sort(List<Task> tasks);
}
