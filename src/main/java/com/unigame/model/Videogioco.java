package com.unigame.model;

import java.sql.Date;

public class Videogioco {
    private int idGame;
    private String titolo;
    private String piattaforma;
    private Date dataRilascio;
    private String descrizione;
    private String copertina;
    private double prezzo;
    private int sconto;
    private String produttore;

    public int getIDGame()
    {
        return idGame;
    }

    public void setIdGame(int idGame)
    {
        this.idGame = idGame;
    }

    public String getTitolo()
    {
        return titolo;
    }
    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }
    public String getPiattaforma() {
        return piattaforma;
    }
    public void setPiattaforma(String piattaforma)
    {
        this.piattaforma = piattaforma;
    }
    public Date getDataRilascio()
    {
        return dataRilascio;
    }
    public void setDataRilascio(Date dataRilascio)
    {
        this.dataRilascio = dataRilascio;
    }
    public String getDescrizione()
    {
        return descrizione;
    }
    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }
    public String getCopertina() {
        return copertina;
    }
    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }
    public double getPrezzo()
    {
        return prezzo;
    }
    public void setPrezzo(double prezzo)
    {
        this.prezzo = prezzo;
    }
    public int getSconto()
    {
        return sconto;
    }
    public void setSconto(int sconto)
    {
        this.sconto = sconto;
    }
    public String getProduttore()
    {
        return produttore;
    }
    public void setProduttore(String produttore)
    {
        this.produttore = produttore;
    }
}
