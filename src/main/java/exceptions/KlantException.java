package exceptions;

import java.util.List;
import java.util.Set;

public class KlantException extends Exception{

	public KlantException()
    {
		 super("Er is iets gemist gelopen met Klant!");
    }
	
	public KlantException(String message)
    {
		 super(message);
    }
    
    public KlantException(List<String> exceptions)
    {
        super(exceptions.toString());
    }

    public KlantException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public KlantException(Throwable cause)
    {
        super(cause);
    }

    public KlantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

	public KlantException(Set<String> exceptions) {
		super(exceptions.toString());
	}
}
