package gg.project.DBapp.Exception;

/**
 * Classe per generare l'eccezione dovuta all'inserimento di un filtro incorretto dall'utente
 * genera l'eccezione se ad esempio l'utente inserisce una parola sbagliata come campo da filtrare
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class DateFilterFormatIncorrectException extends FilterIncorrectException{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Il filtro è incorretto!!";
	}

}
