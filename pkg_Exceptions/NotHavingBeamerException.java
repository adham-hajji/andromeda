package pkg_Exceptions;

/**
 * Le joueur ne possède pas le beamer.
 * @author adham
 */
public class NotHavingBeamerException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Vous ne possédez pas un beamer !";
	}//getMessage()
	
}//NotHavingBeamerException