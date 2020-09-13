package gg.project.DBapp.model;

import java.util.*;
import gg.project.DBapp.Storage.DeletedParser;
import gg.project.DBapp.Storage.Parser;

/**
 * Classe utilizzata per parsare i dati ottenuti dall'API dropbox
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class Data {
	
	/**
	 * Metodo che estrae i dati richiesti dall'api e li inserisce in una lista di File
	 * prende tutti i Record, quindi sia file che cartelle che file eliminati
	 * @param p Parser che contiene tutti i file dalla chiamata list_folder
	 * @return ArrayList<Record> records, lista di tutti i Record 
	 */
	public static ArrayList<Record> getRecords(Parser p){
   	 Record r = null;
   	 ArrayList<Record> records = new ArrayList<Record>();
   	 if(p.entries.size() > 0)
   		 for(HashMap<String,Object> rf : p.entries) {
   			 r = new Record();    //inizializzo un Record che poi andrò ad inserire nella lista
   			 r.setTag((String)rf.get(".tag"));   //inserisco i vari campi comuni a tutti i tipi di file ovvero i campi di Record
   			 r.setName((String)rf.get("name"));
   			 r.setPath_lower((String)rf.get("path_lower"));
   			 if(r.getTag()=="folder")    //se l'oggetto è una cartella devo inserire anche l'id
   				 ((RecordFolder) r).setId((String)rf.get("id"));
   			 else if(r.getTag()=="file") { //se l'oggetto è un file devo inserire anche tutti gli altri campi che mi serviranno poi nella statistiche e filtri
   				 ((RecordFile) r).setId((String)rf.get("id"));
   				 ((RecordFile) r).setClient_modified((String)rf.get("client_modified"));
   				 ((RecordFile) r).setRev((String)rf.get("rev"));
   				 ((RecordFile) r).setSize((int)rf.get("size"));
   			 }
   			 else if(r.getTag()=="deleted")  //se l'oggetto è un file eliminato, lo istanzio come RecordDeleted, gli altri campi li gestisco in un'altra classe per la list_revision
   				 r = new RecordDeleted();
   			 
   		     records.add(r);  //inserisco l'oggetto r nella lista di superclasse Record
   		 }
   	 return records;  //ritorno la lista con tutti gli oggetti presenti nella cartella principale dropbox
    }
	
	
    /**
     * Metodo che memorizza tutti i campi dei file eliminati dopo la list_revision SENZA DUPLICATI
     * @param dp arraylist di DeletedParser che contiene tutti i file eliminati dalla riposta alla richiesta list_revision all'API di dropbox
     * @return recordsdeleted lista di file eliminati senza duplicati
     * @author Enrico Gregorini
     * @author Daniele Gjeka
     */
	public static ArrayList<RecordDeleted> getRecordsDeleted(ArrayList<DeletedParser> dp){
		 RecordDeleted rd = null;
	   	 ArrayList<RecordDeleted> recordsdeleted = new ArrayList<RecordDeleted>();
	   	 
	   	   for(int k=0; k<dp.size(); k++) {
	  	   	 if(dp.get(k).entries.size() > 0)
	   		 for(HashMap<String,Object> rf : dp.get(k).entries) {
	   			 rd = new RecordDeleted();
	   			 rd.setTag("deleted");
	   			 rd.setName((String)rf.get("name"));
	   			 rd.setPath_lower((String)rf.get("path_lower"));
	   			 rd.setId((String)rf.get("id"));
	   			 rd.setClient_modified((String)rf.get("client_modified"));
	   			 rd.setRev((String)rf.get("rev"));
	   			 rd.setSize((int)rf.get("size"));
	   			 if(recordsdeleted.size()>0) {  //controllo se la lista è vuota, se è vuota non possono esserci duplicati
	   			      if(!recordsdeleted.get(recordsdeleted.size()-1).getId().equals(rd.getId()))   //se gli id corrispondono non inserisco l'oggetto così evito i duplicati per le varie versioni dei file eliminati
	   			           recordsdeleted.add(rd);   //se non è un duplicato inserisco il record nella lista
	   			 }
	   			 else      recordsdeleted.add(rd);   //se è vuota inserisco il primo record
	   		 }
	   	   }
	   	 return recordsdeleted;  //ritorno la lista di file eliminati senza duplicati 
	    }
	 
	
	 /**
	  * Metodo che ritorna solo i file presenti nell'account dropbox (senza cartelle)
	  * serve per le statistiche e i filtri 
	  * @param p Parser che contiene tutti i file dalla chiamata list_folder
	  * @return files ArrayList di RecordFile che contiene tutta la lista dei file presenti(non eliminati)
	  */
	 public static ArrayList<RecordFile> getOnlyFile(Parser p){
		 RecordFile file = null;
	   	 ArrayList<RecordFile> files = new ArrayList<RecordFile>();
	   	 if(p.entries.size() > 0)
	   		 for(HashMap<String,Object> rf : p.entries) {
	   			 file = new RecordFile();
	   			 file.setTag((String)rf.get(".tag"));
	   			if(file.getTag().equals("file")) {
	   			 file.setName((String)rf.get("name"));
	   			 file.setPath_lower((String)rf.get("path_lower"));
	   			 file.setId((String)rf.get("id"));
	   			 file.setClient_modified((String)rf.get("client_modified"));
	   			 file.setRev((String)rf.get("rev"));
	   			 file.setSize((int)rf.get("size"));
	   			 files.add(file);
	   			 }
	   		 }
	   	 return files;
		 }
}
	