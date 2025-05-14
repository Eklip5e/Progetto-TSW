package model;

public class Promozione {
    private static int idPromozione;
    private int codice;
    private String descrizione;
    private boolean stato;
    private String tipoSconto;
    private int sconto;

    public static int getIdPromozione() {
        return idPromozione;
    }

    public static void setIdPromozione(int idPromozione) {
        Promozione.idPromozione += 1;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getTipoSconto() {
        return tipoSconto;
    }

    public void setTipoSconto(String tipoSconto) {
        this.tipoSconto = tipoSconto;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }
}
