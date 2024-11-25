import controller.TaskController;
import model.TaskModel;
import view.TaskView;

public class Main {
    public static void main(String[] args) {
        TaskModel model = new TaskModel();
        TaskController controller = new TaskController(model);
        new TaskView(controller, model);
    }
}
