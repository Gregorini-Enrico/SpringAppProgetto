package gg.project.DBapp.Exception;

/**
 * Classe per gestire l'eccezione nel caso in cui l'utente inserisca una sottocartella non presente
 * @author Enrico gregorini
 * @author Daniele Gjeka
 */
public class SubfolderNotFoundException extends Exception{
    
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "La sottocartella inserita non è presente!";
	}

}
