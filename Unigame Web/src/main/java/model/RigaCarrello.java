package model;

public class RigaCarrello {

    private int idRiga;
    private double prezzoUnitario;
    private int quantità;
    private int idUtente;
    private int idVideogioco;

    public int getIdRiga() {
        return idRiga;
    }

    public void setIdRiga(int idRiga) {
        this.idRiga = idRiga;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }
}
