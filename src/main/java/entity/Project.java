package entity;

public class Project {
    private int idproject;
    private String nazvanie;
    private String kod;
    private String put;
    private String ryad;
    private String stolb;
    private String polka;
    private String comment;
    private String status;
    private String zakazchik;
    private String inn;


    public Project (int idproject, String nazvanie, String kod, String put, String ryad, String stolb, String polka,
                    String comment, String status, String zakazchik, String inn) {
        this.idproject = idproject;
        this.nazvanie = nazvanie;
        this.kod = kod;
        this.put = put;
        this.ryad = ryad;
        this.stolb = stolb;
        this.polka = polka;
        this.status = status;
        this.comment = comment;
        this.zakazchik = zakazchik;
        this.inn = inn;
    }


    public String getZakazchik() {
        return zakazchik;
    }

    public void setZakazchik(String zakazchik) {
        this.zakazchik = zakazchik;
    }

    public int getIdproject() {
        return idproject;
    }

    public void setIdproject(int idproject) {
        this.idproject = idproject;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }

    public String getRyad() {
        return ryad;
    }

    public void setRyad(String ryad) {
        this.ryad = ryad;
    }

    public String getStolb() {
        return stolb;
    }

    public void setStolb(String stolb) {
        this.stolb = stolb;
    }

    public String getPolka() {
        return polka;
    }

    public void setPolka(String polka) {
        this.polka = polka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
