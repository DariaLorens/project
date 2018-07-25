package entity;

import java.util.Date;

public class Podzadacha {
    private int nomer;
    private String nazvanie;
    private String status;
    private String fam;
    private String name;
    private String otch;
    private Date dataPostan;
    private Date dataZaver;
    private String comment;

    public Podzadacha(int nomer, String nazvanie, String status, String fam, String name, String otch, Date dataPostan, Date dataZaver, String comment) {
        this.nomer = nomer;
        this.nazvanie = nazvanie;
        this.status = status;
        this.fam = fam;
        this.name = name;
        this.otch = otch;
        this.dataPostan = dataPostan;
        this.dataZaver = dataZaver;
        this.comment = comment;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public Date getDataPostan() {
        return dataPostan;
    }

    public void setDataPostan(Date dataPostan) {
        this.dataPostan = dataPostan;
    }

    public Date getDataZaver() {
        return dataZaver;
    }

    public void setDataZaver(Date dataZaver) {
        this.dataZaver = dataZaver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
