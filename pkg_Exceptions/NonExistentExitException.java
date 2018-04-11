package pkg_Exceptions;

/**
 * La sortie renseignée n'existe pas.
 * @author adham
 */
public class NonExistentExitException extends Exception
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Il n'y a pas de spatioroute allant dans cette direction !";
	}//getMessage()
	
}//NonExistentExitException