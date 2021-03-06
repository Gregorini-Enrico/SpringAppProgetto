package gg.project.DBapp.Filter;

import java.util.ArrayList;
import java.util.List;

import gg.project.DBapp.Exception.PathNotFoundException;
import gg.project.DBapp.model.*;

/**
 * classe per filtrare attraverso il path per il restore
 * l'utente inserisce il path del file che vuole ripristinare 
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class NameFilter {
	
	/**
	 * metodo che ritorna il file con il path scelto dall'utente
	 * @param records lista di file 
	 * @param path del file selezionato
	 * @return List<RecordDeleted> file scelto dall'utente
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public static List<RecordDeleted> getFileDeleted(List<RecordDeleted> records, String path) {
		ArrayList<RecordDeleted> RecordtoRestore = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd:records) 
			if(rd.getPath_lower().equals(path))
				RecordtoRestore.add(rd);
		if(RecordtoRestore.isEmpty())
			throw new PathNotFoundException();
		return RecordtoRestore;
	}

	
	/**
	 * metodo che ritorna il file con il path scelto dall'utente
	 * @param records lista di file 
	 * @param path del file selezionato
	 * @return List<RecordFile> file scelto dall'utente
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
	 */
	public static List<RecordFile> getFile(List<RecordFile> records, String path) {
		ArrayList<RecordFile> RecordtoRestore = new ArrayList<RecordFile>();
		for(RecordFile rd:records) 
			if(rd.getPath_lower().equals(path))
				RecordtoRestore.add(rd);
		if(RecordtoRestore.isEmpty())
			throw new PathNotFoundException();
		return RecordtoRestore;
	}

}
