package gg.project.DBapp.Storage;

import java.util.HashMap;
import java.util.List;

/**
 * Classe per parsare i file eliminati
 * ha come attributi i vari campi dati dalla risposta JSON alla chiamata /files/list_revisions
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class DeletedParser {
	
	public boolean is_deleted;
	public String server_deleted;
	public List<HashMap<String,Object>> entries;
}
