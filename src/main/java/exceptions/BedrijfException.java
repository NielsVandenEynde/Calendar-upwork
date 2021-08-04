package exceptions;

import java.util.List;
import java.util.Set;

public class BedrijfException extends Exception{

	public BedrijfException()
    {
		 super("Er is iets gemist gelopen met Klant!");
    }
	
	public BedrijfException(String message)
    {
		 super(message);
    }
    
    public BedrijfException(List<String> exceptions)
    {
        super(exceptions.toString());
    }

    public BedrijfException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BedrijfException(Throwable cause)
    {
        super(cause);
    }

    public BedrijfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

	public BedrijfException(Set<String> exceptions) {
		super(exceptions.toString());
	}
}
