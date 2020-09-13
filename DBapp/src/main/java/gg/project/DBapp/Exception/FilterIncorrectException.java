package gg.project.DBapp.Exception;

/**
 * Classe astratta per gestire le eccezioni sui filtri incorretti
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public abstract class FilterIncorrectException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public abstract String getMessage();
         
}
