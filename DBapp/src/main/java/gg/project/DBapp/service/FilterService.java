package gg.project.DBapp.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.DBapp.Filter.DateFilter;
import gg.project.DBapp.Filter.NameFilter;
import gg.project.DBapp.Filter.TypeFilter;
import gg.project.DBapp.Storage.DeletedFiles;
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
		 		return TypeFilter.type(rd, (String) filters.get("type"));
		 	else if(filters.containsKey("path"))
		 		return (List<RecordDeleted>) NameFilter.getFile(rd, (String) filters.get("path"));
		    else
		    	return DateFilter.afterDate(rd, (Date) filters.get("date"));
	 }

	
	
	
	
	/*public static List<RecordDeleted> Filtring(List<RecordDeleted> rd, String body) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException{
		   HashMap<String,Object> filters = null; //Variabile in cui verranno inseriti i filtri del body della richiesta
	       ObjectMapper obj = new ObjectMapper();
	       
	       try {
			filters = obj.readValue(body, HashMap.class); //parsing di body (JSON della richiesta) in filters
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	       
	       Object[] fields = filters.keySet().toArray();  //lista con i campi da filtrare
	       for(int i=0; i<filters.size(); i++) {
	    	   String field = (String) fields[i]; //ritorna il campo su cui effettuare il filtro
	           HashMap<String,Object> method = null; //inizializzo una HashMap in cui inserire il metodo del filtro
	           
	           if(filters.get(field) instanceof HashMap<?,?>) { //verifico se il filtro è corretto
	        	   method = (HashMap<String,Object>) filters.get(field);  // inserisco il metodo del filtro (es "type" : "pdf")
	        	   //if(method.isEmpty()) throw new E_IncorrectFilterMethod();//lancio un'eccezione se il metodo del filtro è vuoto
	           } //else throw new E_IncorrectFilterMethod(); //lancio un'eccezione se il metodo del filtro non è corretto
	           
	         try {
					Class<?> typeClass;
					typeClass = Class.forName("gg.project.DBapp.Filter"+field); 
					Constructor<?> constructor = typeClass.getConstructor(); 
					Object typeFilter = constructor.newInstance(); //inizializzo la classe a partire dal campo su cui effettuare il filtro (package Filter)
					rd = apply(method, typeFilter, rd ); //filtro la lista, passando il metodo del filtro, la classe su cui chiamare tale metodo e la lista da filtrare
				
	         } catch (SecurityException e) {
					
					e.printStackTrace();
			 } catch (InstantiationException e) {
					
					e.printStackTrace();
			 } catch (IllegalAccessException e) {
					
					e.printStackTrace();
				   } 
		     }
	             return rd; //lista filtrata
	   }*/
	
	
	   /**
	    * Metodo per applicare il filtro passato
	    * 
	    * @param hmBody  metodo da eseguire
	    * @param typeFilter  attributo su cui eseguire il filtro
	    * @param rd  lista dei file cancellati da filtrare
	    * @return  lista si file cancellati filtrata
	    * @throws NoSuchMethodException
	    * @throws InvocationTargetException
	    */
       /*private static  List<RecordDeleted> apply(HashMap<String, Object> hmBody, Object typeFilter, List<RecordDeleted> rd) throws NoSuchMethodException, InvocationTargetException {
		
		      String s = (String) hmBody.keySet().toArray()[0]; //nome del metodo da eseguire
		
		      Method method = null;
		      try {
			      method = typeFilter.getClass().getMethod(s,List.class, hmBody.get(s).getClass()); 
			      rd= (List<RecordDeleted>) method.invoke(typeFilter, rd, hmBody.get(s)); //invoco il metodo e filtro la lista
			
		      }  catch (SecurityException e) {
			
			     e.printStackTrace();
		      } catch (IllegalAccessException e) {
			
			     e.printStackTrace();
		      } catch (IllegalArgumentException e) {
			
			    e.printStackTrace();
		      } 
		
		      return rd;
	   }*/
}
