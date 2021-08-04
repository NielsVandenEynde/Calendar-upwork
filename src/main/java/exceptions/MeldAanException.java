package exceptions;

public class MeldAanException extends Exception{
	
	public MeldAanException()
    {
		 super("foutAanmelden");
    }
    
    public MeldAanException(String message)
    {
        super(message);
    }

    public MeldAanException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MeldAanException(Throwable cause)
    {
        super(cause);
    }

    public MeldAanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
	

}
