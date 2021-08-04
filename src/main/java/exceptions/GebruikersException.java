package exceptions;

public class GebruikersException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GebruikersException()
    {
		 super("Er is iets gemist gelopen met het aanmaken van speler!");
    }
    
    public GebruikersException(String message)
    {
        super(message);
    }

    public GebruikersException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikersException(Throwable cause)
    {
        super(cause);
    }

    public GebruikersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
