package entity;

public class TipHaracteristica {
    private int idharacteristica;
    private String nazvanie;

    public TipHaracteristica (int idharacteristica, String nazvanie) {
        this.idharacteristica = idharacteristica;
        this.nazvanie = nazvanie;
    }
    public int getIdharacteristica() {
        return idharacteristica;
    }

    public void setIdharacteristica(int idharacteristica) {
        this.idharacteristica = idharacteristica;
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
