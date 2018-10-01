package com.soffid.iam.addons.webservice.exception;

import javax.ejb.EJBException;

import es.caib.seycon.ng.exception.InternalErrorException;

@SuppressWarnings("serial")
public class UnexpectedException extends Exception {

	String message;
	
	public String getMessage() {
		return message;
	}

	public UnexpectedException(Throwable th) {
		do
		{
	        Throwable t = getCause(th);
	        if (t != null)
	        	th = t;
	        else
	        	break;
		} while (true);
		
		if (th instanceof InternalErrorException)
			message = th.getMessage();
		else
		{
			message = th.getClass().getName()+": "+th.getMessage();
		}
    }

    private static Throwable getCause( Throwable th)
    {
    	Throwable cause = null;
    	if (th instanceof EJBException)
    	{
    		cause = ((EJBException) th).getCausedByException();
    	}
    	if (cause == null)
    		cause = th.getCause();
    	if (cause == th)
    		return null;
    	else
    		return cause;
    }
}
