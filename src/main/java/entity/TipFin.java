package entity;

public class TipFin {
    private int idtip_fin;
    private String nazvanie;

    public TipFin(int idtip_fin, String nazvanie) {
        this.idtip_fin = idtip_fin;
        this.nazvanie = nazvanie;
    }
    public int getIdtip_fin() {
        return idtip_fin;
    }

    public void setIdtip_fin(int idtip_fin) {
        this.idtip_fin = idtip_fin;
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
