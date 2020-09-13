package gg.project.DBapp.service;

import java.util.*;

import gg.project.DBapp.Storage.*;
import gg.project.DBapp.model.Metadata;
import gg.project.DBapp.model.*;
import gg.project.DBapp.Restore.*;

/**
 * Servizio principale che istanzio nel controller per gestire le varie chiamate dell'utente
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
@org.springframework.stereotype.Service
public class PrincipalService {

	/**
	 * Metodo per ritornare la lista di tutti i record(sia file che cartelle)
	 * @return lista di Record
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public ArrayList<Record> getRecords(){
		return Storage.download();  
	}
	
	/**
	 * Metodo per ritornare la lista di tutti i file eliminati 
	 * @return lista di RecordDeleted
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public ArrayList<RecordDeleted> getDeletedFiles(){
		return DeletedFiles.downloadDeletedFiles();
	}
	
	/**
	 * Metodo per ritornare i metadata
	 * @return HashMap<String,String> dei metadata con una breve descrizione dei vari campi
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public HashMap<String,String> getMetadata(){
		return Metadata.getFileMetadata();
	}
	
	/**
	 * Metodo che effettua il restore dei file scelti
	 * @param body JSON passato dall'utente per filtrare i dati
	 * @return true se il restore è stato effettuato con successo, false in caso contrario
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public boolean RestoreFile(String body) {
		if(Restore.restore(FilterService.filtring(DeletedFiles.downloadDeletedFiles(), body))) return true;
		else return false;
	}
	
	/**
	 * Metodo che restituisce i file eliminati filtrati in base alle scelte dell'utente
	 * @param filter JSON passato dall'utente per filtrare i dati
	 * @return lista di RecordDeleted
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public List<RecordDeleted> getFilteredFileDeleted(String filter){
		return FilterService.filtring(DeletedFiles.downloadDeletedFiles(), filter);
	}
	
	/**
	 * Metodo che restituisce i file eliminati presenti in base alle scelte dell'utente
	 * @param filter JSON passato dall'utente per filtrare i dati
	 * @return lista di RecordFile
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public List<RecordFile> getFilteredFile(String filter){
		return FilterService.filtringFile(Storage.downloadFile(), filter);
	}
	
	
}
 