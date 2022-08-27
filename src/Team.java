public class Team {
    private int id;
    private String name;

    public Team(String name,int id) {
        this.id = id;
        this.name = name;
        ;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

