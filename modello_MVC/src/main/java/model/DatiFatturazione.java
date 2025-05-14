package model;

import java.util.Date;

public class DatiFatturazione {
    private static int idDatiFatturazione;
    private String idUtente;
    private String via;
    private String città;
    private String cap;
    private String numeroCarta;
    private Date dataScadenza;
    private int cvv;

    public static int getIdDatiFatturazione() {
        return idDatiFatturazione;
    }

    public void setIdDatiFatturazione(int idDatiFatturazione) {
        this.idDatiFatturazione = idDatiFatturazione;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
