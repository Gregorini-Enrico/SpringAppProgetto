package gg.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
	/**
	 * Metodo che estrae i dati richiesti dall'api e li inserisce in una lista di File
	 * 
	 */
	
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

}