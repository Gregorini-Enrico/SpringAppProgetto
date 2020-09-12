package gg.project.DBapp.Stats;


import java.util.*;
import java.util.regex.Pattern;

import gg.project.DBapp.Exception.SubfolderNotFoundException;
import gg.project.DBapp.model.*;

public class Statistics {
		
	/**
	 * Metodo per calcolare la media delle dimensioni dei file presenti nella cartella inserita dall'utente
	 * se subfolder è vuota viene presa la cartella principale
	 * @param records lista dei Record da analizzare
	 * @param subfolder sottocartella scelta dall'utente
	 * return float media della dimensione dei file
	 * @throws SubfolderNotFoundException 
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 * */
	public static double media(List<RecordFile> records, String subfolder) throws SubfolderNotFoundException {
		double s = 0;
		for(RecordFile f:records) {
			if(!subfolder.isEmpty()) { //controllo se la sottocartella è vuota o no
				if(f.getPath_lower().contains(subfolder)) {  //se non è vuota controllo se il file sia dentro la cartella
			         double KB = f.getSize()/(1024*1024); //calcolo la dimensione in MB
			         s += KB;
				}
			}
			else{   //se subfolder è vuota, prendo tutti i file della lista
				    if(f.getPath_lower().contains(subfolder)) {
		               double KB = f.getSize()/(1024*1024); //calcolo la dimensione in MB
		               s += KB;
			        }  
			}
		}
		if(s==0)  throw new SubfolderNotFoundException();
		double media = s/records.size(); //calcolo la media in MB
		media = Math.round(media*100);   media /= 100;  //arrotondo la media a 2 cifre decimali
		return media; //ritorno la media
		}
	
	/**
	 * Metodo per calcolare la media delle dimensioni dei file presenti nella cartella inserita dall'utente
	 * se subfolder è vuota viene presa la cartella principale
	 * @param records lista dei Record da analizzare
	 * @param subfolder sottocartella scelta dall'utente
	 * return float media della dimensione dei file
	 * @throws SubfolderNotFoundException 
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	public static double mediaDeletedFile(List<RecordDeleted> Drecords, String subfolder) throws SubfolderNotFoundException {
		double s = 0;
		for(RecordDeleted f:Drecords){
			if(!subfolder.isEmpty()) {  //controllo se la sottocartella è vuota o no
				if(f.getPath_lower().contains(subfolder)) {    //se non è vuota controllo se il file sia dentro la cartella
			         double KB = f.getSize()/(1024*1024); //calcolo la dimensione in MB
			         s += KB;
				}
			}
			else{    //se subfolder è vuota, prendo tutti i file della lista
				    if(f.getPath_lower().contains(subfolder)) {
		               double KB = f.getSize()/(1024*1024); //calcolo la dimensione in MB
		               s += KB;
			        }  
			}
		}
		if(s==0)  throw new SubfolderNotFoundException();
		double media = s/Drecords.size(); //calcolo la media in MB
		media = Math.round(media*100);   media /= 100;  //arrotondo la media a 2 cifre decimali
		return media; //ritorno la media
	}
	
	
	
	
	/**
	 * Metodo che restituisce il file con la dimensione più grande
	 * @param records lista di file presenti
	 * @param subfolder sottocartella scelta dall'utente 
	 * @return max file più grande
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	public static RecordFile maxDimFile(List<RecordFile> records, String subfolder) throws SubfolderNotFoundException {
		RecordFile max = new RecordFile();
		for(RecordFile r:records){ 
			if(!subfolder.isEmpty()) {   //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()>max.getSize())    max = r; //calcolo la dimensione in B
			}
			else { if(r.getSize()>max.getSize())    max = r;}
		}
		if(max.getName().equals(null)) throw new SubfolderNotFoundException();
		return max; //ritorno il file con dimensione massima
	}
	
	/**
	 * Metodo che restituisce il file eliminato con la dimensione più grande
	 * @param Drecords lista di file eliminati
	 * @param subfolder sottocartella scelta dall'utente 
	 * @return max file eliminato più grande
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	public static RecordDeleted maxDimFileDeleted(List<RecordDeleted> Drecords, String subfolder) throws SubfolderNotFoundException {
		RecordDeleted max = new RecordDeleted();
		for(RecordDeleted r:Drecords){ 
			if(!subfolder.isEmpty()) {   //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))  //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()>max.getSize())    max = r; //calcolo la dimensione in B
			}
			else { if(r.getSize()>max.getSize())    max = r;}
		}
		if(max.getName().equals(null)) throw new SubfolderNotFoundException();
		return max; //ritorno il file con dimensione massima in KB
	}
	
	/**
	 * Metodo che restituisce il file con la dimensione più piccola
	 * @param Drecords lista di file presenti
	 * @param subfolder sottocartella scelta dall'utente 
	 * @return min file più grande
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	public static RecordFile minDimFile(List<RecordFile> records, String subfolder) throws SubfolderNotFoundException {
		RecordFile min = new RecordFile();
		min.setSize(100000000);
		for(RecordFile r:records){ 
			if(!subfolder.isEmpty()) {    //controllo se la sottocartella è vuota o no
			    if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
				     if(r.getSize()<min.getSize())    min = r; //calcolo la dimensione in B
			}
		    else { if(r.getSize()<min.getSize())    min = r;}    //se subfolder è vuota, prendo tutti i file della lista
		}
		if(min.getName().equals(null)) throw new SubfolderNotFoundException();
		return min; //ritorno il file con dimensione massima
	}
	
	/**
	 * Metodo che restituisce il file eliminato con la dimensione più piccola
	 * @param Drecords lista di file eliminati
	 * @param subfolder sottocartella scelta dall'utente 
	 * @return min file eliminato più piccola
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	public static RecordDeleted minDimFileDeleted(List<RecordDeleted> Drecords, String subfolder) throws SubfolderNotFoundException {
		RecordDeleted min = new RecordDeleted();
		min.setSize(100000000);
		for(RecordDeleted r:Drecords) { 
			if(!subfolder.isEmpty()) {    //controllo se la sottocartella è vuota o no
		        if(r.getPath_lower().contains(subfolder))   //se non è vuota controllo se il file sia dentro la cartella
			        if(r.getSize()<min.getSize())    min = r; //calcolo la dimensione in B
		    }
			else { if(r.getSize()<min.getSize())    min = r;}      //se subfolder è vuota, prendo tutti i file della lista
		}
		if(min.getName().equals(null)) throw new SubfolderNotFoundException();
		return min; //ritorno il file con dimensione massima in B
	}
	
	
	
	
	
	
	
	/**
	 * Metodo che restituisce il numero di file presenti per ogni tipo presente nella cartella subfolder inserita dall'utente
	 * se subfolder è vuota prende la cartella principale
	 * @param records tutti i file presenti
	 * @param subfolder cartella dove effettuare la statistica
	 * @return type HashMap<String, Integer> tabella che rappresenta quanti file sono presenti per ogni tipo nella cartella scelta
	 * @throws SubfolderNotFoundException 
	 */
	public static HashMap<String, Integer> getFileType(List<RecordFile> records, String subfolder) throws SubfolderNotFoundException{
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
		if(type.isEmpty())  throw new SubfolderNotFoundException();
		return type;
	}
	
	
	/**
	 * Metodo che restituisce il numero di file eliminati per ogni tipo presente nella cartella subfolder inserita dall'utente
	 * se subfolder è vuota prende la cartella principale
	 * @param records tutti i file eliminati
	 * @param subfolder cartella dove effettuare la statistica
	 * @return type HashMap<String, Integer> tabella che rappresenta quanti file sono presenti per ogni tipo nella cartella scelta
	 * @throws SubfolderNotFoundException 
	 */
	public static HashMap<String, Integer> getDeletedFileType(List<RecordDeleted> records, String subfolder) throws SubfolderNotFoundException{
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
		if(type.isEmpty())  throw new SubfolderNotFoundException();
		return type;
	}
}
 