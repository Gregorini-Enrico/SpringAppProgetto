# SpringAppProgetto
Questo è il repository che contiene il progetto svolto da Enrico Gregorini e Daniele Gjeka per l'esame di Programmazione A Oggetti per il secondo anno di Ingegneria Informatica e dell'Automazione.

# Introduzione
Il progetto consiste in una SpringBoot application, creata nell'ambiente java, usando l'IDE eclipse, che permette di modellare e acquisire dati da una API esterna. Il nostro macrotema infatti essendo la Dropbox API, abbiamo dovuto attraverso quest'ultima, autenticarci così da ottenere indietro dati corrispondenti ai file presenti nell'account dropbox e nella cartella associata. Il nostro obiettivo era quello di: a partire dalla lista dei file cancellati in una cartella Dropbox, di effettuare il restore degli stessi dando la possibilità all'utente di scegliere il file, il tipo di file o gli intervalli temporali rispetto alla data di cancellazione dei file di cui effettuare il restore. 

Tutto questo è possibile grazie a delle chiamate specifiche fatte all'API dropbox quali: 
1) /files/list_folder: per ricevere tutti i file/cartelle presenti nella cartella dropbox presa in riferimento attraverso il token d'accesso;
2) /files/list_revisions: grazie a questa chiamata siamo riusciti ad ottenere più campi che descrivono in maniera più completa un file che era stato precedentemente cancellato;
3) /files/restore: permette di ripristinare il file scelto se questo è stato cancellato entro e non oltre gli ultimi 30 giorni.

Oltre questo la nostra applicazione permette di analizzare i dati contententi la cartella dropbox attraverso statistiche (sulla dimensione e tipo di file) e filtri(sulla dimensione, tipo e date di modifica dei file stessi) che spiegheremo in dettaglio più avanti.

Per mostrare tutti questi passi al meglio, mostriamo il diagramma UML dei casi d'suo così da introdurre nella maniera più semplice possibile al funzionamento del progetto: 

UML CASI D'USO

# 1° passo: UML CLASS DIAGRAM
La prima parte del lavoro è stata quella di pensare a come strutturare il progetto e per fare ciò ci è stato molto utile, l'UML ovvero Unified Modeling Language. In questa prima parte abbiamo iniziato a pensare al funzionamento della nostra applicazione: 
1)acquisizione dei dati dall'API dropbox 
2)parsing di quest'ultimi in liste di Record creati ad hoc per i nostri scopi 
3)analisi delle informazioni accumulate attraverso statistiche e filtri 
4)gestione delle chiamate (GET o POST) effettuate dall'utente

Per fare tutto ciò abbiamo iniziato a pensare ai vari pacchetti che ci sarebbero serviti per suddividere il nostro lavoro al meglio rendendolo il più semplice e chiaro possibile anche per lo sviluppo del codice poi.

QUI CI VA LA FOTO CON TUTTI I PACCHETTI DEL PROGETTO

Ovviamente in seguito per ogni pacchetto abbiamo sviluppato tutte le varie classi che andavano ad implementare il progetto, così facendo abbiamo creato l'UML Class Diagram.

ORA PER OGNI PACCHETTO MOSTRIAMO TUTTE LE CLASSI CHE COMPONGONO IL PROGETTO

gg.project.DBapp.controller
Package che contiene il controller dell'applicazione, ovvero la classe che gestisce tutte le chiamate GET o POST dell'utente.
<a href> diagramma delle classi di gg.project.DBapp.controller </a href>

gg.project.DBapp.Storage
Package che gestisce il download dei dati direttamente dalle chiamate alla dropbox API.
<a href> diagramma delle classi di gg.project.DBapp.Storage </a href>

gg.project.DBapp.model
Package che contiene tutte le classi che servono a parsare i dati ottenuti (Parser, DeletedParser) e a implementare un oggetto, file dropbox, tramite la classe Record e sue figlie.
<a href> diagramma delle classi di gg.project.DBapp.model </a href>

gg.project.DBapp.service
Package che restituisce i dati che andranno poi passati al controller (e quindi in risposta all'utente) e interpreta i filtri in formato JSON, inseriti nelle richieste POST.
<a href> diagramma delle classi di gg.project.DBapp.model </a href>

gg.project.DBapp.Stats
Package che implementa le statistiche sui file presenti nelle varie cartelle dropbox.
<a href> diagramma delle classi di gg.project.DBapp.Stats </a href>

gg.project.DBapp.Filter
Package che implementa i filtri: i filtri si possono applicare direttamente sul nome di file, sul tipo o sulla data di modifica.
<a href> diagramma delle classi di gg.project.DBapp.Stats </a href>

gg.project.DBapp.Restore
Package che implementa l'obiettivo dell'applicazione ovvero quello di effettuare il restore dei file scelti dall'utente in base ai filtri.ù
<a href> diagramma delle classi di gg.project.DBapp.Stats </a href>

gg.project.DBapp.Exception
Package che gestisce le varie eccezioni che possono essere lanciate all'interno del programma.
<a href> diagramma delle classi di gg.project.DBapp.Exception </a href>












