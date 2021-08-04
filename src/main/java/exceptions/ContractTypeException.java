package exceptions;

import java.util.Set;

public class ContractTypeException extends Exception{

	public ContractTypeException()
    {
		 super("Er is iets gemist gelopen met ContractType!");
    }
    
    public ContractTypeException(String message)
    {
        super(message);
    }

    public ContractTypeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ContractTypeException(Throwable cause)
    {
        super(cause);
    }

    public ContractTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

	public ContractTypeException(Set<String> exceptions) {
		super(exceptions.toString());
	}
}
