package pkg_Items;


import pkg_Exceptions.NonExistentBeamedRoomException;
import pkg_Engine.Room;


/**
 * Un beamer implémentant la capacité de se téléporter.
 * @author adham
 */
public class BeamerItem extends Item
{
	private Room aBeamedRoom;
	
	
	public BeamerItem() {
		this("Beamer", "Cet objet extrêmement rare vous permet de vous téléporter à l'endroit de votre choix.", 5000, 2);
	}//BeamerItem()

	
	/**
	 * @see pkg_Items.Item
	 */
	public BeamerItem(String pName, String pDescription, int pPrice, int pWeight) {
		super(pName, pDescription, pPrice, pWeight);
	}//BeamerItem()
	
	
	/**
	 * Définir la salle chargée du beamer
	 * @param pBeamedRoom Salle chargée du beamer
	 */
	public void setBeamedRoom(final Room pBeamedRoom) {
		this.aBeamedRoom = pBeamedRoom;
	}//setBeamedRoom()
	
	
	/**
	 * Obtenir la salle chargée du beamer
	 * @return Salle chargée du beamer
	 * @throws NonExistentBeamedRoomException
	 */
	public Room getBeamedRoom() throws NonExistentBeamedRoomException
	{
		if(!this.hasBeamedRoom()) throw new NonExistentBeamedRoomException();
		return this.aBeamedRoom;
	}//getBeamedRoom()
	
	
	/**
	 * Savoir si le beamer a une salle chargée
	 * @return true si le beamer a une salle chargée, faux sinon
	 */
	public boolean hasBeamedRoom() {
		return !(this.aBeamedRoom == null);
	}//hasBeamedRoom()
	
	
	/**
	 * Supprimer la salle chargée du beamer
	 * @throws NonExistentBeamedRoomException
	 */
	public void removeBeamedRoom() throws NonExistentBeamedRoomException
	{
		if(!this.hasBeamedRoom()) throw new NonExistentBeamedRoomException();
		this.aBeamedRoom = null;
	}//removeBeamedRoom()
	
}//BeamerItem
