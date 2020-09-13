package gg.project.DBapp.Exception;

/**
 * Classe per gestire eventuali date inserite incorrentamente dall'utente
 * ad es se l'anno ha 5 cifre o il mese è superiore a 12, o il giorno è superiore a 31
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class DateIncorrectException extends FilterIncorrectException{
    
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "La data inserita non è valida!";
	}
  
}
