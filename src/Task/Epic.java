package Task;

public class Epic extends Task {

    public Epic (String name, String description) {
        super(name,description);
    }
    @Override
    public String toString() {
        return "name = "+ super.getName()+" | "+
                "description = "+super.getDescription()+"\n";
    }
}
