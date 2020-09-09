package gg.project.DBapp.Filter;

import java.util.*;

import gg.project.DBapp.Exception.TypeNotFoundException;
import gg.project.DBapp.model.RecordDeleted;

/**
 * Classe per filtrare i file su cui effettuare il restore in base al tipo
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class TypeFilter {
              
	      
	      /**
	       * metodo che restituisce tutti i file del tipo definito dalla variabile type
	       * @param records
	       * @param type
	       * @return
	       */
	      public static List<RecordDeleted> type(List<RecordDeleted> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));
	    	  if(records.isEmpty()) throw new TypeNotFoundException();
	    	  return records;
	      }
}
