package entity;

public class TipUser {
    private int id;
    private String nazvanie;

    public TipUser(int id, String nazvanie) {
        this.id = id;
        this.nazvanie = nazvanie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    @Override
    public String toString() {
        return nazvanie;
    }
}
