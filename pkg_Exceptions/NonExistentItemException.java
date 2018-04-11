package pkg_Exceptions;

/**
 * L'item en question n'existe pas.
 * @author adham
 */
public class NonExistentItemException extends Exception
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Cet objet n'existe pas !\n";
	}//getMessage()
	
}//NonExistentItemException
