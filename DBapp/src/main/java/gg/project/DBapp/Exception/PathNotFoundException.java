package gg.project.DBapp.Exception;

public class PathNotFoundException extends RuntimeException{

	/**
	 * Classe per generare l'eccezione nel caso in cui non esista il path inserito dall'utente
	 * @author Enrico Gregorini
     * @author Daniele Gjeka
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Non esiste nessun file al path inserito nella cartella principale dropbox!";
	}
}
