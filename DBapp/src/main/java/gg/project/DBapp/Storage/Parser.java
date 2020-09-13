package gg.project.DBapp.Storage;

import java.util.HashMap;
import java.util.List;

/**
 * Classe per parsare i Record
 * ha come attributi i vari campi dati dalla risposta JSON alla chiamata /files/list_folder
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class Parser {
	public List<HashMap<String,Object>> entries;
	public String cursor;
	public boolean has_more;
}
