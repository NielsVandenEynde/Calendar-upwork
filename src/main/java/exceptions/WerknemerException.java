package exceptions;

import java.util.List;
import java.util.Set;

public class WerknemerException extends Exception{

	public WerknemerException()
    {
		 super("Er is iets gemist gelopen met Klant!");
    }
	
	public WerknemerException(String message)
    {
		 super(message);
    }
    
    public WerknemerException(List<String> exceptions)
    {
        super(exceptions.toString());
    }

    public WerknemerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public WerknemerException(Throwable cause)
    {
        super(cause);
    }

    public WerknemerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

	public WerknemerException(Set<String> exceptions) {
		super(exceptions.toString());
	}
}
