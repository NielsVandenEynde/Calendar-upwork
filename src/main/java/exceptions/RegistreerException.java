
package exceptions;

public class RegistreerException extends Exception{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistreerException()
    {
		 super("Er is iets gemist gelopen met het registreren!");
    }
    
    public RegistreerException(String message)
    {
        super(message);
    }

    public RegistreerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RegistreerException(Throwable cause)
    {
        super(cause);
    }

    public RegistreerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
