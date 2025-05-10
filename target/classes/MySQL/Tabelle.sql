create table Negozio(
	nome varchar(100),
    citta varchar(100),
    via varchar(100),
    cap varchar(5),
    telefono varchar(15) not null,
    primary key(nome, citta, via, cap)
);

create table Impiegato(
	matricola varchar(4) primary key,
    nome varchar(100) not null,
    cognome varchar(100) not null,
    stipendio decimal(7, 2) not null,
    tipo varchar(100) not null,
    numero_cassa int,
    n_nome varchar(100) not null,
    n_citta varchar(100) not null,
    n_via varchar(100) not null,
    n_cap varchar(5) not null,
    data_assunzione date not null,
    foreign key(n_nome, n_citta, n_via, n_cap) references Negozio(nome, citta, via, cap)
);

create table Reparto(
	codice_reparto varchar(2) primary key,
    nome_reparto varchar(100) not null,
    n_nome varchar(100) not null,
    n_citta varchar(100) not null,
    n_via varchar(100) not null,
    n_cap varchar(5) not null,
    foreign key(n_nome, n_citta, n_via, n_cap) references Negozio(nome, citta, via, cap)
);

create table Videogioco(
	titolo varchar(100),
    piattaforma varchar(100),
    anno_uscita int not null,
    prezzo decimal(5, 2) not null,
    primary key(titolo, piattaforma)
);

create table Genere(
	genere varchar(100) primary key
);

create table Cliente(
	cf varchar(16) primary key, 
	nome varchar(100) not null, 
	cognome varchar(100) not null
);

create table Contatto(
	contatto varchar(100),
    cliente varchar(16),
    primary key(contatto, cliente),
    foreign key(cliente) references Cliente(cf)
);

create table Comprare(
	data datetime,
    reparto varchar(2),
    v_titolo varchar(100),
    v_piattaforma varchar(100),
    cliente varchar(16),
    quantita int,
    prezzo_totale decimal(6, 2),
    primary key(data, v_titolo, v_piattaforma, cliente),
	foreign key(v_titolo, v_piattaforma) references Videogioco(titolo, piattaforma),
	foreign key(cliente) references Cliente(cf)
);

create table Gestire(
	reparto varchar(2),
    impiegato varchar(4),
    primary key(reparto, impiegato),
    foreign key(reparto) references Reparto(codice_reparto),
    foreign key(impiegato) references Impiegato(matricola)
);

create table Contenere(
	reparto varchar(2),
    v_titolo varchar(100),
	v_piattaforma varchar(100),
    primary key(reparto, v_titolo, v_piattaforma),
    foreign key(reparto) references Reparto(codice_reparto),
    foreign key(v_titolo, v_piattaforma) references Videogioco(titolo, piattaforma)
);

create table Classificare(
	v_titolo varchar(100),
    v_piattaforma varchar(100),
    genere varchar(100),
    primary key(v_titolo, v_piattaforma, genere),
    foreign key(v_titolo, v_piattaforma) references Videogioco(titolo, piattaforma),
    foreign key(genere) references Genere(genere)
);