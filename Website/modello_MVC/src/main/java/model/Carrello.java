package model;

public class Carrello {
    private static int idCarrello;
    private double totale;
    private String idUser;
    private int idPromozione;

    public static int getIdCarrello() {
        return idCarrello;
    }

    public static void setIdCarrello(int idCarrello) {
        Carrello.idCarrello += 1;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getIdPromozione() {
        return idPromozione;
    }

    public void setIdPromozione(int idPromozione) {
        this.idPromozione = idPromozione;
    }
}
