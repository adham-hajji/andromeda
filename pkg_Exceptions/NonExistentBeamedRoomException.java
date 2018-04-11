package pkg_Exceptions;

/**
 * Le beamer n'a pas de salle chargée.
 * @author adham
 */
public class NonExistentBeamedRoomException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Aucune salle n'a été chargée par le beamer !";
	}//getMessage()
	
}//NonExistentExitException