package model;

import java.util.Date;

public class Acquisto {
    private static int idAcquisto;
    private Date dataAcquisto;
    private String idUtente;
    private int idCarrello;
    private int idFattura;

    public static int getIdAcquisto() {
        return idAcquisto;
    }

    public static void setIdAcquisto(int idAcquisto) {
        Acquisto.idAcquisto += 1;
    }

    public java.sql.Date getDataAcquisto() {
        return (java.sql.Date) dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdFattura() {
        return idFattura;
    }

    public void setIdFattura(int idFattura) {
        this.idFattura = idFattura;
    }
}
