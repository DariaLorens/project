package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Finance {
    private int idfinance;
    private Date date;
    private String nazvanie;
    private BigDecimal summa;
    private String comment;

    public Finance (int idfinance, Date date, String nazvanie, BigDecimal summa, String comment) {
        this.idfinance = idfinance;
        this.date = date;
        this.nazvanie = nazvanie;
        this.summa = summa;
        this.comment = comment;
    }

    public int getIdfinance() {
        return idfinance;
    }

    public void setIdfinance(int idfinance) {
        this.idfinance = idfinance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
