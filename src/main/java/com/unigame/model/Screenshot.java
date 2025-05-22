package com.unigame.model;

public class Screenshot {
    private String screenshot;    // URL immagine (PK)
    private int idVideogioco;     // FK al videogame

    public Screenshot() {}

    public Screenshot(String screenshot, int idVideogioco) {
        this.screenshot = screenshot;
        this.idVideogioco = idVideogioco;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getIdVideogioco() {
        return idVideogioco;
    }

    public void setIdVideogioco(int idVideogioco) {
        this.idVideogioco = idVideogioco;
    }
}
