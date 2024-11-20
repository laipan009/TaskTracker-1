package Task;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;

public class Task {
    private String name;
    private String description;
    private long ID;
    private TaskStatus status;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        status = TaskStatus.NEW;
        ID = InMemoryTaskManager.generateID();
    }

    public Task(String name, String description, long ID, String status) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        setStatus(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public TaskType getType() {
        return TaskType.TASK;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setStatus(String str) {
        switch (str) {
            case "NEW" :
                status = TaskStatus.NEW;
                break;
            case "IN_PROGRESS":
                status = TaskStatus.IN_PROGRESS;
                break;
            case "DONE" :
                status = TaskStatus.DONE;
                break;
        }
    }

    @Override
    public String toString() {
        return "name = "+ name+" | "+
                "description = "+description+ " | ID "+ID +"\n";
    }
}
