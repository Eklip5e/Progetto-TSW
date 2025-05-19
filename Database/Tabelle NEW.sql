CREATE TABLE Utente (
	IDUtente INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) UNIQUE,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Propic VARCHAR(255),
    IsAdmin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Genere (
IDGenere INT(7) AUTO_INCREMENT PRIMARY KEY,
Genere VARCHAR(30) NOT NULL,
Descrizione TEXT NOT NULL -- Necessaria per comprendere di cosa parla il genere --
);

CREATE TABLE Videogioco (
IDGame INT AUTO_INCREMENT PRIMARY KEY,
Titolo VARCHAR(70) NOT NULL,
Piattaforma VARCHAR(25) NOT NULL,
ReleaseDate DATE NOT NULL,
Descrizione TEXT,
Prezzo DECIMAL (5, 2) NOT NULL,
Sconto INT,
Produttore VARCHAR(50) NOT NULL
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
IDUser INT PRIMARY KEY ,
FOREIGN KEY (IDUser) REFERENCES Utente(IDUtente)
); -- Il carrello e' un entita' poiche' l'utente puo' acquistare piu' volte lo stesso videogioco o puo' fare diversi acquisti --

CREATE TABLE Screenshots (
Screenshot VARCHAR(255) PRIMARY KEY,
IDVideogioco INT NOT NULL,
FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (IDGame)
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
IDVideogioco INT NOT NULL,
FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (IDGame),
PRIMARY KEY (IDGenere, IDVideogioco)
);