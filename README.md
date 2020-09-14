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

UML CASI D'USO

# 1° passo: UML CLASS DIAGRAM
La prima parte del lavoro è stata quella di pensare a come strutturare il progetto e per fare ciò ci è stato molto utile, l'UML ovvero Unified Modeling Language. In questa prima parte abbiamo iniziato a pensare al funzionamento della nostra applicazione: 
1. acquisizione dei dati dall'API dropbox 
2. parsing di quest'ultimi in liste di Record creati ad hoc per i nostri scopi 
3. analisi delle informazioni accumulate attraverso statistiche e filtri 
4. gestione delle chiamate (GET o POST) effettuate dall'utente

Per fare tutto ciò abbiamo iniziato a pensare ai vari pacchetti che ci sarebbero serviti per suddividere il nostro lavoro al meglio rendendolo il più semplice e chiaro possibile anche per lo sviluppo del codice poi.

QUI CI VA LA FOTO CON TUTTI I PACCHETTI DEL PROGETTO

Ovviamente in seguito per ogni pacchetto abbiamo sviluppato tutte le varie classi che andavano ad implementare il progetto, così facendo abbiamo creato l'UML Class Diagram.


<B> gg.project.DBapp.controller </B> <br>
Package che contiene il controller dell'applicazione, ovvero la classe che gestisce tutte le chiamate GET o POST dell'utente.
<a href="diagramma delle classi di gg.project.DBapp.controller"> </a>

<B> gg.project.DBapp.Storage </B> <br>
Package che gestisce il download dei dati direttamente dalle chiamate alla dropbox API.
<a href="diagramma delle classi di gg.project.DBapp.Storage"> </a>

<B> gg.project.DBapp.model </B> <br>
Package che contiene tutte le classi che servono a parsare i dati ottenuti (Parser, DeletedParser) e a implementare un oggetto, file dropbox, tramite la classe Record e sue figlie.
<A HREF="diagramma delle classi di gg.project.DBapp.model"> </A>

<B> gg.project.DBapp.service </B> <br>
Package che restituisce i dati che andranno poi passati al controller (e quindi in risposta all'utente) e interpreta i filtri in formato JSON, inseriti nelle richieste POST.
<A HREF="diagramma delle classi di gg.project.DBapp.service"> </A>

<B> gg.project.DBapp.Stats </B> <br>
Package che implementa le statistiche sui file presenti nelle varie cartelle dropbox.
<A HREF="diagramma delle classi di gg.project.DBapp.Stats"> </A>

<B> gg.project.DBapp.Filter </B> <br>
Package che implementa i filtri: i filtri si possono applicare direttamente sul nome di file, sul tipo o sulla data di modifica.
<A HREF="diagramma delle classi di gg.project.DBapp.Filter"> </A>

<B> gg.project.DBapp.Restore </B> <br>
Package che implementa l'obiettivo dell'applicazione ovvero quello di effettuare il restore dei file scelti dall'utente in base ai filtri.
<A HREF="diagramma delle classi di gg.project.DBapp.Restore"> </A>

<B> gg.project.DBapp.Exception </B> <br>
Package che gestisce le varie eccezioni che possono essere lanciate all'interno del programma.
<A HREF="diagramma delle classi di gg.project.DBapp.Exception"> </A>


# Funzionamento

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

## Filtri 

Inoltre sono stati implementati anche diversi filtri sia per quanto riguarda i file da ripristinare sia nel caso in cui l'utente ricerchi una tipologia di file specifica.
Vi sono tre diverse tipologie di filtri: 









