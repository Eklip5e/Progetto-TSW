create schema unigame;
use unigame;

-- Completa
CREATE TABLE Utente (
	idUtente INT AUTO_INCREMENT PRIMARY KEY,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    dataDiNascita DATE NOT NULL
);

UPDATE Utente
SET admin = true
WHERE email = 'gennaropio.albano@outlook.it';

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

INSERT INTO Videogioco (titolo, piattaforma, dataRilascio, descrizione, produttore, appIdSteam, prezzo, sconto) VALUES
('The Witcher 3: Wild Hunt', 'PC', '2015-05-18', 'Open world RPG con narrazione profonda', 'CD Projekt', 292030, 39.99, 20),
('Cyberpunk 2077', 'PC', '2020-12-10', 'RPG futuristico open world', 'CD Projekt', 1091500, 59.99, 15),
('Dark Souls III', 'PC', '2016-04-12', 'RPG d’azione impegnativo', 'FromSoftware', 374320, 49.99, 25),
('DOOM Eternal', 'PC', '2020-03-20', 'FPS frenetico e violento', 'id Software', 782330, 59.99, 30),
('Red Dead Redemption 2', 'PC', '2019-11-05', 'Western open world epico', 'Rockstar Games', 1174180, 59.99, 35),
('Hades', 'PC', '2020-09-17', 'Rogue-like d’azione con storia mitologica', 'Supergiant Games', 1145360, 24.99, 40),
('Among Us', 'PC', '2018-06-15', 'Party game di deduzione sociale', 'Innersloth', 945360, 4.99, 50),
('Sekiro: Shadows Die Twice', 'PC', '2019-03-22', 'Action-adventure con combattimenti tattici', 'FromSoftware', 814380, 59.99, 20),
('The Elder Scrolls V: Skyrim', 'PC', '2011-11-11', 'RPG open world classico', 'Bethesda', 72850, 39.99, 25),
('Stardew Valley', 'PC', '2016-02-26', 'Simulatore agricolo rilassante', 'ConcernedApe', 413150, 14.99, 50),
('Minecraft', 'PC', '2011-11-18', 'Sandbox creativo senza limiti', 'Mojang', 322330, 26.95, 10),
('Fortnite', 'PC', '2017-07-21', 'Battle royale popolare', 'Epic Games', 332200, 0.00, 0),
('Control', 'PC', '2019-08-27', 'Action-adventure con poteri psichici', 'Remedy Entertainment', 870780, 29.99, 30),
('Resident Evil Village', 'PC', '2021-05-07', 'Horror survival con atmosfera cupa', 'Capcom', 1196590, 59.99, 15),
('Assassin’s Creed Valhalla', 'PC', '2020-11-10', 'RPG storico open world', 'Ubisoft', 1517290, 59.99, 20);
('Ghost of Tsushima', 'PlayStation', '2020-07-17', 'Azione open world ambientato nel Giappone feudale', 'Sucker Punch', 1245620, 59.99, 20),
('Horizon Zero Dawn', 'PlayStation', '2017-02-28', 'RPG d’azione con mondo post-apocalittico', 'Guerrilla Games', 1151640, 49.99, 25),
('God of War', 'PlayStation', '2018-04-20', 'Azione-avventura epica con mitologia nordica', 'Santa Monica Studio', 1593500, 59.99, 15),
('Spider-Man: Miles Morales', 'PlayStation', '2020-11-12', 'Avventura open world con supereroi Marvel', 'Insomniac Games', 1729690, 49.99, 30),
('Forza Horizon 5', 'Xbox', '2021-11-09', 'Racing open world nel Messico', 'Playground Games', 1551360, 59.99, 20),
('Halo Infinite', 'Xbox', '2021-12-08', 'FPS sci-fi epico', '343 Industries', 1240430, 59.99, 25),
('Gears 5', 'Xbox', '2019-09-10', 'TPS con campagna e multiplayer', 'The Coalition', 1236130, 39.99, 40),
('Sea of Thieves', 'Xbox', '2018-03-20', 'Azione multiplayer pirata', 'Rare', 1172620, 39.99, 35),
('Super Mario Odyssey', 'Nintendo', '2017-10-27', 'Platform 3D con Mario', 'Nintendo', 0, 59.99, 0),
('The Legend of Zelda: Breath of the Wild', 'Nintendo', '2017-03-03', 'Open world action-adventure', 'Nintendo', 0, 59.99, 0),
('Animal Crossing: New Horizons', 'Nintendo', '2020-03-20', 'Simulatore di vita rilassante', 'Nintendo', 0, 59.99, 0),
('Splatoon 3', 'Nintendo', '2022-09-09', 'Shooter multiplayer colorato', 'Nintendo', 0, 59.99, 10),
('Minecraft Dungeons', 'PC', '2020-05-26', 'Dungeon crawler nell’universo di Minecraft', 'Mojang', 1362280, 19.99, 30),
('Valheim', 'PC', '2021-02-02', 'Survival vichingo sandbox', 'Iron Gate Studio', 892970, 19.99, 40),
('Among Trees', 'PC', '2019-06-07', 'Survival sandbox rilassante', 'FJRD Interactive', 796310, 19.99, 25);

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