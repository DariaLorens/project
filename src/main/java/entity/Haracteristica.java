package entity;

public class Haracteristica {
    private int idproject_haracteristica;
    private String nazvanie;
    private String soderjanie;

    public Haracteristica (int idproject_haracteristica, String nazvanie, String soderjanie) {
        this.idproject_haracteristica = idproject_haracteristica;
        this.nazvanie = nazvanie;
        this.soderjanie = soderjanie;

    }

    public int getIdproject_haracteristica() {
        return idproject_haracteristica;
    }

    public void setIdproject_haracteristica(int idproject_haracteristica) {
        this.idproject_haracteristica = idproject_haracteristica;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getSoderjanie() {
        return soderjanie;
    }

    public void setSoderjanie(String soderjanie) {
        this.soderjanie = soderjanie;
    }
}
