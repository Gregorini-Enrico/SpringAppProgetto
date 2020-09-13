package gg.project.DBapp.Exception;

/**
 * Classe per generare l'eccezione nel caso in cui non esistano file nell'intervallo temporale scelto dall'utente
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class FileInDateNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Non esistono file nell'intervallo temporale inserito!";
	}
}
