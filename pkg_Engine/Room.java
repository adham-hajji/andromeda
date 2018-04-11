package pkg_Engine;


import java.util.HashMap;

import pkg_Exceptions.NonExistentItemException;
import pkg_Items.Item;
import pkg_Items.ItemList;
import pkg_Characters.Character;


/**
 * Une salle du jeu
 * @author adham
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private ItemList aItems;
    private String aImageName;
    private Character aCharacter;
    

    /**
     * @param pDescription Description de la salle
     * @param pImageName Image illustrant la salle
     */
    public Room(final String pDescription, final String pImageName)
    {
        this.aDescription = pDescription;
        this.aImageName = pImageName;
        this.aExits = new HashMap<String, Room>();
        this.aItems = new ItemList();
    }//Room()
    

    /**
     * Créer une sortie
     * @param pDirection Direction de la sortie
     * @param pNeighbor Salle voisine
     */
    public void setExit(final String pDirection, final Room pNeighbor) {
        this.aExits.put(pDirection, pNeighbor);
    }//setExits()
    
    
    /**
     * Obtenir une sortie
     * @param pDirection Direction de la sortie
     * @return Salle voisine
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }//getExit()
    
    
    /**
     * Obtenir la liste des sorties disponibles
     * @return Liste des sorties disponibles
     */
    public String getExitString()
    {
        StringBuilder vExitList = new StringBuilder("Sorties de spatioroute disponibles : ");
        for(String vExit : this.aExits.keySet()) vExitList.append(vExit + " ");
        return vExitList.toString();
    }//getExitString()
    
    
	/**
	 * Ajouter un item
	 * @param pItem L'item à ajouter
	 */
	public void setItem(final Item pItem) {
		this.aItems.addItem(pItem.getName(), pItem);
	}//setItem()
	
	
	/**
	 * Ajouter un item
	 * @param pItemName Nom de l'item à ajouter
	 * @param pItem L'item à ajouter
	 */
	public void setItem(final String pItemName, final Item pItem) {
		this.aItems.addItem(pItemName, pItem);
	}//setItem()
	
	
	/**
	 * Ajouter un PNJ
	 * @param pCharacter PNJ à ajouter
	 */
	public void setCharacter(final Character pCharacter) {
		this.aCharacter = pCharacter;
	}//setCharacter()
	
	
	/**
	 * Obtenir un PNJ
	 * @return Un PNJ
	 */
	public Character getCharacter() {
		return this.aCharacter;
	}//setCharacter()
	
	
	/**
	 * Supprimer un item
	 * @param pItemName Nom de l'item à supprimer
	 * @throws NonExistentItemException
	 */
	public void removeItem(final String pItemName) throws NonExistentItemException {
		this.aItems.removeItem(pItemName);
	}//setItem()
	
	
	/**
	 * Obtenir un item
	 * @param pItemName Nom de l'item à obtenir
	 * @return Un item
	 * @throws NonExistentItemException
	 */
	public Item getItem(final String pItemName) throws NonExistentItemException {
		return this.aItems.getItem(pItemName);
	}//getItem()
    
	
    public String getShortDescription() {
        return this.aDescription;
    }//getDescription()
    
    
    /**
     * Obtenir une longue description de la salle
     * @return Longue description de la salle
     */
    public String getLongDescription()
    {
    	StringBuilder vLongDescription = new StringBuilder();
    	
    	vLongDescription.append(this.aDescription + "\n");
    	vLongDescription.append(this.getItemList() + "\n");
    	vLongDescription.append(this.getCharacterInfo() + "\n");
    	vLongDescription.append(this.getExitString() + "\n");
    	
        return vLongDescription.toString();
    }//getLongDescription()
    
    
    /**
     * Obtenir la liste des items de la salle
     * @return Liste des items de la salle
     */
    public String getItemList()
    {
    	StringBuilder vItemList = new StringBuilder();
    	vItemList.append("Il y a " + this.aItems.length() + " objet(s) présent(s) dans cette salle.\n");
    	vItemList.append(this.aItems.getItemList() + "\n");
    	return vItemList.toString();
    }//getItemList()


	/**
	 * Obtenir le nom de l'image d'illustration de la salle
	 * @return Nom de l'image d'illustration de la salle
	 */
	public String getImageName() {
		return this.aImageName;
	}//getImageName()
	
	
	/**
	 * Savoir si la salle renseignée est une sortie de la salle
	 * @param pRoom Salle à tester
	 * @return true si la salle renseignée est une sortie, false sinon
	 */
	public boolean isExit(final Room pRoom) {
		if(this.aExits.containsValue(pRoom)) return true;
		else return false;
	}//isExit()
	
	
	/**
	 * Obtenir les informations d'un PNJ
	 * @return Informations d'un PNJ
	 */
	public String getCharacterInfo() {
		if(this.aCharacter == null) return "Il n'y a aucun PNJ dans cette salle.";
		else return "Il y a un PNJ. Il s'agit de " + this.aCharacter.getName() + ".\n" + aCharacter.getDescription();
	}//getCharacterInfo()
    
}//Room
