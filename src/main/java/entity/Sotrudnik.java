package entity;

import DBpodcl.DBProcessor;

public class Sotrudnik {
    private int idsotrudnik;
    private String name;
    private String fam;
    private String otch;
    private String dolgnost;
    private int procent;
    final DBProcessor c1 = new DBProcessor();


    public Sotrudnik(int idsotrudnik, String name, String fam, String otch, String dolgnost) {
        this.idsotrudnik = idsotrudnik;
        this.name = name;
        this.fam = fam;
        this.otch = otch;
        this.dolgnost = dolgnost;
    }

    public int getIdsotrudnik() {
        return idsotrudnik;
    }

    public void setIdsotrudnik(int idsotrudnik) {
        this.idsotrudnik = idsotrudnik;
    }

    public String getName() {
        return name;
    }
    public String getFam() { return fam; }
    public String getOtch() { return otch; }

    public void setName(String name) {
        this.name = name;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public String getDolgnost() {
        return dolgnost;
    }

    public void setDolgnost(String dolgnost) {
        this.dolgnost = dolgnost;
    }


    @Override
    public String toString() {
        String famNameOtch = fam + " " + name + " " + otch;
        return famNameOtch;
    }
}
