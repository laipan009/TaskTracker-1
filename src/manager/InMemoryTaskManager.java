package manager;

import Task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private HistoryManager historyManager = Managers.getDefaultHistory();
    static long ID = Long.MIN_VALUE;
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, SubTask> subTasks = new HashMap<>();
    private final Map<Long, Epic> epics = new HashMap<>();

    public static long generateID() {
        return ID++;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    @Override
    public List<SubTask> getSubTasks() {
        return new ArrayList<SubTask>(subTasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllSubtasks() {
        subTasks.clear();
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public Task getTask(long id) {
       historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public SubTask getSubtask(long id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public Epic getEpic(long id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void addTask(Task task) {
      //  task.setID(generateID());
        tasks.put(task.getID(), task);
    }

    @Override
    public void addSubTask(SubTask subTask) {
      //  subTask.setID(generateID());
        subTasks.put(subTask.getID(), subTask);
    }

    @Override
    public void addEpic(Epic epic) {
       // epic.setID(generateID());
        epics.put(epic.getID(), epic);
    }

    @Override
    public void updateTask(Task newTask) {
        if (tasks.containsKey(newTask.getID())) {
            tasks.put(newTask.getID(), newTask);
        }
    }

    @Override
    public void updateSubtask(SubTask newSubTask) {
        if (subTasks.containsKey(newSubTask.getID())) {
            subTasks.put(newSubTask.getID(), newSubTask);
        }
    }

    @Override
    public void updateEpic(Epic newEpic) {
        if (epics.containsKey(newEpic.getID())) {
            epics.put(newEpic.getID(), newEpic);
        }
    }

    @Override
    public void removeTask(long id) {
        tasks.remove(id);
    }

    @Override
    public void removeSubTask(long id) {
        subTasks.remove(id);
    }

    @Override
    public void removeEpic(long id) {
        epics.remove(id);
    }

    @Override
    public List<SubTask> getSubtasksWithEpicID(long id) {
        ArrayList<SubTask> sub = new ArrayList<>();
        for (SubTask a : subTasks.values()) {
            if (a.getEpicID() == id)
                sub.add(a);

        }
        return sub;
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        boolean isDone = true;
        boolean isNew = true;

        ArrayList<SubTask> subTask = new ArrayList<>();
        for (SubTask task : subTasks.values()) {
            if (task.getEpicID() == epic.getID())
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}
