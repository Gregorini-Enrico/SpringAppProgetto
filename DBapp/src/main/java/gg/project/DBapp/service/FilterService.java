package gg.project.DBapp.service;

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
		 	HashMap<String,Object> filters = null; //Variabile in cui verranno inseriti i filtri del body della richiesta
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
		    	if(filters.containsValue("after"))
		    		return DateFilter.afterDate(rd, (Date)filters.get("date"));
		    	else if(filters.containsValue("before"))
		    		return DateFilter.beforeDate(rd, (Date)filters.get("date"));
		    	else 
		    		return DateFilter.betweenDate(rd, (Date)filters.get("data inizio"), (Date)filters.get("data fine"));
		    }
	 }
}
