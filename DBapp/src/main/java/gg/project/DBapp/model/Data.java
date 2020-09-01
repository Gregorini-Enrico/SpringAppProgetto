package gg.project.DBapp.model;

import java.util.*;
import gg.project.DBapp.Storage.DeletedParser;
import gg.project.DBapp.Storage.Parser;

public class Data {
	
	/**
	 * Metodo che estrae i dati richiesti dall'api e li inserisce in una lista di File
	 * 
	 */
	public static ArrayList<Record> getRecords(Parser p){
   	 Record r = null;
   	 ArrayList<Record> records = new ArrayList<Record>();
   	 if(p.entries.size() > 0)
   		 for(HashMap<String,Object> rf : p.entries) {
   			 r = new Record();
   			 r.setTag((String)rf.get(".tag"));
   			 r.setName((String)rf.get("name"));
   			 r.setPath_lower((String)rf.get("path_lower"));
   			 if(r.getTag()=="folder")
   				 ((RecordFolder) r).setId((String)rf.get("id"));
   			 else if(r.getTag()=="file") {
   				 ((RecordFile) r).setId((String)rf.get("id"));
   				 ((RecordFile) r).setClient_modified((String)rf.get("client_modified"));
   				 ((RecordFile) r).setRev((String)rf.get("rev"));
   				 ((RecordFile) r).setSize((int)rf.get("size"));
   			 }
   			 else if(r.getTag()=="deleted")
   				 r = new RecordDeleted();
   			 
   		     records.add(r);
   		 }
   	 return records;
    }

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
	   			 recordsdeleted.add(rd);
	   		 }
	   	   }
	   	 return recordsdeleted;
	    }
	
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
	