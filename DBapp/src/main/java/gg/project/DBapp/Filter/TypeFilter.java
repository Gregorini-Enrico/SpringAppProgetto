package gg.project.DBapp.Filter;

import java.util.*;

import gg.project.DBapp.Exception.TypeNotFoundException;
import gg.project.DBapp.model.*;

/**
 * Classe per filtrare i file in base al tipo
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class TypeFilter {
              
	      
	      /**
	       * metodo che restituisce tutti i file eliminati del tipo definito dalla variabile type
	       * @param records
	       * @param type
	       * @return
	       */
	      public static List<RecordDeleted> typeDeleted(List<RecordDeleted> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));
	    	  if(records.isEmpty()) throw new TypeNotFoundException();
	    	  return records;
	      }
	      
	      /**
	       * metodo che restituisce tutti i file del tipo definito dalla variabile type
	       * @param records
	       * @param type
	       * @return
	       */
	      public static List<RecordFile> type(List<RecordFile> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));
	    	  if(records.isEmpty()) throw new TypeNotFoundException();
	    	  return records;
	      }
}
