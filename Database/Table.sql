CREATE TABLE UTENTE (
    id_utente INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    id_dati_fatturazione INT,  -- Collegamento ai dati di fatturazione
    FOREIGN KEY (id_dati_fatturazione) REFERENCES DATI_FATTURAZIONE(id_dati_fatturazione)
);

CREATE TABLE DATI_FATTURAZIONE (
    id_dati_fatturazione INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT,  -- Riferimento all'utente
    indirizzo VARCHAR(255) NOT NULL,
    città VARCHAR(100) NOT NULL,
    CAP VARCHAR(10) NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    nazione VARCHAR(100) NOT NULL,
    numero_carta VARCHAR(19) NOT NULL,  -- 16 caratteri + possibili spazi
    data_scadenza DATE NOT NULL,        -- data scadenza della carta (MM-YYYY)
    cvv VARCHAR(4) NOT NULL,            -- CVV o CVC (generalmente 3 o 4 caratteri)
    FOREIGN KEY (id_utente) REFERENCES UTENTE(id_utente)
);

create table Carrello (

);

create table Promozione {

}

create table Riga_carrello {

}

create table Videogioco {

}

create table Genere {

}

create table Chiave {

}

create table Acquisto {

}

create table Fattura {

}