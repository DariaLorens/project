package entity;

public class Zakazchik {
    private int idzakazchik;
    private String nazvanie;
    private String yr_adres;
    private String inn;


    public Zakazchik (int idzakazchik, String nazvanie, String yr_adres, String inn) {
        this.idzakazchik = idzakazchik;
        this.nazvanie = nazvanie;
        this.yr_adres = yr_adres;
        this.inn = inn;
    }

    public void setIdzakazchik(int idzakazchik) {
        this.idzakazchik = idzakazchik;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public void setYr_adres(String yr_adres) {
        this.yr_adres = yr_adres;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getNazvanie() { return nazvanie; }
    public String getYr_adres() { return yr_adres; }
    public String getInn() { return inn; }
    public int getIdzakazchik() {
        return idzakazchik;
    }

    @Override
    public String toString() {
        return nazvanie;
    }
}
