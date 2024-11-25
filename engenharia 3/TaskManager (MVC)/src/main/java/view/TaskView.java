package view;

import controller.TaskController;
import model.CompositeTask;
import model.Task;
import model.TaskObserver;
import model.TaskModel;
import model.strategies.SortByDueDate;
import model.strategies.SortByPriority;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Stack;

public class TaskView extends JFrame implements TaskObserver {
    private final TaskController controller;
    private final TaskModel model;

    private DefaultTableModel tableModel;
    private JTable taskTable;
    private JButton goBackButton;

    private final Stack<CompositeTask> taskHierarchy = new Stack<>();

    public TaskView(TaskController controller, TaskModel model) {
        this.controller = controller;
        this.model = model;

        model.addObserver(this);

        initUI();
    }

    private void initUI() {
        setTitle("Task Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Task Table
        tableModel = new DefaultTableModel(new Object[]{"Name", "Priority", "Due Date"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(taskTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton addSubtaskButton = new JButton("Add Subtask");
        JButton removeButton = new JButton("Remove Task");
        JButton viewSubtasksButton = new JButton("View Subtasks");
        goBackButton = new JButton("Go Back");
        JButton sortPriorityButton = new JButton("Sort by Priority");
        JButton sortDueDateButton = new JButton("Sort by Due Date");

        buttonPanel.add(addButton);
        buttonPanel.add(addSubtaskButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewSubtasksButton);
        buttonPanel.add(goBackButton);
        buttonPanel.add(sortPriorityButton);
        buttonPanel.add(sortDueDateButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showAddTaskDialog(null));
        addSubtaskButton.addActionListener(e -> addSubtask());
        removeButton.addActionListener(e -> removeSelectedTask());
        viewSubtasksButton.addActionListener(e -> viewSubtasks());
        goBackButton.addActionListener(e -> goBack());
        sortPriorityButton.addActionListener(e -> controller.sortTasks(new SortByPriority()));
        sortDueDateButton.addActionListener(e -> controller.sortTasks(new SortByDueDate()));

        goBackButton.setEnabled(false);

        setVisible(true);
    }

    private void showAddTaskDialog(CompositeTask parentTask) {
        JDialog dialog = new JDialog(this, parentTask == null ? "Add Task" : "Add Subtask", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JTextField nameField = new JTextField();
        JTextField priorityField = new JTextField();
        JTextField dueDateField = new JTextField();

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Priority:"));
        dialog.add(priorityField);
        dialog.add(new JLabel("Due Date (YYYY-MM-DD):"));
        dialog.add(dueDateField);

        JButton addTaskButton = new JButton("Add");
        dialog.add(new JLabel());
        dialog.add(addTaskButton);

        addTaskButton.addActionListener(e -> {
            String name = nameField.getText();
            int priority = Integer.parseInt(priorityField.getText());
            String dueDate = dueDateField.getText();
            Task task = new Task(name, priority, dueDate);

            if (parentTask != null) {
                parentTask.addTask(task);
            } else {
                controller.addTask(task);
            }

            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    private void addSubtask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            String taskName = (String) tableModel.getValueAt(selectedRow, 0);
            Task selectedTask = findTaskByName(taskName);

            if (selectedTask != null) {
                CompositeTask compositeTask;
                if (!(selectedTask instanceof CompositeTask)) {
                    compositeTask = new CompositeTask(selectedTask.getName(),
                            selectedTask.getPriority(),
                            selectedTask.getDueDate());

                    // Replace the task in the model with the new CompositeTask
                    controller.removeTask(selectedTask);
                    controller.addTask(compositeTask);
                } else {
                    compositeTask = (CompositeTask) selectedTask;
                }

                showAddTaskDialog(compositeTask);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No task selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewSubtasks() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            String taskName = (String) tableModel.getValueAt(selectedRow, 0);
            Task selectedTask = findTaskByName(taskName);

            if (selectedTask instanceof CompositeTask) {
                CompositeTask compositeTask = (CompositeTask) selectedTask;
                taskHierarchy.push(compositeTask);
                refreshTaskTable(compositeTask.getSubTasks());
                goBackButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selected task has no subtasks", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No task selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        if (!taskHierarchy.isEmpty()) {
            taskHierarchy.pop();
            if (taskHierarchy.isEmpty()) {
                refreshTaskTable(model.getTasks());
                goBackButton.setEnabled(false);
            } else {
                refreshTaskTable(taskHierarchy.peek().getSubTasks());
            }
        }
    }

    private void removeSelectedTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow >= 0) {
            String taskName = (String) tableModel.getValueAt(selectedRow, 0);
            Task selectedTask = findTaskByName(taskName);

            if (selectedTask != null) {
                if (taskHierarchy.isEmpty()) {
                    controller.removeTask(selectedTask);
                    update("'" + selectedTask.getName() + "' removed from task list");
                } else {
                    CompositeTask currentCompositeTask = taskHierarchy.peek();
                    currentCompositeTask.removeTask(selectedTask);
                    update("'" + selectedTask.getName() +"' removed from '" + currentCompositeTask.getName() + "' subtask list");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No task selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private Task findTaskByName(String name) {
        List<Task> currentTasks = taskHierarchy.isEmpty() ? model.getTasks() : taskHierarchy.peek().getSubTasks();
        return currentTasks.stream().filter(task -> task.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void update(String message) {
        if (taskHierarchy.isEmpty()) {
            refreshTaskTable(model.getTasks());
        } else {
            refreshTaskTable(taskHierarchy.peek().getSubTasks());
        }
        System.out.println(message);
    }

    private void refreshTaskTable(List<Task> tasks) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Task task : tasks) {
            tableModel.addRow(new Object[]{task.getName(), task.getPriority(), task.getDueDate()});
        }
    }
}
