package gg.project.DBapp.Filter;

import java.io.FileNotFoundException;
import java.util.*;

import gg.project.DBapp.Exception.TypeNotFoundException;
import gg.project.DBapp.model.RecordDeleted;

public class TypeFilter {
          /**
           * Classe astratta per filtrare i file su cui effettuare il restore in base al tipo
           * @author Enrico Gregorini
           * @author Daniele Gjeka
           */
	      public TypeFilter() {}
	      
	      public List<RecordDeleted> type(List<RecordDeleted> records, String type) {
	    	  records.removeIf(r->(!r.getName().contains(type)));
	    	  if(records.isEmpty()) throw new TypeNotFoundException();
	    	  return records;
	      }
}
