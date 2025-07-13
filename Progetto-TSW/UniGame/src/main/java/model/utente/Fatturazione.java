package model.utente;

import java.util.Date;

public class Fatturazione {
    private int idFatturazione;
    private String nome;
    private String indirizzo;
    private String numeroCarta;
    private String titolareCarta;
    private Date dataScadenza;
    private char cvc;
    private int idUtente;

    public int getIdFatturazione() {
        return idFatturazione;
    }

    public void setIdFatturazione(int idFatturazione) {
        this.idFatturazione = idFatturazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getTitolareCarta() {
        return titolareCarta;
    }

    public void setTitolareCarta(String titolareCarta) {
        this.titolareCarta = titolareCarta;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public char getCvc() {
        return cvc;
    }

    public void setCvc(char cvc) {
        this.cvc = cvc;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
