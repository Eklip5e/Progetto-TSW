-- Seleziona tutti i videogiochi per la piattaforma PS5 e che sono usciti dopo il 2020 o hanno un prezzo maggiore di 60 --
select *
from Videogioco
where piattaforma = 'PS5' and (anno_uscita > 2020 or prezzo > 60)
order by prezzo desc;

select c.data, c.v_titolo, c.v_piattaforma, c.quantita, c.prezzo_totale
from Comprare c join Reparto r on c.reparto = r.codice_reparto
where r.n_nome = 'NextLevel Napoli';

select sum(stipendio) as somma_stipendi
from Impiegato;

select n_nome, sum(stipendio) as somma_stipendi
from Impiegato
group by n_nome;

select r.n_nome, sum(c.prezzo_totale) as totale_guadagno
from Comprare c join Reparto r on c.reparto = r.codice_reparto
group by r.n_nome
having sum(c.prezzo_totale) > 500;


create view calcolo_guadagno_totale as
select r.n_nome, sum(c.prezzo_totale) as guadagno_totale
from Reparto r join Comprare c on r.codice_reparto = c.reparto
group by r.n_nome;

select *
from calcolo_guadagno_totale
where guadagno_totale = (select max(guadagno_totale)
						 from calcolo_guadagno_totale);
                                    
select *
from Videogioco
where piattaforma = 'PS5'
union
select *
from Videogioco
where Piattaforma = 'Xbox Series X';

select v.titolo, v.piattaforma
from Videogioco v
where not exists (
					select *
					from Cliente cl
					where not exists (
										select *
										from Comprare c
										where c.v_titolo = v.titolo and c.v_piattaforma = v.piattaforma and c.cliente = cl.cf));
	


