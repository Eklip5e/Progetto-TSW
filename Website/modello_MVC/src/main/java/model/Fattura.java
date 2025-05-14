package model;

public class Fattura {
    private static int idFattura;
    private int idDatiFatturazione;

    public int getIdFattura() {
        return idFattura;
    }

    public void setIdFattura(int idFattura) {
        this.idFattura += 1;
    }

    public int getIdDatiFatturazione() {
        return idDatiFatturazione;
    }

    public void setIdDatiFatturazione(int idDatiFatturazione) {
        this.idDatiFatturazione = idDatiFatturazione;
    }
}
