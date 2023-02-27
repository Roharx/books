package be;
/*
@author Bálint Farkas
 */
public class Author {
    private final int id;
    private final String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return
                id + "," + name;
    }
}
