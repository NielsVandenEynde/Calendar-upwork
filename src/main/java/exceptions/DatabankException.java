package exceptions;

public class DatabankException extends Exception {
	
	public DatabankException()
    {
		 super(String.format("Daan: 'Kunt ge databank ook in het engels zeggen?'%n Niels: 'Da is een goede vraag Daan, er zijn geen domme vragen %n"));
    }
    
    public DatabankException(String message)
    {
        super(message);
    }

    public DatabankException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DatabankException(Throwable cause)
    {
        super(cause);
    }

    public DatabankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
