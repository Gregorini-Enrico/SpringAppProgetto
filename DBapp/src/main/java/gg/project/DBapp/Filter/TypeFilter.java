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
	       * @param records lista di tutti i file eliminati 
	       * @param type tipo passato dall'utente
	       * @return records lista filtrata in base al tipo
	       */
	      public static List<RecordDeleted> typeDeleted(List<RecordDeleted> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));  //rimuovo dalla lista se il nome contiene il tipo passato dall'utente
	    	  if(records.isEmpty()) throw new TypeNotFoundException();   //se la lista è vuota lancio l'eccezione
	    	  return records;   //ritorno la lista filtrata in base al tipo
	      }
	      
	      /**
	       * metodo che restituisce tutti i file del tipo definito dalla variabile type
	       * @param records lista di tutti i file presenti 
	       * @param type tipo passato dall'utente
	       * @return records lista filtrata in base al tipo
	       */
	      public static List<RecordFile> type(List<RecordFile> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));   //rimuovo dalla lista se il nome contiene il tipo passato dall'utente
	    	  if(records.isEmpty()) throw new TypeNotFoundException();  //se la lista è vuota lancio l'eccezione
	    	  return records;    //ritorno la lista filtrata in base al tipo
	      }
}
