insert into Negozio(nome, citta, via, cap, telefono)
values
('NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '02-12345678'),
('NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '06-23456789'),
('NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '081-3456789');

insert into Impiegato(matricola, nome, cognome, stipendio, tipo, numero_cassa, n_nome, n_citta, n_via, n_cap, data_assunzione)
values
-- Milano --
('M001', 'Mario', 'Rossi', 30000, 'gestore reparto', NULL, 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '2023-05-01'),
('M002', 'Lucia', 'Bianchi', 35000, 'gestore reparto', NULL, 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '2023-06-15'),
('M003', 'Giovanni', 'Neri', 29000, 'cassiere', 1, 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '2023-04-20'),
('M004', 'Paola', 'Verdi', 32000, 'addetto alle vendite', NULL, 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '2023-07-01'),
('M005', 'Luca', 'Gialli', 28000, 'addetto alle pulizie', NULL, 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121', '2023-08-10'),

-- Roma --
('R001', 'Alessandra', 'Ferro', 31000, 'cassiere', 1, 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '2023-03-15'),
('R002', 'Marco', 'Pellegrini', 33000, 'gestore reparto', NULL, 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '2023-06-01'),
('R003', 'Chiara', 'Belli', 29500, 'cassiere', 2, 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '2023-05-10'),
('R004', 'Roberto', 'Corsi', 30500, 'gestore reparto', NULL, 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '2023-04-01'),
('R005', 'Elena', 'Moretti', 27500, 'addetto alle pulizie', NULL, 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187', '2023-09-15'),

-- Napoli --
('N001', 'Francesco', 'Della Corte', 32000, 'cassiere', 1, 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '2023-02-10'),
('N002', 'Giulia', 'Martino', 34000, 'gestore reparto', NULL, 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '2023-07-15'),
('N003', 'Antonio', 'Lombardi', 30000, 'cassiere', 2, 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '2023-06-20'),
('N004', 'Sofia', 'Ferrara', 29000, 'addetto alle vendite', NULL, 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '2023-08-05'),
('N005', 'Federico', 'Esposito', 27000, 'addetto alle pulizie', NULL, 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134', '2023-10-01');

insert into Reparto(codice_reparto, nome_reparto, n_nome, n_citta, n_via, n_cap)
values
-- Milano --
('M1', 'PlayStation', 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121'),
('M2', 'Xbox', 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121'),
('M3', 'Nintendo Switch', 'NextLevel Milano', 'Milano', 'Via Monte Napoleone, 20', '20121'),

-- Roma --
('R1', 'PlayStation', 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187'),
('R2', 'Xbox', 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187'),
('R3', 'Nintendo Switch', 'NextLevel Roma', 'Roma', 'Via Condotti, 85', '00187'),

-- Napoli --
('N1', 'PlayStation', 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134'),
('N2', 'Xbox', 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134'),
('N3', 'Nintendo Switch', 'NextLevel Napoli', 'Napoli', 'Via Toledo, 50', '80134');

insert into Videogioco(titolo, piattaforma, anno_uscita, prezzo)
values
('FIFA 2025', 'PS5', 2025, 60.00),
('Gran Turismo 7', 'PS5', 2023, 70.00),
('Demon’s Souls', 'PS5', 2020, 75.00),
('Halo Infinite', 'Xbox Series X', 2023, 55.00),
('Forza Horizon 5', 'Xbox Series X', 2022, 65.00),
('Gears 5', 'Xbox Series X', 2019, 50.00),
('The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 2023, 60.00),
('Super Smash Bros. Ultimate', 'Nintendo Switch', 2018, 55.00),
('Mario Kart 8 Deluxe', 'Nintendo Switch', 2017, 50.00),
('Spider-Man 2', 'PS5', 2023, 65.00),
('Ratchet & Clank: Rift Apart', 'PS5', 2021, 70.00),
('Returnal', 'PS5', 2021, 60.00),
('Minecraft', 'Xbox Series X', 2020, 35.00),
('Sea of Thieves', 'Xbox Series X', 2021, 45.00),
('State of Decay 2', 'Xbox Series X', 2018, 40.00),
('Animal Crossing: New Horizons', 'Nintendo Switch', 2020, 55.00),
('Super Mario Odyssey', 'Nintendo Switch', 2017, 50.00),
('Splatoon 2', 'Nintendo Switch', 2017, 45.00),
('Horizon Forbidden West', 'PS5', 2022, 65.00),
('The Last of Us Part II', 'PS5', 2020, 70.00),
('God of War Ragnarök', 'PS5', 2022, 75.00),
('Fable IV', 'Xbox Series X', 2024, 60.00),
('Forza Motorsport 7', 'Xbox Series X', 2020, 55.00),
('Destiny 2', 'Xbox Series X', 2017, 50.00),
('Fire Emblem: Three Houses', 'Nintendo Switch', 2019, 60.00),
('The Legend of Zelda: Breath of the Wild', 'Nintendo Switch', 2017, 65.00),
('Xenoblade Chronicles 2', 'Nintendo Switch', 2017, 55.00);

insert into Genere(genere) 
values
('Sport'),
('Corsa'),
('Simulazione'),
('Azione'),
('Avventura'),
('Sparatutto'),
('Open World'),
('Sandbox'),
('Platform');

insert into Cliente(cf, nome, cognome)
values
('RSSMRA85M01H501Z', 'Mario', 'Rossi'),
('VRTGNN90L01H501Y', 'Giulia', 'Verdi'),
('BNCPFL88P01H501X', 'Paolo', 'Bianchi'),
('LMNLSS82C01H501U', 'Laura', 'Neri');

insert into Contatto(contatto, cliente)
values
('3456789012', 'RSSMRA85M01H501Z'),
('3481234567', 'VRTGNN90L01H501Y'),
('3398765432', 'BNCPFL88P01H501X'),
('3334445566', 'LMNLSS82C01H501U');

insert into Comprare(data, reparto, v_titolo, v_piattaforma, cliente, quantita, prezzo_totale)
values
('2024-12-03 11:48:00', 'M1', 'Spider-Man 2', 'PS5', 'VRTGNN90L01H501Y', 2, 130),
('2024-12-01 10:15:00', 'M1', 'FIFA 2025', 'PS5', 'RSSMRA85M01H501Z', 2, 120.00),
('2024-11-15 14:30:00', 'M2', 'Halo Infinite', 'Xbox Series X', 'VRTGNN90L01H501Y', 1, 55.00),
('2024-12-10 09:00:00', 'M3', 'The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 'VRTGNN90L01H501Y', 3, 180.00),
('2024-11-01 16:45:00', 'M1', 'Gran Turismo 7', 'PS5', 'BNCPFL88P01H501X', 1, 70.00),
('2024-12-05 11:45:00', 'M1', 'Spider-Man 2', 'PS5', 'RSSMRA85M01H501Z', 1, 65.00),

('2024-12-03 11:45:00', 'R1', 'Spider-Man 2', 'PS5', 'BNCPFL88P01H501X', 1, 65.00),
('2024-11-03 11:45:00', 'R1', 'Spider-Man 2', 'PS5', 'LMNLSS82C01H501U', 1, 65.00),
('2024-11-20 15:30:00', 'R2', 'Minecraft', 'Xbox Series X', 'RSSMRA85M01H501Z', 2, 70.00),
('2024-12-12 17:00:00', 'R3', 'Animal Crossing: New Horizons', 'Nintendo Switch', 'VRTGNN90L01H501Y', 1, 55.00),
('2024-12-08 16:00:00', 'R3', 'Splatoon 2', 'Nintendo Switch', 'LMNLSS82C01H501U', 3, 150.00),

('2024-11-30 19:30:00', 'N1', 'God of War Ragnarök', 'PS5', 'RSSMRA85M01H501Z', 1, 65),
('2024-11-30 19:30:00', 'N1', 'God of War Ragnarök', 'PS5', 'VRTGNN90L01H501Y', 1, 65),
('2024-11-30 19:30:00', 'N1', 'God of War Ragnarök', 'PS5', 'BNCPFL88P01H501X', 3, 195),
('2024-11-25 13:20:00', 'N1', 'Horizon Forbidden West', 'PS5', 'BNCPFL88P01H501X', 1, 65.00),
('2024-12-05 10:00:00', 'N2', 'Fable IV', 'Xbox Series X', 'LMNLSS82C01H501U', 3, 180.00),
('2024-12-15 18:30:00', 'N3', 'Fire Emblem: Three Houses', 'Nintendo Switch', 'RSSMRA85M01H501Z', 2, 120.00),
('2024-11-30 19:30:00', 'N1', 'God of War Ragnarök', 'PS5', 'LMNLSS82C01H501U', 2, 130.00),
('2024-12-10 21:00:00', 'N3', 'Super Mario Odyssey', 'Nintendo Switch', 'VRTGNN90L01H501Y', 1, 60.00);

insert into Gestire(reparto, impiegato)
values
-- Milano --
('M1', 'M001'),
('M2', 'M002'),
('M3', 'M001'),
('M1', 'M002'),
('M2', 'M001'),
('M3', 'M002'),

-- Roma --
('R1', 'R002'),
('R2', 'R004'),
('R3', 'R002'),
('R1', 'R004'),
('R2', 'R002'),
('R3', 'R004'),

-- Napoli --
('N1', 'N002'),
('N2', 'N002'),
('N3', 'N002');

insert into Contenere(reparto, v_titolo, v_piattaforma)
values
-- Milano --
('M1', 'FIFA 2025', 'PS5'),
('M1', 'Gran Turismo 7', 'PS5'),
('M2', 'Forza Horizon 5', 'Xbox Series X'),
('M2', 'Minecraft', 'Xbox Series X'),
('M3', 'The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch'),
('M3', 'Animal Crossing: New Horizons', 'Nintendo Switch'),

-- Roma --
('R1', 'FIFA 2025', 'PS5'),
('R1', 'Gran Turismo 7', 'PS5'),
('R1', 'Spider-Man 2', 'PS5'),
('R2', 'Halo Infinite', 'Xbox Series X'),
('R2', 'Forza Horizon 5', 'Xbox Series X'),
('R2', 'Minecraft', 'Xbox Series X'),
('R3', 'The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch'),
('R3', 'Super Mario Odyssey', 'Nintendo Switch'),
('R3', 'Animal Crossing: New Horizons', 'Nintendo Switch'),

-- Napoli --
('N1', 'FIFA 2025', 'PS5'),
('N1', 'Gran Turismo 7', 'PS5'),
('N1', 'Spider-Man 2', 'PS5'),
('N2', 'Forza Horizon 5', 'Xbox Series X'),
('N2', 'Minecraft', 'Xbox Series X'),
('N3', 'Animal Crossing: New Horizons', 'Nintendo Switch');

insert into Classificare(v_titolo, v_piattaforma, genere)
values
('FIFA 2025', 'PS5', 'Sport'),
('Gran Turismo 7', 'PS5', 'Corsa'),
('Gran Turismo 7', 'PS5', 'Simulazione'),
('Spider-Man 2', 'PS5', 'Azione'),
('Spider-Man 2', 'PS5', 'Avventura'),
('Halo Infinite', 'Xbox Series X', 'Sparatutto'),
('Forza Horizon 5', 'Xbox Series X', 'Corsa'),
('Forza Horizon 5', 'Xbox Series X', 'Open World'),
('Minecraft', 'Xbox Series X', 'Avventura'),
('Minecraft', 'Xbox Series X', 'Sandbox'),
('The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 'Azione'),
('The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 'Avventura'),
('Super Mario Odyssey', 'Nintendo Switch', 'Platform'),
('Super Mario Odyssey', 'Nintendo Switch', 'Azione'),
('Animal Crossing: New Horizons', 'Nintendo Switch', 'Simulazione');
