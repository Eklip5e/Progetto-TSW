package model;

import java.util.Date;

public class Fatturazione {
    private int idFatturazione;
    private String numero;
    private String titolare;
    private Date scadenza;
    private String cvc;
    private int idUtente;

    public int getIdFatturazione() {
        return idFatturazione;
    }

    public void setIdFatturazione(int idFatturazione) {
        this.idFatturazione = idFatturazione;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitolare() {
        return titolare;
    }

    public void setTitolare(String titolare) {
        this.titolare = titolare;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
