CREATE TABLE Utente (
    Username VARCHAR(50) PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);

CREATE TABLE Genere (
IDGenere VARCHAR(30) NOT NULL PRIMARY KEY,
Descrizione TEXT NOT NULL -- Necessaria per comprendere di cosa parla il genere --
);

CREATE TABLE VG (
IDGenere VARCHAR(30) NOT NULL,
FOREIGN KEY (IDGenere) REFERENCES Genere (IDGenere),
IDVideogioco INT NOT NULL,
FOREIGN KEY (IDVideogioco) REFERENCES Videogioco (IDGame),
PRIMARY KEY (IDGenere, IDVideogioco)
);

CREATE TABLE Fattura (
IDFatturazione INT AUTO_INCREMENT PRIMARY KEY,
IDUtente VARCHAR(50) NOT NULL,
Via VARCHAR(40) NOT NULL,
CAP INT(5) NOT NULL,
Citta VARCHAR(40) NOT NULL,
DataAcquisto DATE NOT NULL
);

CREATE TABLE Fatturazione (
IDUser VARCHAR(50) REFERENCES Utente (Username),
IDFatturazione INT REFERENCES Fattura (IDFatturazione)
);

CREATE TABLE PaymentMethod (
IDUser VARCHAR(50) REFERENCES Utente (Username),
NumCarta INT(16) REFERENCES DatiCarta (NumCarta)
);

CREATE TABLE DatiCarta (
NumCarta INT(16) PRIMARY KEY,
DataScadenza DATE NOT NULL,
CVV INT(3) NOT NULL
);

CREATE TABLE Chiave (
GameKey VARCHAR(15) PRIMARY KEY,
Venduto BOOLEAN DEFAULT FALSE,
Riscattato BOOLEAN DEFAULT FALSE,
IDVideogioco INT NOT NULL REFERENCES Videogioco (IDGame)
);

CREATE TABLE Carrello (
IDUser VARCHAR(50) PRIMARY KEY ,
FOREIGN KEY (IDUser) REFERENCES Utente(Username)
); -- Il carrello e' un entita' poiche' l'utente puo' acquistare piu' volte lo stesso videogioco o puo' fare diversi acquisti --

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

CREATE TABLE Screenshots (
Screenshot VARCHAR(255) PRIMARY KEY,
IDVideogioco INT NOT NULL,
FOREIGN KEY (IDVideogioco) REFERENCES Vieogioco (IDGame)
);

CREATE TABLE Contiene (
Quantita INT DEFAULT 1,
DataInserimento DATE,
Bloccato BOOLEAN DEFAULT FALSE,
IDGioco INT REFERENCES Videogioco (IDGame),
IDUser VARCHAR(50) REFERENCES Carrello (IDUser)
);