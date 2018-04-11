package pkg_Exceptions;

/**
 * La chaîne de texte renseignée est invalide.
 * @author adham
 */
public class NotValidStringException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "La chaîne de caractères renseignée est invalide !";
	}//getMessage()
	
}//NonExistentExitException