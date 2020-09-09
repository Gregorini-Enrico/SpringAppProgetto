package gg.project.DBapp.Filter;

import java.util.ArrayList;
import java.util.List;

import gg.project.DBapp.Storage.DeletedFiles;
import gg.project.DBapp.model.RecordDeleted;

/**
 * classe per filtrare attraverso il path per il restore
 * l'utente inserisce il path del file che vuole ripristinare 
 */
public class NameFilter {
	
	/**
	 * metodo che ritorna il file con il path scelto dall'utente
	 * @param path del file selezionato
	 * @return RecordToRestore file scelto dall'utente
	 */
	public static List<RecordDeleted> getFile(List<RecordDeleted> records, String path) {
		ArrayList<RecordDeleted> RecordtoRestore = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd:records) 
			if(rd.getName().contains(path))
				RecordtoRestore.add(rd);
		return RecordtoRestore;
	}

}