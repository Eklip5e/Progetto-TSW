package model;

public class RigaOrdine {
    private int idRigaOrdine;
    private int idOrdine;
    private int idVideogioco;
    private String chiave;
    private double prezzoUnitario;

    public int getIdRigaOrdine() {
        return idRigaOrdine;
    }

    public void setIdRigaOrdine(int idRigaOrdine) {
        this.idRigaOrdine = idRigaOrdine;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }
}
