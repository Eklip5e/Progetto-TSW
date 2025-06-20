create schema unigame;
use unigame;

-- Completa
CREATE TABLE Utente (
	idUtente INT AUTO_INCREMENT PRIMARY KEY,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    dataDiNascita DATE NOT NULL
);

-- Completa
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

-- Completa
CREATE TABLE Banner (
	idBanner INT PRIMARY KEY,
    idVideogioco INT NOT NULL,
    FOREIGN KEY (idVideogioco) REFERENCES Videogioco (idVideogioco)
);

CREATE TABLE RigaCarrello (
    idRiga INT AUTO_INCREMENT PRIMARY KEY,
    idUtente INT NOT NULL,
    idVideogioco INT NOT NULL,
    FOREIGN KEY (idUtente) REFERENCES Utente(idUtente),
    FOREIGN KEY (idVideogioco) REFERENCES Videogioco(idVideogioco)
);

CREATE TABLE Genere (
IDGenere INT(7) AUTO_INCREMENT PRIMARY KEY,
Genere VARCHAR(30) NOT NULL,
Descrizione TEXT NOT NULL -- Necessaria per comprendere di cosa parla il genere --
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

CREATE TABLE DatiCarta (
NumCarta VARCHAR(16) PRIMARY KEY,
DataScadenza DATE NOT NULL,
CVV INT(3) NOT NULL
);

CREATE TABLE Fatturazione (
IDUser INT REFERENCES Utente (IDUtente),
IDFatturazione INT REFERENCES Fattura (IDFatturazione)
);

CREATE TABLE PaymentMethod (
IDUser INT REFERENCES Utente (IDUtente),
NumCarta VARCHAR(16) REFERENCES DatiCarta (NumCarta)
);

CREATE TABLE Chiave (
GameKey VARCHAR(15) PRIMARY KEY,
Venduto BOOLEAN DEFAULT FALSE,
Riscattato BOOLEAN DEFAULT FALSE,
IDVideogioco INT NOT NULL REFERENCES Videogioco (IDGame)
);

CREATE TABLE Carrello (
idUtente INT PRIMARY KEY,
idVideogioco INT NOT NULL,
FOREIGN KEY (idUtente) REFERENCES Utente(idUtente),
FOREIGN KEY (idVideogioco) REFERENCES Videogioco (idVideogioco)
); -- Il carrello e' un entita' poiche' l'utente puo' acquistare piu' volte lo stesso videogioco o puo' fare diversi acquisti --

CREATE TABLE Img (
Copertina VARCHAR(255) PRIMARY KEY,
Banner VARCHAR(255) NOT NULL,
Video VARCHAR(255) NOT NULL,
IDVideogioco INT NOT NULL,
FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (idVideogioco)
);

CREATE TABLE Contiene (
Quantita INT DEFAULT 1,
DataInserimento DATE,
Bloccato BOOLEAN DEFAULT FALSE,
IDGioco INT REFERENCES Videogioco (IDGame),
IDUser INT REFERENCES Carrello (IDUser),
Totale DECIMAL(5, 2)
);

CREATE TABLE VG (
IDGenere INT(7) NOT NULL,
FOREIGN KEY (IDGenere) REFERENCES Genere (IDGenere),
idVideogioco INT NOT NULL,
FOREIGN KEY (idVideogioco) REFERENCES Videogioco (idVideogioco),
PRIMARY KEY (IDGenere, idVideogioco)
);