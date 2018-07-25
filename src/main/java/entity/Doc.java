package entity;

public class Doc {
    private int iddoc;
    private String nazvanie;
    private String nomer;
    private String put;
    private int idtipdoc;
    private String tipdoc;

    public Doc (int iddoc, String nazvanie, String nomer, String put, int idtipdoc, String tipdoc) {
        this.iddoc = iddoc;
        this.nazvanie = nazvanie;
        this.nomer = nomer;
        this.put = put;
        this.idtipdoc = idtipdoc;
        this.tipdoc = tipdoc;

    }

    public int getIddoc() {
        return iddoc;
    }

    public void setIddoc(int iddoc) {
        this.iddoc = iddoc;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }

    public int getIdtipdoc() {
        return idtipdoc;
    }

    public void setIdtipdoc(int idtipdoc) {
        this.idtipdoc = idtipdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

}
