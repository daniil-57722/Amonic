package sample;

public class User {
    String name;
    String lname;
    int role;

    public User(String name, String lname, int role) {
        this.role = role;
        this.name = name;
        this.lname = lname;
    }

    public String getName() {
        return name;
    }

    public String getLname() {
        return lname;
    }

    public int getRole() {
        return role;
    }
}
