package gg.project.DBapp.Exception;

public class DateFilterFormatIncorrectException extends FilterIncorrectException{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Il filtro è incorretto!!  Es: { date: { between: [datainizio,datafine]}}";
	}

}
