# SpringAppProgetto
Questo è il repository che contiene il progetto svolto da Enrico Gregorini e Daniele Gjeka per l'esame di Programmazione A Oggetti per il secondo anno di Ingegneria Informatica e dell'Automazione

# Introduzione
Il progetto consiste in una SpringBoot application, creata nell'ambiente java, usando l'IDE eclipse, che permette di modellare e acquisire dati da una API esterna. Il nostro macrotema infatti essendo la Dropbox API, abbiamo dovuto attraverso quest'ultima, autenticarci così da ottenere indietro dati corrispondenti ai file presenti nell'account dropbox e nella cartella associata. Il nostro obiettivo era quello di: a partire dalla lista dei file cancellati in una cartella Dropbox, di effettuare il restore degli stessi dando la possibilità all'utente di scegliere il file, il tipo di file o gli intervalli temporali rispetto alla data di cancellazione dei file di cui effettuare il restore. 

Tutto questo è possibile grazie a delle chiamate specifiche fatte all'API dropbox quali: 
* /files/list_folder: per ricevere tutti i file/cartelle presenti nella cartella dropbox presa in riferimento attraverso il token d'accesso;
* /files/list_revisions: grazie a questa chiamata siamo riusciti ad ottenere più campi che descrivono in maniera più completa un file che era stato precedentemente cancellato;
* /files/restore: permette di ripristinare il file scelto se questo è stato cancellato entro e non oltre gli ultimi 30 giorni.

Oltre questo la nostra applicazione permette di analizzare i dati contententi la cartella dropbox attraverso statistiche (sulla dimensione e tipo di file) e filtri(sulla dimensione, tipo e date di modifica dei file stessi) che spiegheremo in dettaglio più avanti.

Per mostrare tutti questi passi al meglio, mostriamo il diagramma UML dei casi d'suo così da introdurre nella maniera più semplice possibile al funzionamento del progetto: 

![Immagine del diagramma dei casi d'uso](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Use%20Case%20Diagram.PNG)

# UML CLASS DIAGRAM
La prima parte del lavoro è stata quella di pensare a come strutturare il progetto e per fare ciò ci è stato molto utile, l'UML ovvero Unified Modeling Language. In questa prima parte abbiamo iniziato a pensare al funzionamento della nostra applicazione: 
1. acquisizione dei dati dall'API dropbox 
2. parsing di quest'ultimi in liste di Record creati ad hoc per i nostri scopi 
3. analisi delle informazioni accumulate attraverso statistiche e filtri 
4. gestione delle chiamate (GET o POST) effettuate dall'utente

Per fare tutto ciò abbiamo iniziato a pensare ai vari pacchetti che ci sarebbero serviti per suddividere il nostro lavoro al meglio rendendolo il più semplice e chiaro possibile anche per lo sviluppo del codice poi.

![Immagine del diagramma dei pacchetti](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Package.PNG)

Ovviamente in seguito per ogni pacchetto abbiamo sviluppato tutte le varie classi che andavano ad implementare il progetto, così facendo abbiamo creato l'UML Class Diagram.


### gg.project.DBapp.controller  <br> <br> 
Package che contiene il controller dell'applicazione, ovvero la classe che gestisce tutte le chiamate GET o POST dell'utente.
![controller class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Controller.PNG)

### gg.project.DBapp.Storage  <br> <br>
Package che gestisce il download dei dati direttamente dalle chiamate alla dropbox API.
![Storage class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/StorageUpdate.PNG)

### gg.project.DBapp.model  <br> <br>
Package che contiene tutte le classi che servono a parsare i dati ottenuti (Parser, DeletedParser) e a implementare un oggetto, file dropbox, tramite la classe Record e sue figlie.
![Model class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/ModelUpdate.PNG)

### gg.project.DBapp.service  <br> <br>
Package che restituisce i dati che andranno poi passati al controller (e quindi in risposta all'utente) e interpreta i filtri in formato JSON, inseriti nelle richieste POST.

![Service class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Service.PNG)

### gg.project.DBapp.Stats <br> <br>
Package che implementa le statistiche sui file presenti nelle varie cartelle dropbox. <br>
![Stats class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Statistics.PNG)

### gg.project.DBapp.Filter <br> <br>
Package che implementa i filtri: i filtri si possono applicare direttamente sul nome di file, sul tipo o sulla data di modifica.
![Filter class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Filter.PNG)

### gg.project.DBapp.Restore <br> <br>
Package che implementa l'obiettivo dell'applicazione ovvero quello di effettuare il restore dei file scelti dall'utente in base ai filtri.
![Restore class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Restore.PNG)

### gg.project.DBapp.Exception <br> <br>
Package che gestisce le varie eccezioni che possono essere lanciate all'interno del programma.
![Exception class diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Class%20Diagram/Exception.PNG)


# Funzionamento

Mostriamo ora come utilizzare l'applicazione attraverso le sue chiamate e come quest'ultime funzionano.

## Chiamate 

| ROTTA | METODO | DESCRIZIONE |
|-|-|-|
| /files | GET | per ottenere la lista di tutti i file presenti (sia file o file eliminati che cartelle) |
| /files/deleted | GET | per ottenere solo la lista dei file eliminati con più dettagli |
| /metadata | GET | per ottenere i metadata ovvero i campi che utilizzerà la nostra applicazione<br>per produrre statistiche e applicare filtri |
| /statistics | GET | per ricevere statistiche sui dati |
| /filter | POST | per filtrare la ricerca attraverso vari campi |
| /restore | POST | per ripristinare file scelti dall'utente attraverso filtri |

Di seguito mostriamo il diagramma delle sequenze di una delle prime chiamate, ovvero quella che restituisce la lista di tutti i file presenti nella cartella principale dropbox, quindi senza distinzioni di tipo (sia file, file eliminati che cartelle).

![getFiles use case diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Sequence%20Diagram/file.PNG)

## Statistiche 

L'applicazione analizza i file presenti in dropbox e effettua statistiche su di essi. Le statistiche vengono effettuate attraverso richieste GET a diverse rotte che a questo indirizzo localhost:8080/statistics/ di seguito poi la rotta che si preferisce. <br>
Ci sono dei parametri da immettere per personalizzare quest'ultime, quali:
* file: definisce se la statistica che si vuole effettuare, deve essere fatta sui file presenti inserendo <B> "file" </B> oppure inserendo <B> "deleted" </B> essa verrà eseguita sui file eliminati
* subfolder: se inserita la statistica viene effettuata solo per i file presenti nella sottocartella, in caso contrario i file vengono presi direttamente dalla cartella principale 

| ROTTA | PARAMS | DESCRIZIONE |
|-|-|-|
| /statistics/media | file, subfolder | restituisce la dimensione media dei file in MB |
| /statistics/max | file, subfolder | restituisce il file con la dimensione maggiore |
| /statistics/min | file, subfolder | restituisce il file con la dimensione minore |
| /statistics/type | file , subfolder | restituisce una tabella che mostra quanti file <br>sono presenti nella cartella scelta per ogni tipo |

![getStats use case diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Sequence%20Diagram/statis%20type.PNG)

## Filtri 

Inoltre sono stati implementati anche diversi filtri sia per quanto riguarda i file da ripristinare sia nel caso in cui l'utente ricerchi una tipologia di file specifica.
Questa chiamata è gestita alla rotta <B> localhost:8080/filter </B> tramite metodo <B> POST </B>. 
Vi sono tre diverse tipologie di filtri in base al campo che si sceglie: 
| CAMPO | PARAMS | BODY | DESCRIZIONE |
|-|-|-|-|
| path | file | "path": "<U>percorso del file</U>" | restituisce il file al percorso desiderato |
| type | file | "type": "<U> tipo del file </U>" | restituisce tutti i file del tipo inserito |
| date | file | "date": {"after" : "<U> data </U>"}<br>  "date": {"before" : "<U> data </U>"}<br>  "date": {"between" : <U>["data1", "data2"]</U> | restituisce i file modificati dopo della data inserita <br>restituisce i file modificati prima della data inserita <br>restituisce i file modificati durante l'intervallo inserito |

Per rendere più chiare queste chiamate mostriamo il diagramma delle sequenze, nel caso in cui l'utente scelga di filtrare il campo type (mostriamo questo esempio perchè in sostanza quello che fa l'applicazione è molto simile in tutti i casi, cambiando semplicemente l'ultima classe che gestisce il filtro del proprio campo) <br>
![getStats use case diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Sequence%20Diagram/filter%20seq.PNG)

## Restore

Come detto già in precedenza l'obiettivo della nostra applicazione è quello di ripristinare file scelti dall'utente in base al file, al tipo e alla data di cancellazione di quest'ultimo. Il ragionamento è molto simile a quello fatto per i filtri infatti essi sono gli stessi anche per quanto riguarda il restore, ovvero si va a scegliere il file, o i file, tramite la body (esattamente come abbiamo mostrato nella sezione sui filtri) della richiesta <B> POST </B> alla rotta <B> localhost:8080/restore </B>. 

Di seguito mostriamo il diagramma delle sequenze per la chiamata che effettua il restore dei file: 
![getFilter use case diagram](https://github.com/Gregorini-Enrico/SpringAppProgetto/blob/new_master/Diag%20UML/Sequence%20Diagram/restore%20seq.PNG)

# Componenti 

#### Enrico Gregorini
#### Daniele Gjeka

Il lavoro mostrato è stato sviluppato per la maggior parte insieme, sia di persona che in via telematica. Ci sono state parti del progetto su cui ha lavorato maggiormente 
uno e altre prese in carico dall'altro componente, infatti: 
* Daniele Gjeka ha elaborato tutti i diagrammi UML (tramite UML designer), commentato il codice e generato la documentazione javadoc.
* Enrico Gregorini ha sviluppato la parte delle statistiche e filtri.
Il resto del lavoro è stato svolto insieme così da rimanere sempre aggiornati passo passo sugli avanzamenti del progetto. 







