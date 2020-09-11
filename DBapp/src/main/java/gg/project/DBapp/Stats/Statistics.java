package gg.project.DBapp.Stats;


import java.util.*;
import java.util.regex.Pattern;

import gg.project.DBapp.model.*;

public class Statistics {
		
	/**
	 * Metodo per calcolare la media delle dimensioni dei file presenti nella cartella inserita dall'utente
	 * se subfolder è vuota viene presa la cartella principale
	 * @param records lista dei Record da analizzare
	 * @param subfolder sottocartella scelta dall'utente
	 * return float media della dimensione dei file
	 */
	public static double media(List<RecordFile> records, String subfolder) {
		double s = 0;
		for(RecordFile f:records) {
			if(!subfolder.isEmpty()) { //controllo se la sottocartella è vuota o no
				if(f.getPath_lower().contains(subfolder)) {  //se non è vuota controllo se il file sia dentro la cartella
			         double KB = f.getSize()/1024; //calcolo la dimensione in KB
			         s += KB;
				}
			}
			else{   //se subfolder è vuota, prendo tutti i file della lista
				    if(f.getPath_lower().contains(subfolder)) {
		               double KB = f.getSize()/1024; //calcolo la dimensione in KB
		               s += KB;
			        }  
			}
		}
		return s/records.size(); //ritorna la media 
	}
	
	/**
	 * Metodo per calcolare la media delle dimensioni dei file presenti nella cartella inserita dall'utente
	 * se subfolder è vuota viene presa la cartella principale
	 * @param records lista dei Record da analizzare
	 * @param subfolder sottocartella scelta dall'utente
	 * return float media della dimensione dei file
	 */
	public static double mediaDeletedFile(List<RecordDeleted> Drecords, String subfolder) {
		double s = 0;
		for(RecordDeleted f:Drecords){
			if(!subfolder.isEmpty()) {  //controllo se la sottocartella è vuota o no
				if(f.getPath_lower().contains(subfolder)) {    //se non è vuota controllo se il file sia dentro la cartella
			         double KB = f.getSize()/1024; //calcolo la dimensione in KB
			         s += KB;
				}
			}
			else{    //se subfolder è vuota, prendo tutti i file della lista
				    if(f.getPath_lower().contains(subfolder)) {
		               double KB = f.getSize()/1024; //calcolo la dimensione in KB
		               s += KB;
			        }  
			}
		}
		return s/Drecords.size(); //ritorna la media in KB
	}
	
	public static RecordFile maxDimFile(List<RecordFile> records, String subfolder) {
		RecordFile max = new RecordFile();
		for(RecordFile r:records){ 
			if(!subfolder.isEmpty()) {   //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()>max.getSize())    max = r; //calcolo la dimensione in B
				}
				else { if(r.getSize()>max.getSize())    max = r;}
			}
		return max; //ritorno il file con dimensione massima
	}
	
	public static RecordDeleted maxDimFileDeleted(List<RecordDeleted> Drecords, String subfolder) {
		RecordDeleted max = new RecordDeleted();
		for(RecordDeleted r:Drecords){ 
			if(!subfolder.isEmpty()) {   //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))  //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()>max.getSize())    max = r; //calcolo la dimensione in B
				}
				else { if(r.getSize()>max.getSize())    max = r;}
			}
		return max; //ritorno il file con dimensione massima in KB
	}
	
	public static RecordFile minDimFile(List<RecordFile> records, String subfolder) {
		RecordFile max = records.get(0);
		for(RecordFile r:records){ 
			if(!subfolder.isEmpty()) {    //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()<max.getSize())    max = r; //calcolo la dimensione in B
			}
		    else { if(r.getSize()<max.getSize())    max = r;}    //se subfolder è vuota, prendo tutti i file della lista
		}
		return max; //ritorno il file con dimensione massima
	}
	
	public static RecordDeleted minDimFileDeleted(List<RecordDeleted> Drecords, String subfolder) {
		RecordDeleted max = Drecords.get(0);
		for(RecordDeleted r:Drecords) { 
			if(!subfolder.isEmpty()) {    //controllo se la sottocartella è vuota o no
		        if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
			        if(r.getSize()<max.getSize())    max = r; //calcolo la dimensione in B
		    }
			else { if(r.getSize()<max.getSize())    max = r;}      //se subfolder è vuota, prendo tutti i file della lista
		}
		return max; //ritorno il file con dimensione massima in B
	}
	
	
	/**
	 * Metodo che restituisce il numero di file presenti per ogni tipo presente nella cartella subfolder inserita dall'utente
	 * se subfolder è vuota prende la cartella principale
	 * @param records tutti i file presenti
	 * @param subfolder cartella dove effettuare la statistica
	 * @return type HashMap<String, Integer> tabella che rappresenta quanti file sono presenti per ogni tipo nella cartella scelta
	 */
	public static HashMap<String, Integer> getFileType(List<RecordFile> records, String subfolder){
		HashMap<String, Integer> type = new HashMap<String, Integer>();
		for(Record r:records) {
			Integer i=0;
			String[] format = r.getName().split(Pattern.quote("."));
			if(!subfolder.isEmpty()) {   //controllo se la sottocartella è vuota o no
			   if(r.getPath_lower().contains(subfolder)) {    //se non è vuota controllo se il file sia dentro la cartella
			      if(type.containsKey(format[format.length-1])) {  //controllo se in type è già presente un file di quel formato.. format[format.length-1] sarebbe il formato del file preso in considerazione in questa iterazione del for-each
				     i = type.get(format[format.length-1])+1;  //se è già presente incremento di uno il contatore di quel formato specifico, contatore ovvero la variabile i
				     type.put(format[format.length-1], i);     //inserisco nell'HashMap type il nuovo valore di i associato al suo formato
			      }
			      else type.put(format[format.length-1], ++i);   //se non è presente quel formato inserisco in type il formato con il suo contatore i che lo vado a preincrementare(per settarlo a 1 essendo prima a 0 perchè non era presente quel formato) 
			   }
			}
			else {    //se subfolder è vuota, prendo tutti i file della lista
				if(type.containsKey(format[format.length-1])) {
				     i = type.get(format[format.length-1])+1;
				     type.put(format[format.length-1], i);
			      }
				else type.put(format[format.length-1], ++i);
			}
		}
		return type;
	}
	
	
	/**
	 * Metodo che restituisce il numero di file eliminati per ogni tipo presente nella cartella subfolder inserita dall'utente
	 * se subfolder è vuota prende la cartella principale
	 * @param records tutti i file eliminati
	 * @param subfolder cartella dove effettuare la statistica
	 * @return type HashMap<String, Integer> tabella che rappresenta quanti file sono presenti per ogni tipo nella cartella scelta
	 */
	public static HashMap<String, Integer> getDeletedFileType(List<RecordDeleted> records, String subfolder){
		HashMap<String, Integer> type = new HashMap<String, Integer>();
		for(Record r:records) {
			Integer i=0;
			String[] format = r.getName().split(Pattern.quote("."));
			if(!subfolder.isEmpty()) {    //controllo se la sottocartella è vuota o no
				   if(r.getPath_lower().contains(subfolder)) {    //se non è vuota controllo se il file sia dentro la cartella
				      if(type.containsKey(format[format.length-1])) {  //controllo se in type è già presente un file di quel formato.. format[format.length-1] sarebbe il formato del file preso in considerazione in questa iterazione del for-each
					     i = type.get(format[format.length-1])+1;      //se è già presente incremento di uno il contatore di quel formato specifico, contatore ovvero la variabile i
					     type.put(format[format.length-1], i);         //inserisco nell'HashMap type il nuovo valore di i associato al suo formato
				      }
				      else type.put(format[format.length-1], ++i);     //se non è presente quel formato inserisco in type il formato con il suo contatore i che lo vado a preincrementare(per settarlo a 1 essendo prima a 0 perchè non era presente quel formato)
				   }
			}
			else {   //se subfolder è vuota, prendo tutti i file della lista
					if(type.containsKey(format[format.length-1])) {
					     i = type.get(format[format.length-1])+1;
					     type.put(format[format.length-1], i);
				    }
					else type.put(format[format.length-1], ++i);
			}
		}
		return type;
	}
}
 