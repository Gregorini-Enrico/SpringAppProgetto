package gg.project.DBapp.Exception;

/**
 * Classe per gestire l'eccezione nel caso in cui l'utente inserisce un intervallo di date dove la data di fine sia precedente a quella di inizio
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class DateFilterIncorrectException extends FilterIncorrectException{

	private static final long serialVersionUID = 1L;

	
	@Override
	public String getMessage() {
		return "La data di fine intervallo è precedente a quella di inzio!";
	}
 
	
}
