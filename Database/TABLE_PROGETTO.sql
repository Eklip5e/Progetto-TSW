-- table --
CREATE TABLE UTENTE (
	usurname VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE DATI_FATTURAZIONE (
	id_dati_fatturazione INT AUTO_INCREMENT PRIMARY KEY,
    id_utente VARCHAR(50) NOT NULL,
    via VARCHAR(100) NOT NULL,
    città VARCHAR(100) NOT NULL,
    CAP VARCHAR(10) NOT NULL,
    numero_carta VARCHAR(19) NOT NULL,
    data_scadenza DATE NOT NULL,
    CVV VARCHAR(4) NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES UTENTE (usurname) ON DELETE CASCADE
);

CREATE TABLE VIDEOGIOCO (
	codice int AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(100) NOT NULL,
    piattaforma VARCHAR(50) NOT NULL,
    data_rilascio date NOT NULL,
    descrizione TEXT NOT NULL,
    prezzo DECIMAL(6, 2) NOT NULL,
    sconto INT NOT NULL,
    screenshot VARCHAR(255) NOT NULL
);

CREATE TABLE CHIAVE (
	codice_chiave VARCHAR(50) PRIMARY KEY,
    stato BOOLEAN NOT NULL DEFAULT TRUE,
    data_attivazione DATE NOT NULL,
    id_videogioco INT NOT NULL,
    FOREIGN KEY (id_videogioco) REFERENCES VIDEOGIOCO (codice)
);

CREATE TABLE GENERE (
    nome VARCHAR(50) PRIMARY KEY,
    descrizione TEXT NOT NULL
);

CREATE TABLE CARRELLO (
	id_carrello INT AUTO_INCREMENT PRIMARY KEY,
    totale DECIMAL(6, 2) NOT NULL DEFAULT 0.00,
    id_user VARCHAR(50) NOT NULL,
    id_promozione INT,
    FOREIGN KEY (id_user) REFERENCES UTENTE (usurname) ON DELETE CASCADE,
    FOREIGN KEY (id_promozione) REFERENCES PROMOZIONE (id_promozione)
);

CREATE TABLE PROMOZIONE (
	id_promozione INT AUTO_INCREMENT PRIMARY KEY,
    codice VARCHAR(20) NOT NULL UNIQUE,
    descrizione TEXT NOT NULL,
    stato BOOLEAN NOT NULL DEFAULT TRUE,
    tipo_sconto VARCHAR(10) CHECK (tipo_sconto IN ('percentuale', 'fisso')) NOT NULL,
    sconto INT NOT NULL
);

CREATE TABLE FATTURA (
	id_fattura INT AUTO_INCREMENT PRIMARY KEY,
    id_dati_fatturazione INT NOT NULL,
	FOREIGN KEY (id_dati_fatturazione) REFERENCES DATI_FATTURAZIONE (id_dati_fatturazione)
);

CREATE TABLE ACQUISTO (
	id_acquisto INT AUTO_INCREMENT PRIMARY KEY,
	data_acquisto DATETIME NOT NULL,
    id_utente VARCHAR(50) NOT NULL,
    id_carrello INT NOT NULL,
    id_fattura INT NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES UTENTE (usurname),
    FOREIGN KEY (id_carrello) REFERENCES CARRELLO (id_carrello),
    FOREIGN KEY (id_fattura) REFERENCES FATTURA (id_fattura)
);

CREATE TABLE RECENSIONE (
	data DATE NOT NULL,
    voto INT NOT NULL CHECK (voto >= 1 AND voto <= 5),
    commento TEXT NOT NULL,
    id_utente VARCHAR(50) NOT NULL,
    id_videogioco INT NOT NULL,
    PRIMARY KEY (id_utente, id_videogioco),
    FOREIGN KEY (id_utente) REFERENCES UTENTE (usurname),
    FOREIGN KEY (id_videogioco) REFERENCES VIDEOGIOCO (codice)
);

CREATE TABLE CONTIENE (
	quantità INT NOT NULL CHECK (quantità > 0),
    id_carrello INT NOT NULL,
    id_videogioco INT NOT NULL,
    PRIMARY KEY (id_carrello, id_videogioco),
    FOREIGN KEY (id_carrello) REFERENCES CARRELLO (id_carrello),
    FOREIGN KEY (id_videogioco) REFERENCES VIDEOGIOCO (codice)
);

CREATE TABLE VG (
	id_videogioco INT NOT NULL,
    id_genere VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_videogioco, id_genere),
    FOREIGN KEY (id_videogioco) REFERENCES VIDEOGIOCO (codice),
    FOREIGN KEY (id_genere) REFERENCES GENERE (nome)
);
