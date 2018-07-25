package entity;

public class User {
    private String login;
    private String pass;
    private int id_tip;

    public User(String login, String pass, int id_tip) {
        this.login = login;
        this.pass = pass;
        this.id_tip = id_tip;
    }

    public String getLogin() { return login; }
    public String getPass() { return pass; }
    public int getId_tip() {
        return id_tip;
    }
}
