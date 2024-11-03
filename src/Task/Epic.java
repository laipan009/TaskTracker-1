package Task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    List<Long> subtaskId = new ArrayList<>();

    public Epic (String name, String description) {
        super(name,description);
    }
    @Override
    public String toString() {
        return "name = "+ super.getName()+" | "+
                "description = "+super.getDescription()+"\n";
    }

    public void addSubTask(SubTask subTask) {
        subtaskId.add(subTask.getID());
    }

    public List<Long> getSubtaskId() {
        return subtaskId;
    }
}
