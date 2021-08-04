package exceptions;

public class TicketException extends Exception {

	public TicketException()
    {
		 super("Er is iets gemist gelopen met het aanmaken van speler!");
    }
    
    public TicketException(String message)
    {
        super(message);
    }

    public TicketException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public TicketException(Throwable cause)
    {
        super(cause);
    }

    public TicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
