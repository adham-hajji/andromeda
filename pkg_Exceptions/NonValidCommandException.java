package pkg_Exceptions;

/**
 * La commande renseignée par le joueur est invalide.
 * @author adham
 */
public class NonValidCommandException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "La commande que vous avez tapé n'est pas valide !\n";
	}//getMessage()
	
}//NonValidCommandException
