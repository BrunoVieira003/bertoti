package model.strategies;

import model.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPriority implements SortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}
