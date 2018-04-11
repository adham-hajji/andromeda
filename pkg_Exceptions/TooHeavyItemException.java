package pkg_Exceptions;

/**
 * L'item est trop lourd pour le joueur.
 * @author adham
 */
public class TooHeavyItemException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "L'objet que vous souhaitez prendre est trop lourd !";
	}//getMessage()
	
}//TooHeavyItemException