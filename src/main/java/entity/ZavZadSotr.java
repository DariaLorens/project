package entity;

public class ZavZadSotr {
    private String project;
    private String zadacha;
    private String podzadacha;
    private String datazaver;


    public ZavZadSotr (String project, String zadacha, String podzadacha, String datazaver) {
        this.project = project;
        this.zadacha = zadacha;
        this.podzadacha = podzadacha;
        this.datazaver = datazaver;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getZadacha() {
        return zadacha;
    }

    public void setZadacha(String zadacha) {
        this.zadacha = zadacha;
    }

    public String getPodzadacha() {
        return podzadacha;
    }

    public void setPodzadacha(String podzadacha) {
        this.podzadacha = podzadacha;
    }

    public String getDatazaver() {
        return datazaver;
    }

    public void setDatazaver(String datazaver) {
        this.datazaver = datazaver;
    }
}
