package entity;

import DBpodcl.DBProcessor;

public class Zadacha {
    private int idzadacha;
    private int nomer;
    private String nazvanie;
    private int procent;
    final DBProcessor c1 = new DBProcessor();

    public Zadacha (int idzadacha, int nomer, String nazvanie){
        this.idzadacha = idzadacha;
        this.nomer = nomer;
        this.nazvanie = nazvanie;
        this.procent = c1.procentVipolneniya(idzadacha);
    }

    public int getIdzadacha() {
        return idzadacha;
    }

    public void setIdzadacha(int idzadacha) {
        this.idzadacha = idzadacha;
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public int getNomer() {
        return nomer;
    }

    public void setNomer(int nomer) {
        this.nomer = nomer;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }
}
