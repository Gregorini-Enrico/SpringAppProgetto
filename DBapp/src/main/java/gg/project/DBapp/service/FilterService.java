package gg.project.DBapp.service;


import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.DBapp.Exception.DateFilterFormatIncorrectException;
import gg.project.DBapp.Filter.DateFilter;
import gg.project.DBapp.Filter.NameFilter;
import gg.project.DBapp.Filter.TypeFilter;
import gg.project.DBapp.model.*;

/**
 * Classe per gestire i filtri, soprattutto per parsare la RequestBody in formato JSON che inserisce l'utente nella richiesta POST
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class FilterService {

	/**
	 * Metodo che decodifica il filtro passato adll'utente nella richiesta per il restore
	 * @param rd  lista dei file cancellati
	 * @param body  RequestBody della richiesta per il restore
	 * @return List<RecordDeleted> lista di file eliminati filtrata
	 * @author Enrico Gregorini
	 * @author Daniele Gjeka
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
		 		return TypeFilter.typeDeleted(rd, filters.get("type"));  //richiamo il metodo che gestisce il filtro sul tipo
		 	else if(filters.containsKey("path"))
		 		return NameFilter.getFileDeleted(rd, filters.get("path"));  //richiamo il metodo che gestisce il filtro sul "nome" del file
		    else if(filters.containsKey("date")){    
		    	Object stringaUtente = filters.get("date"); 
		    	String []dataUtente = stringaUtente.toString().split("=");
		    	//filters.replace("date", filters.get("date"), dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	filters.replace("date", dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	if(dataUtente[0].contains("after"))
		    		return DateFilter.afterDate(rd, filters.get("date"));    //richiamo il metodo che gestisce il filtro sui file modificati dopo la data passata
		    	else if(dataUtente[0].contains("before"))
		    		return DateFilter.beforeDate(rd, filters.get("date"));   //richiamo il metodo che gestisce il filtro sui file modificati prima della data passata
		    	else if(dataUtente[0].contains("between"))
		    		return DateFilter.betweenDate(rd, filters.get("date"));  //richiamo il metodo che gestisce il filtro sui file modificati durante l'intervallo passato
		    	else throw new DateFilterFormatIncorrectException();   //se il metodo sulle date è incorretto genero un'eccezione
		    }
		    else throw new DateFilterFormatIncorrectException();  //se il campo inserito è sbagliato genero un'eccezione
	 }
	 
	
	
	/**
	 * Metodo che decodifica il filtro passato adll'utente nella richiesta per i filtri sui file presenti
	 * @param rd  lista dei file presenti
	 * @param body  RequestBody della richiesta per il restore
	 * @return ArrayList<RecordFile> lista di file filtrata
	 * @author Enrico Gregorini
     * @author Daniele Gjeka
	 */
	@SuppressWarnings("unchecked")
	public static List<RecordFile> filtringFile(List<RecordFile> rd, String body){
		 	HashMap<String,String> filters = null; //Variabile in cui verranno inseriti i filtri del body della richiesta
		 	ObjectMapper obj = new ObjectMapper();
		 	
		 	try {
				filters = obj.readValue(body, HashMap.class); //parsing di body (JSON della richiesta) in filters
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		 	if(filters.containsKey("type")) 
		 		return TypeFilter.type(rd, (String) filters.get("type"));   //richiamo il metodo che gestisce il filtro sul tipo
		 	else if(filters.containsKey("path"))
		 		return NameFilter.getFile(rd, (String) filters.get("path"));   //richiamo il metodo che gestisce il filtro sul "nome" del file
		    else if(filters.containsKey("date")){   
		    	Object stringaUtente = filters.get("date");
		    	String []dataUtente = stringaUtente.toString().split("=");
		    	//filters.replace("date", filters.get("date"), dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	filters.replace("date", dataUtente[1].substring(0, dataUtente[1].length()-1));
		    	if(dataUtente[0].contains("after"))
		    		return DateFilter.afterDateFile(rd, filters.get("date"));   //richiamo il metodo che gestisce il filtro sui file modificati dopo la data passata
		    	else if(dataUtente[0].contains("before"))
		    		return DateFilter.beforeDateFile(rd, filters.get("date"));   //richiamo il metodo che gestisce il filtro sui file modificati prima della data passata
		    	else if(dataUtente[0].contains("between"))
		    		return DateFilter.betweenDateFile(rd, filters.get("date"));  //richiamo il metodo che gestisce il filtro sui file modificati durante l'intervallo passato	 
		    	else throw new DateFilterFormatIncorrectException();             //se il metodo sulle date è incorretto genero un'eccezione
		    }
		    else throw new DateFilterFormatIncorrectException();  //se il campo inserito è sbagliato genero un'eccezione
	 } 
	 
	 
}
