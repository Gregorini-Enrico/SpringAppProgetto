package gg.project.DBapp.model;

import java.util.HashMap;

/**
 * Classe che serve per restituire i metadata per una richiesta GET
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class Metadata {
          
	public static HashMap<String,String> getFileMetadata(){
		HashMap<String,String> metadata = new HashMap<String, String>();
		metadata.put("name", "nome del file con l'estensione");
		metadata.put("tag", "tipologia di elemento('folder','file','deleted')");
		metadata.put("path_lower", "path del file");
		metadata.put("id", "identificativo univoco del file");
		metadata.put("client_modified", "informazioni (data, ora) sulla modifica del file su DB");
		metadata.put("rev", "codice univoco per ogni tipologia di file(.txt, .pdf, .docx ecc) per il restore");
		metadata.put("size", "dimensione del file in byte");
		return metadata;
	}
}
