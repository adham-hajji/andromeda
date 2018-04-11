package pkg_Items;


import java.util.HashMap;

import pkg_Exceptions.NonExistentItemException;


/**
 * @author adham
 */
/**
 * @author adham
 *
 */
public class ItemList {
	private HashMap<String, Item> aItems;
	
	
	public ItemList() {
		this.aItems = new HashMap<String, Item>();
	}//ItemList()
	
	
	/**
	 * Ajouter un item
	 * @param pItem Item à ajouter
	 */
	public void addItem(final Item pItem) {
		this.aItems.put(pItem.getName(), pItem);
	}//addItem()
	
	
	/**
	 * Ajouter un item
	 * @param pItemKey Clé de l'item
	 * @param pItem Item à ajouter
	 */
	public void addItem(final String pItemKey, final Item pItem) {
		this.aItems.put(pItemKey, pItem);
	}//addItem()
	
	
	/**
	 * Obtenir un item
	 * @param pItemKey Clé de l'item
	 * @return L'item
	 * @throws NonExistentItemException
	 */
	public Item getItem(final String pItemKey) throws NonExistentItemException
	{
		if(this.aItems.get(pItemKey) == null) throw new NonExistentItemException();
		return this.aItems.get(pItemKey);
	}//getItem()
	
	
	/**
	 * Supprimer un item
	 * @param pItemKey Clé de l'item
	 * @throws NonExistentItemException
	 */
	public void removeItem(final String pItemKey) throws NonExistentItemException
	{
		if(this.aItems.get(pItemKey) == null) throw new NonExistentItemException();
		this.aItems.remove(pItemKey);
	}//removeItem()
	
	
	/**
	 * Savoir si la liste est vide
	 * @return true si la liste est vide, false sinon
	 */
	public boolean isEmpty() {
		return this.aItems.isEmpty();
	}//isEmpty()
	
	
	/**
	 * Obtenir la taille de la liste
	 * @return Taille de la liste
	 */
	public int length() {
		return this.aItems.size();
	}//length()
	
	
	/**
	 * Obtenir la liste des item
	 * @return Liste des items
	 */
	public String getItemList()
	{
    	if(!this.aItems.isEmpty()) {
    		StringBuilder vItemList = new StringBuilder();
    		
    		for(String vItemName : this.aItems.keySet())
    			vItemList.append("¤ " + this.aItems.get(vItemName).getShortDescription() + "\n");
    		
    		return vItemList.toString();
    	}
    	
    	return "";
	}//getItemList()
	
	
	/**
	 * Savoir si la liste contient un item
	 * @param pItemName Nom de l'item en question
	 * @return true si l'item existe, faux sinon
	 */
	public boolean hasItemName(final String pItemName)
	{
		if(this.aItems.containsKey(pItemName)) return true;
		else return false;
	}//hasItemKey()
	
}//ItemList