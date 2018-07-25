package entity;

public class TipDoc {
    private int id;
    private String nazvanie;

    public TipDoc(int idtip_doc, String nazvanie) {
        this.id = idtip_doc;
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

