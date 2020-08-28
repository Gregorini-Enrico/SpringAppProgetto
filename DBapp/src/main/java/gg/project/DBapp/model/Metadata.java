package gg.project.DBapp.model;

import java.util.HashMap;

public class Metadata {
          
	public static HashMap<String,String> getFileMetadata(){
		HashMap<String,String> metadata = new HashMap<String, String>();
		metadata.put("name", "nome del file con l'estensione");
		metadata.put("tag", "tipologia di elemento('folder','file','deleted')");
		
		
		return metadata;
	}
}
