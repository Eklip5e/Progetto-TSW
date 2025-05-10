
-- Tabella Utente
CREATE TABLE Utente (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Email varchar(100) not null unique,
    Username VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL, -- Password cifrata (es. BCrypt)
    Telefono VARCHAR(15),
    Via VARCHAR(100),
    Citta VARCHAR(100),
    CAP VARCHAR(5)
);

-- Tabella Carrello
CREATE TABLE Carrello (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PrezzoFinale DECIMAL(8, 2) DEFAULT 0.00,
    UtenteID INT NOT NULL,
    FOREIGN KEY (UtenteID) REFERENCES Utente(ID)
);

-- Tabella Promozioni
CREATE TABLE Promozioni (
    Codice VARCHAR(50) PRIMARY KEY,
    Descrizione VARCHAR(255)
);

-- Tabella Applied (Relazione tra Carrello e Promozioni)
CREATE TABLE Applied (
    CarrelloID INT,
    PromozioneCodice VARCHAR(50),
    PRIMARY KEY (CarrelloID, PromozioneCodice),
    FOREIGN KEY (CarrelloID) REFERENCES Carrello(ID),
    FOREIGN KEY (PromozioneCodice) REFERENCES Promozioni(Codice)
);

-- Tabella Videogioco
CREATE TABLE Videogioco (
    Codice INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Prezzo DECIMAL(6, 2) NOT NULL,
    Piattaforma VARCHAR(50) NOT NULL,
    Sconto DECIMAL(4, 2) DEFAULT 0.00,
    DataRilascio DATE
);

-- Tabella Genere
CREATE TABLE Genere (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NomeGenere VARCHAR(100) NOT NULL UNIQUE
);

-- Tabella VG (Relazione molti-a-molti tra Videogioco e Genere)
CREATE TABLE VG (
    VideogiocoCodice INT,
    GenereID INT,
    PRIMARY KEY (VideogiocoCodice, GenereID),
    FOREIGN KEY (VideogiocoCodice) REFERENCES Videogioco(Codice),
    FOREIGN KEY (GenereID) REFERENCES Genere(ID)
);

-- Tabella AreInserted (Relazione tra Videogioco e Carrello)
CREATE TABLE AreInserted (
    VideogiocoCodice INT,
    CarrelloID INT,
    Quantita INT DEFAULT 1,
    PRIMARY KEY (VideogiocoCodice, CarrelloID),
    FOREIGN KEY (VideogiocoCodice) REFERENCES Videogioco(Codice),
    FOREIGN KEY (CarrelloID) REFERENCES Carrello(ID)
);

-- Tabella Acquisto
CREATE TABLE Acquisto (
    Data DATETIME PRIMARY KEY,
    UtenteID INT NOT NULL,
    CarrelloID INT NOT NULL,
    FOREIGN KEY (UtenteID) REFERENCES Utente(ID),
    FOREIGN KEY (CarrelloID) REFERENCES Carrello(ID)
);

-- Tabella Fattura
CREATE TABLE Fattura (
    IDFatt INT AUTO_INCREMENT PRIMARY KEY,
    DataAcquisto DATETIME NOT NULL,
    PrezzoFinale DECIMAL(8, 2) NOT NULL,
    Indirizzo VARCHAR(255),
    Nome VARCHAR(100),
    Cognome VARCHAR(100),
    AcquistoData DATETIME,
    FOREIGN KEY (AcquistoData) REFERENCES Acquisto(Data)
);