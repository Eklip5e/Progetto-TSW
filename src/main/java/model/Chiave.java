package model;

import java.util.Date;

public class Chiave {
    private String gameKey; // Chiave primaria
    private boolean venduto;
    private boolean riscattato;
    private Date dataAttivazione;
    private int idVideogioco;

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public boolean isVenduto() {
        return venduto;
    }

    public void setVenduto(boolean venduto) {
        this.venduto = venduto;
    }

    public boolean isRiscattato() {
        return riscattato;
    }

    public void setRiscattato(boolean riscattato) {
        this.riscattato = riscattato;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(Date dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }
}