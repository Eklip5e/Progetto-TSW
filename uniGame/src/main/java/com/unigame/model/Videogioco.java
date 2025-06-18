package com.unigame.model;

import java.sql.Date;

public class Videogioco {
    private int idVideogioco;
    private String titolo;
    private String piattaforma;
    private java.util.Date dataRilascio;
    private String descrizione;
    private String produttore;
    private int appIdSteam;
    private double prezzo;
    private int sconto;

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public java.util.Date getDataRilascio() {
        return dataRilascio;
    }

    public void setDataRilascio(java.util.Date dataRilascio) {
        this.dataRilascio = dataRilascio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public int getAppIdSteam() {
        return appIdSteam;
    }

    public void setAppIdSteam(int appIdSteam) {
        this.appIdSteam = appIdSteam;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }
}
