package gg.project.DBapp.service;

import static org.hamcrest.CoreMatchers.containsString;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.DBapp.Filter.DateFilter;
import gg.project.DBapp.Filter.NameFilter;
import gg.project.DBapp.Filter.TypeFilter;
import gg.project.DBapp.model.RecordDeleted;

public class FilterService {

	/**
	 * Metodo che decodifica il filtro passato adll'utente nella richiesta per il restore
	 * 
	 * @param rd  lista dei file cancellati
	 * @param body  RequestBody della richiesta per il restore
	 * @return  lista di file filtrata
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	 @SuppressWarnings("unchecked")
	public static List<RecordDeleted> filtring(List<RecordDeleted> rd, String body){
		 	HashMap<String,String> filters = null; //Variabile in cui verranno inseriti i filtri del body della richiesta
		 	ObjectMapper obj = new ObjectMapper();
		 	
		 	try {
				filters = obj.readValue(body, HashMap.class); //parsing di body (JSON della richiesta) in filters
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		 	if(filters.containsKey("type")) 
		 		return TypeFilter.typeDeleted(rd, (String) filters.get("type"));
		 	else if(filters.containsKey("path"))
		 		return (List<RecordDeleted>) NameFilter.getFile(rd, (String) filters.get("path"));
		    else {  //DA MODIFICARE 
		    	Object stringaUtente = filters.get("date");
		    	String []dataUtente = stringaUtente.toString().split("=");
		    	//filters.replace("date", filters.get("date"), dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	filters.replace("date", dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	if(dataUtente[0].contains("after"))
		    		return DateFilter.afterDate(rd, filters.get("date"));
		    	else if(dataUtente[0].contains("before"))
		    		return DateFilter.beforeDate(rd, filters.get("date"));
		    	else 
		    		return DateFilter.betweenDate(rd, filters.get("data inizio"), filters.get("data fine"));
		    	
		    	
		      /*HashMap<String,Object> values =  filters.values();
		    	if(values.containsKey("after"))
		    		return DateFilter.afterDate(rd, (Date)values.get("after"));
		    	else if(values.containsKey("before"))
		    		return DateFilter.beforeDate(rd, (Date)values.get("before"));
		    	else 
		    		return DateFilter.betweenDate(rd, (Date)values.get("data inizio"), (Date)values.get("data fine"));
		    	*/
		    	
		    	
		    }
	 }
}
