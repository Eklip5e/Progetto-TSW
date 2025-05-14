package model;

import java.util.Date;

public class Chiave {
    private static int idChiave;
    private boolean stato;
    private Date dataAttivazione;
    private int idVideogioco;

    public static int getidChiave() {
        return idChiave;
    }

    public static void setidChiave(int id_carrello) {
        Chiave.idChiave += 1;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
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
