
import Task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
   static long ID = Long.MIN_VALUE;
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, SubTask> subTasks = new HashMap<>();
    private final Map<Long, Epic> epics = new HashMap<>();

    private static long generateID() {
        return ID++;
    }

    public List<Task> getTasks() {
        return new ArrayList<Task>(tasks.values());
    }
    public List<SubTask> getSubTasks() {
        return new ArrayList<SubTask>(subTasks.values());
    }
    public List<Epic> getEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }
    public void removeAllSubtasks() {
        subTasks.clear();
    }
    public void removeAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    public Task getTask(long id) {
        return tasks.get(id);
    }
    public SubTask getSubtask(long id) {
        return subTasks.get(id);
    }
    public Epic getEpic(long id) {
        return epics.get(id);
    }

    public void addTask(Task task) {
        task.setID(generateID());
        tasks.put(task.getID(),task);
    }
    public void addSubTask(SubTask subTask) {
        subTask.setID(generateID());
        subTasks.put(subTask.getID(), subTask);
    }
    public void addEpic(Epic epic) {
        epic.setID(generateID());
        epics.put(epic.getID(), epic);
    }

    public void updateTask(Task newTask) {
        if (tasks.containsKey(newTask.getID())) {
            tasks.put(newTask.getID(), newTask);
        }
    }
    public void updateSubtask(SubTask newSubTask) {
        if (subTasks.containsKey(newSubTask.getID())) {
            subTasks.put(newSubTask.getID(), newSubTask);
        }
    }
    public void updateEpic(Epic newEpic) {
        if (epics.containsKey(newEpic.getID())) {
            epics.put(newEpic.getID(), newEpic);
        }
    }

    public void removeTask(long id) {
        tasks.remove(id);
    }
    public void removeSubTask(long id) {
        subTasks.remove(id);
    }
    public void removeEpic(long id) {
        epics.remove(id);
    }

    public ArrayList<SubTask> getSubtasksWithEpicID(long id) {
        ArrayList<SubTask> sub = new ArrayList<>();
        for ( SubTask a : subTasks.values()) {
                if (a.getEpicID()==id)
                    sub.add(a);

        }
        return sub;
    }

    public void updateEpicStatus(Epic epic) {
        boolean isDone = true;
        boolean isNew = true;

        ArrayList<SubTask> subTask = new ArrayList<>();
        for (SubTask task : subTasks.values()) {
            if (task.getEpicID()==epic.getID())
                subTask.add(task);
        }

        for (var task : subTask) {
            if (task.getStatus() != TaskStatus.DONE) {
                isDone = false;
            }
            if (task.getStatus() != TaskStatus.NEW) {
                isNew = false;
            }
            if (subTasks.isEmpty()) {
                isNew = true;
            }
        }
        if (isDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (isNew) {
            epic.setStatus(TaskStatus.NEW);
        } else if (!isDone && !isNew) {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

}
