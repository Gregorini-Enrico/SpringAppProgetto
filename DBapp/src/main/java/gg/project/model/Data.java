package gg.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import gg.project.Storage.Parser;

public class Data {
	
	public static ArrayList<Record> getRecords(Parser p){
   	 Record r = null;
   	 ArrayList <Record> records = new ArrayList<Record>();
   	 if(p.entries.size() > 0)
   		 for(HashMap<String,Object> rf : p.entries) {
   			 r = new Record();
   			 r.setTag((String)rf.get("tag"));			 
   			 r.setName((String)rf.get("name"));
   			 r.setPath_lower((String)rf.get("path_lower"));
   		     records.add(r);
   		 }
   	 return records;
    }
	
	
	
	
	/**
	 * Metodo che estrae i dati richiesti dall'api e li inserisce in una lista di File
	 * 
	 */
     /*public static ArrayList<RecordFolder> getFolder(Parser p){
    	 RecordFolder f = null;
    	 ArrayList <RecordFolder> folders = new ArrayList<RecordFolder>();
    	 if(p.entries.size() > 0)
    		 for(HashMap<String,Object> rf : p.entries) {
    			 f = new RecordFolder();
    			 f.setTag((String)rf.get("tag"));			 
    			 f.setName((String)rf.get("name"));
    			 f.setPath((String)rf.get("path_lower"));
    		     f.setId((String)rf.get("id"));
    		     folders.add(f);
    		 }
    	 return folders;
     }
     
     public static List<RecordFile> getFile(List<HashMap<String,Object>> dati){
    	 RecordFile f = null;
    	 List <RecordFile> files = new ArrayList<RecordFile>();
    	 if(dati.size() > 0)
    		 for(HashMap<String,Object> hm : dati) {
    			 f = new RecordFile();
    			 f.setTag((String)hm.get("tag"));			 
    			 f.setName((String)hm.get("name"));
    			 f.setPath((String)hm.get("path_lower"));
    		     f.setId((String)hm.get("id"));
                 f.setClient_modified((String)hm.get("client_modified"));
    		     f.setServer_modified((String)hm.get("server_modified"));
                 f.setRev((String)hm.get("rev"));
    		     f.setSize((int)hm.get("size"));
    		     files.add(f);
    		 }
    	 return files;
     }
     
     public static List<RecordDeleted> getDeletedFile(List<HashMap<String,Object>> dati){
    	 RecordDeleted f = null;
    	 List <RecordDeleted> Dfiles = new ArrayList<RecordDeleted>();
    	 if(dati.size() > 0)
    		 for(HashMap<String,Object> hm : dati) {
    			 f = new RecordDeleted();
    			 f.setTag((String)hm.get("tag"));			 
    			 f.setName((String)hm.get("name"));
    			 f.setPath((String)hm.get("path_lower"));
    		     Dfiles.add(f);
    		 }
    	 return Dfiles;
     }*/

}