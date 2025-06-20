create schema unigame;
use unigame;

-- Tabella Utente
CREATE TABLE Utente (
	idUtente INT AUTO_INCREMENT PRIMARY KEY,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    dataDiNascita DATE NOT NULL
);

-- Tabella Videogioco
CREATE TABLE Videogioco (
idVideogioco INT AUTO_INCREMENT PRIMARY KEY,
titolo VARCHAR(70) NOT NULL,
piattaforma VARCHAR(25) NOT NULL,
dataRilascio DATE NOT NULL,
descrizione TEXT,
produttore VARCHAR(50) NOT NULL,
appIdSteam INT NOT NULL,
prezzo DECIMAL (5, 2) NOT NULL,
sconto INT
);

-- Creazione Banner
CREATE TABLE Banner (
	idBanner INT PRIMARY KEY,
    idVideogioco INT NOT NULL,
    FOREIGN KEY (idVideogioco) REFERENCES Videogioco (idVideogioco)
);

CREATE TABLE Carrello 
(
    idCarrello INT AUTO_INCREMENT PRIMARY KEY,
    idVideogioco INT NOT NULL,
    FOREIGN KEY (idVideogioco) REFERENCES Videogioco(idVideogioco)
);

CREATE TABLE Chiave (
    GameKey INT AUTO_INCREMENT PRIMARY KEY,
    IDVdeogioco INT NOT NULL,
    Venduto BOOLEAN NOT NULL DEFAULT FALSE
    Riscattato BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (IDVideogioco) REFERENCES Videogioco(idVideogioco)
);

CREATE TABLE Contiene (
    Quantita INT NOT NULL,
    DataInserimento DATE NOT NULL,
    Bloccato BOOLEAN NOT NULL DEFAULT FALSE,
    IDGioco INT NOT NULL,
    IDUtente INT NOT NULL,
    Totale DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (IDGioco) REFERENCES Videogioco(idVideogioco),
    FOREIGN KEY (IDUtente) REFERENCES Utente(idUtente)
);

CREATE TABLE DatiCarta (
    NumCarta VARCHAR(16) PRIMARY KEY,
    DataScadenza DATE NOT NULL,
    CVV INT(3) NOT NULL
);

CREATE TABLE Fattura (
    IDFatturazione INT AUTO_INCREMENT PRIMARY KEY,
    IDUser INT NOT NULL,
    Via VARCHAR(40) NOT NULL,
    CAP INT(5) NOT NULL,
    Citta VARCHAR(40) NOT NULL,
    DataAcquisto DATE NOT NULL,
    FOREIGN KEY (IDUser) REFERENCES Utente (IDUtente)
);

CREATE TABLE Fatturazione (
    IDUser INT REFERENCES Utente (IDUtente),
    IDFatturazione INT REFERENCES Fattura (IDFatturazione)
); 

CREATE TABLE Genere (
    IDGenere INT(7) AUTO_INCREMENT PRIMARY KEY,
    Genere VARCHAR(30) NOT NULL,
    Descrizione TEXT NOT NULL -- Necessaria per comprendere di cosa parla il genere --
);

CREATE TABLE Img (
    Copertina VARCHAR(255) PRIMARY KEY,
    Banner VARCHAR(255) NOT NULL,
    Video VARCHAR(255) NOT NULL,
    IDVideogioco INT NOT NULL,
    FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (idVideogioco)
);

CREATE TABLE PaymentMethod (
    IDUser INT REFERENCES Utente (IDUtente),
    NumCarta VARCHAR(16) REFERENCES DatiCarta (NumCarta)
);

CREATE TABLE RigaCarrello (
    idRiga INT AUTO_INCREMENT PRIMARY KEY,
    idUtente INT NOT NULL,
    idVideogioco INT NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente),
    FOREIGN KEY (idVideogioco) REFERENCES Videogioco(idVideogioco)
);

CREATE TABLE Sviluppatore (
    IDSviluppatore INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Descrizione TEXT NOT NULL
);

CREATE TABLE Sviluppa (
    IDVideogioco INT NOT NULL,
    IDSviluppatore INT NOT NULL,
    FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (idVideogioco),
    FOREIGN KEY (IDSviluppatore) REFERENCES Sviluppatore (IDSviluppatore)
);

CREATE TABLE Tag (
    IDTag INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(30) NOT NULL
);

CREATE TABLE VideogiocoTag (
    IDVideogioco INT NOT NULL,
    IDTag INT NOT NULL,
    FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (idVideogioco),
    FOREIGN KEY (IDTag) REFERENCES Tag (IDTag)
);

CREATE TABLE Wishlist (
    IDWishlist INT AUTO_INCREMENT PRIMARY KEY,
    IDUtente INT NOT NULL,
    FOREIGN KEY (IDUtente) REFERENCES Utente (idUtente)
);

CREATE TABLE WishlistItem (
    IDWishlist INT NOT NULL,
    IDVideogioco INT NOT NULL,
    FOREIGN KEY (IDWishlist) REFERENCES Wishlist (IDWishlist),
    FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (idVideogioco)
);


