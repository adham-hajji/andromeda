package pkg_Characters;


import java.util.Stack;

import pkg_Exceptions.NonExistentBeamedRoomException;
import pkg_Exceptions.NonExistentExitException;
import pkg_Exceptions.NonExistentItemException;
import pkg_Exceptions.NonValidCommandException;
import pkg_Exceptions.NotHavingBeamerException;
import pkg_Exceptions.TooHeavyItemException;
import pkg_Items.BeamerItem;
import pkg_Items.Item;
import pkg_Items.ItemList;
import pkg_Engine.Room;


/**
 * Un personnage jouable du jeu.
 * @author adham
 */
public class Player extends Character
{
    private Stack<Room> aTravelHistory;
    private ItemList aItems;
    private int aMaximumWeight;
	private Room aCurrentRoom;
	
    
	/**
	 * 
	 * @param pName Nom du joueur
	 * @param pDescription Description du joueur
	 * @param pCurrentRoom Salle actuelle
	 * @param pMaximumWeight Poids maximale d'un item
	 */
    public Player(final String pName, final String pDescription, final Room pCurrentRoom, final int pMaximumWeight)
    {
    	super(pName, pDescription);
    	
    	this.aCurrentRoom = pCurrentRoom;
    	this.aTravelHistory = new Stack<Room>();
    	this.aItems = new ItemList();
    	this.aMaximumWeight = pMaximumWeight;
    }//Player
    
    
    /**
     * Définir la salle actuelle
     * @param pCurrentRoom Salle actuelle
     */
    public void setCurrentRoom(final Room pCurrentRoom) {
    	this.aCurrentRoom = pCurrentRoom;
    }//setCurrentRoom()
    
    
    /**
     * Obtenir la salle actuelle
     * @return Salle actuelle
     */
	public Room getCurrentRoom() {
		return this.aCurrentRoom;
	}//getCurrentRoom()
    
    
    /**
     * Définir le poids maximal d'un item
     * @param pMaximumItemWeight Poids maximale d'un item
     */
    public void setMaximumWeight(final int pMaximumItemWeight) {
    	this.aMaximumWeight = pMaximumItemWeight;
    }//setMaximumWeight()
    
    
    /**
     * Aller à une salle
     * @param pNextRoom Salle d'après
     */
    public void goRoom(final Room pNextRoom)
    {
    	this.aTravelHistory.push(this.getCurrentRoom());
    	this.setCurrentRoom(pNextRoom);
    }//goRoom()
    
    
    /**
     * Aller à la salle précédente
     * @throws NonValidCommandException
     * @throws NonExistentExitException
     */
    public void goBack() throws NonValidCommandException, NonExistentExitException
    {
    	if(this.aTravelHistory.isEmpty()) throw new NonValidCommandException();
    	if(!this.aTravelHistory.lastElement().isExit(this.getCurrentRoom())) throw new NonExistentExitException();
    	
    	Room vPreviousRoom = this.aTravelHistory.pop();
    	this.setCurrentRoom(vPreviousRoom);
    }//goBack()
    
    
    /**
     * Prendre l'item
     * @param pItemName
     * @return Description de l'item prit
     * @throws NonExistentItemException
     * @throws TooHeavyItemException
     */
    public String takeItem(final String pItemName) throws NonExistentItemException, TooHeavyItemException
    {
    	Item vTakenItem = this.getCurrentRoom().getItem(pItemName);
    	
    	if(vTakenItem.getWeight() > this.aMaximumWeight) throw new TooHeavyItemException();
    	
    	this.getCurrentRoom().removeItem(pItemName);
    	this.aItems.addItem(pItemName, vTakenItem);
    	
    	return vTakenItem.getLongDescription();
    }//takeItem()
    
    
    /**
     * Lacher l'item
     * @param pItemName
     * @return Description de l'item laché
     * @throws NonExistentItemException
     */
    public String dropItem(final String pItemName) throws NonExistentItemException
    {
    	Item vDroppedItem = this.aItems.getItem(pItemName);
    	
    	this.getCurrentRoom().setItem(pItemName, vDroppedItem);
    	this.aItems.removeItem(pItemName);
    	
    	return vDroppedItem.getShortDescription();
    }//dropItem()
    
    
    /**
     * Jeter l'item
     * @param pItemName
     * @return Description de l'item jeté
     * @throws NonExistentItemException
     */
    public String removeItem(final String pItemName) throws NonExistentItemException
    {
    	Item vRemovedItem = this.aItems.getItem(pItemName);
    	this.aItems.removeItem(pItemName);
    	
    	return vRemovedItem.getShortDescription();
    }//removeItem()
    
    
    /**
     * Obtenir la liste des items de l'inventaire
     * @return Liste des items de l'inventaire
     */
    public String getItemList()
    {
    	StringBuilder vItemList = new StringBuilder();
    	vItemList.append("Il y a " + this.aItems.length() + " objet(s) présent(s) dans votre inventaire.\n");
    	vItemList.append(this.aItems.getItemList() + "\n");
    	
    	return vItemList.toString();
    }//getItemList()
    
    
    /**
     * Connaître l'existence d'un item dans l'inventaire
     * @param pItemName Nom de l'item
     * @return
     */
    public boolean hasItem(final String pItemName)
    {
    	if(this.aItems.hasItemName(pItemName))
    		return true;
    	else
    		return false;
    }//hasBeamer()
    
    
    /**
     * Charger le beamer
     * @throws NotHavingBeamerException
     */
    public void chargeBeamer() throws NotHavingBeamerException
    {
    	try {
	    	BeamerItem vBeamer = (BeamerItem) this.aItems.getItem("beamer");
	    	vBeamer.setBeamedRoom(this.getCurrentRoom());
    	}
    	catch(NonExistentItemException pError) {
    		throw new NotHavingBeamerException();
    	}
    }//chargeBeamer()
    
    
    /**
     * Actionner le beamer
     * @throws NotHavingBeamerException
     * @throws NonExistentBeamedRoomException
     */
    public void fireBeamer() throws NotHavingBeamerException, NonExistentBeamedRoomException
    {
    	try {
    		BeamerItem vBeamer = (BeamerItem) this.aItems.getItem("beamer");
        	this.setCurrentRoom(vBeamer.getBeamedRoom());
        	vBeamer.removeBeamedRoom();
    	}
    	catch(NonExistentItemException pError) {
    		throw new NotHavingBeamerException();
    	}
    }//fireBeamer()
    
}//Player
