package gg.project.DBapp.model;

import java.util.ArrayList;
import java.util.HashMap;

import gg.project.DBapp.Storage.DeletedParser;
import gg.project.DBapp.Storage.Parser;
import gg.project.DBapp.Storage.RestoreParser;

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

	public static ArrayList<RecordDeleted> getRecordsDeleted(DeletedParser[] dp){
		RecordDeleted rd = null;
	   	 ArrayList<RecordDeleted> recordsdeleted = new ArrayList<RecordDeleted>();
	   	 int i=0;
	   	 if(dp[i].entries.size() > 0)
	   		 for(HashMap<String,Object> rf : dp[i].entries) {
	   			 rd = new RecordDeleted();
	   			 rd.setTag((String)rf.get(".tag"));
	   			 rd.setName((String)rf.get("name"));
	   			 rd.setPath_lower((String)rf.get("path_lower"));
	   			 rd.setId((String)rf.get("id"));
	   			 rd.setClient_modified((String)rf.get("client_modified"));
	   			 rd.setRev((String)rf.get("rev"));
	   			 rd.setSize((int)rf.get("size"));
	   			 recordsdeleted.add(rd);
	   			 i++;
	   		 }
	   	 return recordsdeleted;
	    }
}
	
	/*
	public static ArrayList<RecordDeleted> getRecordsRestore(RestoreParser rp){

		RecordDeleted rd = null;
	   	ArrayList<RecordDeleted> recordsdeleted = new ArrayList<RecordDeleted>();

	   		 for(HashMap<String,Object> rf : rp) {
	   			 rd = new RecordDeleted();
	   			 rd.setTag((String)rf.get(".tag"));
	   			 rd.setName((String)rf.get("name"));
	   			 rd.setPath_lower((String)rf.get("path_lower"));
	   			 rd.setId((String)rf.get("id"));
	   			 rd.setClient_modified((String)rf.get("client_modified"));
	   			 rd.setRev((String)rf.get("rev"));
	   			 rd.setSize((int)rf.get("size"));
	   			 recordsdeleted.add(rd);
	   		
	   	 return recordsdeleted;
	    }  
}
	*/