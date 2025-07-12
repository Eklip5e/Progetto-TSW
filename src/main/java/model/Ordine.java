package model;

import java.util.Date;

public class Ordine {
    private int idOrdine;
    private Date dataOrdine;
    private String stato;
    private double totale;
    private int idUtente;
    private int idFatturazione;

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdFatturazione() {
        return idFatturazione;
    }

    public void setIdFatturazione(int idFatturazione) {
        this.idFatturazione = idFatturazione;
    }
}
