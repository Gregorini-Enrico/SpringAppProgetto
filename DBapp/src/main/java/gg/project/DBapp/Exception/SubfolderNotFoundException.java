package gg.project.DBapp.Exception;

public class SubfolderNotFoundException extends Exception{
    /**
     * Classe per gestire l'eccezione nel caso in cui l'utente inserisca una sottocartella non presente
     * @author Enrico gregorini
	 * @author Daniele Gjeka
     */

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "La sottocartella inserita non è presente!";
	}

}
