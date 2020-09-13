package gg.project.DBapp.Exception;

/**
 * Classe per generare l'eccezione nel caso in cui non esistano file del tipo scelto dall'utente
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class TypeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Non esistono file di questo tipo nella cartella principale dropbox!";
	}
}
