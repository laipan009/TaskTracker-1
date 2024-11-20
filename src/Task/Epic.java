package Task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Long> subtaskId = new ArrayList<>();

    public Epic (String name, String description) {
        super(name,description);
    }

    public Epic(String name, String description, long ID, String status) {
        super(name, description, ID, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void addSubTask(SubTask subTask) {
        subtaskId.add(subTask.getID());
    }

    @Override
    public TaskType getType() {
        return TaskType.EPIC;
    }

    public List<Long> getSubtaskId() {
        return subtaskId;
    }
}
