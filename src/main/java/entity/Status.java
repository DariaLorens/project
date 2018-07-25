package entity;

public class Status {
    private int idstatus;
    private String nazvanie;

    public Status(int idstatus, String nazvanie) {
        this.idstatus = idstatus;
        this.nazvanie = nazvanie;
    }

    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
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
