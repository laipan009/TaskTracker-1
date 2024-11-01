package Task;

public class SubTask extends Task{
    long epicID;
    public SubTask(String name, String description, long epicID) {
        super(name,description);
        this.epicID = epicID;
    }

    public long getEpicID() {
        return epicID;
    }

    public void setEpicID(long id) {
        epicID = id;
    }

    @Override
    public String toString() {
        return "name = "+ super.getName()+" | "+
                "description = "+super.getDescription()+"\n";
    }
}
