# Unigame - Progetto TSW 2024/25

## Contesto del Progetto:
Questo progetto è stato realizzato nell'ambito del corso universitario **"Tecnologie e Software per il Web"** presso l'**Università degli Studi di Salerno**. 
Il lavoro nasce come applicazione pratica delle nozioni teoriche e degli esercizi proposti durante le lezioni, con l'obiettivo di consolidare le competenze acquisite nello sviluppo di applicazioni web dinamiche e strutturate.
### Docenti:
- Gennaro Costagliola
- Mattia De Rosa
### Studenti:
- Aniello Del Prete - *Mat.* 0515119517
- Gennaro Albano - *Mat.* 0512119547

# Sommario

1. Obiettivo del Progetto
2. Analisi Siti Esistenti
	1. Instant Gaming
	2. G2A
3. Funzionalità del Sito
4. Diagramma Navigazionale
5. Layout
6. Scelta dei Colori
7. La Base dei Dati


---

# 1. Obiettivo del Progetto

## Il sito nasce con l’obiettivo di offrire un’alternativa "economicamente" per l’acquisto di videogiochi digitali. 
### Si rivolge a un pubblico ampio di videogiocatori: dai giocatori occasionali agli appassionati e i più esperti.

Il bisogno principale che intende soddisfare è quello di trovare videogiochi ottenibili tramite una **chiave di attivazione** a prezzi competitivi, rifornendo quindi il sito di giochi popolari ed *"ultima generazione"* garantendo allo stesso tempo affidabilità, rapidità nella consegna delle key digitali e un’interfaccia semplice e intuitiva.

Un disclaimer da fare è che è comunque un sito progettato per una presentazione universitaria, quindi in chiave professionale non è *ancora* funzionante.

# 2. Analisi Siti Esistenti

In questo capitolo verranno collegati siti e immagini a cui ci siamo ispirati per la creazione di questo progetto, verranno allegate le immagini ed il nome della pagina delle pagine a cui ci siamo ispirati.

### 2.1 - [Instant Gaming](https://www.instant-gaming.com/en/)

### Layout:
- Home
  
![image](https://github.com/user-attachments/assets/28d24b6c-ba68-43cc-a7f3-3bf4c8623444)

- Login & Register
  
![image](https://github.com/user-attachments/assets/d444a59e-6f3e-4335-be48-225dc56d46a9)
![image](https://github.com/user-attachments/assets/13161a4d-f9cc-472f-9924-7145c42fe4c9)

### Funzionalità principali:
1. Ricerca e filtri avanzati: permette agli utenti di trovare rapidamente i giochi per piattaforma, genere o fascia di prezzo.
2. Prezzi scontati rispetto ai listini ufficiali, con indicazione del risparmio in percentuale.
3. Sistema di carrello.
4. Recensioni utenti integrate per valutare l’affidabilità e la qualità del prodotto.
5. Wishlist e notifiche: possibilità di aggiungere giochi preferiti e ricevere alert sugli sconti.

### 2.1 - [G2A](https://www.g2a.com/it/)

### Layout
- Home
  
![image](https://github.com/user-attachments/assets/4a1e2df5-887f-419b-87fa-11a9f026d3d7)

# 3. Funzionalità del Sito

### 3.1 - Funzionalità per l’Utente non Registrato:
- #### 3.1.1 - Visualizzazione del catalogo prodotti
	I videogiochi sono suddivisi per *genere*, *prezzo* e *piattaforme*, quali:
	- **PC**
	- **PS5**
	- **Xbox**
	- **Switch**
- #### 3.1.2 - Ricerca e filtri avanzati
	- Sul sito, *come su Instant Gaming*, al click della barra della ricerca o della lente di ingrandimento si accede ad una ricerca tramite nome, piattaforma, genere ed anno di uscita, permettendo di sorprendere il cliente con giochi nuovi di un genere specifico, oppure la ricerca per nome se si cerca un gioco specifico.
- #### 3.1.3 - Scheda prodotto
	- Ogni gioco ha una pagina dedicata con descrizione, immagini, se presente, un prezzo scontato, recensioni e specifiche tecniche minime e raccomandate.
- #### 3.1.4 - Aggiunta, Visualizza e Modifica i contenuti del carrello
	- L’utente può aggiungere uno o più giochi al carrello.
	- Controllo sui prodotti inseriti
	- Possibilità di rimuovere articoli
	- Modificare quantità
	- Applicare codici sconto. 
### 3.2 - Aggiunte alle funzionalità base per l’Utente Registrato

L’utente non registrato non potrà fare acquisti a meno che non effettua la Registrazione o il Login al proprio account.
In più se la sessione resta ospite, quindi non registrata, al prossimo riaccesso al sito, quest'ultimo non manterrà in memoria i prodotti inseriti precedentemente nel carrello.
#### 3.2.1 - Checkout e Consegna
- Procedura di acquisto semplice e sicura, con più opzioni di pagamento (carta, PayPal, ecc).
- Consegna immediata della key dopo l’acquisto, direttamente via email o sul profilo utente.
#### 3.3.2 - Registrazione e Login dell'Utente al sito
- L'utente può Registrarsi imettendo:
	- Nome
	- Cognome
	- Email
	- Username
	- Password
- In alternativa, effettuare il Login imettendo:
	- Username
	- Password
#### 3.2.2 - Gestione del Profilo Utente
- Possibilità di visualizzare ordini passati
- Controllo di giochi in wishlist
- Controllo e modifica di dati personali e foto profilo.
#### 3.2.3 - Pubblicare Recensioni e Valutazioni sul singolo gioco 
- Gli utenti possono lasciare opinioni sui giochi acquistati.
- Iscrizione alla Newsletter per tenere aggiornati gli utenti su promozioni, sconti e nuove uscire.
### 3.3 - Funzionalità per l’amministratore:
- Gestione del catalogo: possibilità di aggiungere, modificare o eliminare giochi dal database, aggiornare prezzi e sconti.
- Gestione degli ordini: visualizzazione e monitoraggio di tutti gli ordini effettuati dagli utenti.
- Gestione utenti: visualizzazione profili registrati e gestione account.
- Gestione contenuti: possibilità di aggiornare testi, immagini, news e pagine informative.
- Gestione promozioni e codici sconto: creazione e gestione di campagne promozionali.
- Supporto clienti: sistema di assistenza per rispondere a richieste o problemi degli utenti.

# 4. Diagramma Navigazionale

Questo è il chart navigazionale dei collegamenti tra le varie pagine web:

![image](https://github.com/user-attachments/assets/54dce3f3-4498-4821-beb3-38e1b3e2fc78)

# 5. Layout

Durante il processo di creazione del sito, una prima bozza è stata la seguente:

![image](https://github.com/user-attachments/assets/b8a04397-d15b-4076-969e-372365cff8e2)

Abbiamo continuato con prima l'implementazione servlet, poi il layout del sito;
Una prima versione del sito era questa:
https://github.com/user-attachments/assets/d721572b-db32-433b-b3b2-9ba8b17d949d

## 6. Scelta dei Colori
Abbiamo pensato ad una palette di colori che ricordasse una tonalità sicura e veloce
![image](https://github.com/user-attachments/assets/6cb1f5ba-3c93-40db-b8e8-5f43dbad680a)

#f3eef9           #2f1749       #522b87      #9b71ce      #c8b1e4

# 7. La Base dei Dati

# !!!TO-DO
