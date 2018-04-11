package pkg_Items;


/**
 * Un objet.
 * @author adham
 */
public class Item
{
	private String aName;
	private String aDescription;
	private int aWeight;
	private int aPrice;

	
	/**
	 * @param pName Nom de l'objet
	 * @param pDescription Description de l'objet
	 * @param pPrice Prix de l'objet
	 * @param pWeight Poids de l'objet
	 */
	public Item(final String pName, final String pDescription, final int pPrice, final int pWeight)
	{
		this.aName = pName;
		this.aDescription  = pDescription;
		this.aPrice = pPrice;
		this.aWeight = pWeight;
	}//Item()
	
	
	/**
	 * Obtenir le nom de l'objet
	 * @return Nom de l'objet
	 */
	public String getName() {
		return this.aName;
	}//getName()
	
	
	/**
	 * Obtenir la description de l'objet
	 * @return Description de l'objet
	 */
	public String getDescription() {
		return this.aDescription;
	}//getDescription()
	
	
	/**
	 * Obtenir le prix de l'objet
	 * @return Prix de l'objet
	 */
	public int getPrice() {
		return this.aPrice;
	}//getPrice()
	
	
	/**
	 * Obtenir le poids de l'objet
	 * @return Poids de l'objet
	 */
	public int getWeight() {
		return this.aWeight;
	}//getWeight()

	
	/**
	 * Obtenir une description courte de l'objet
	 * @return Description courte de l'objet
	 */
	public String getShortDescription() {
		return this.aName + " ~ " + this.aDescription;
	}//getShortDescription()
	
	
	/**
	 * Obtenir une longue description de l'objet
	 * @return Longue description de l'objet
	 */
	public String getLongDescription()
	{
		StringBuilder vLongDescription = new StringBuilder();
		
		vLongDescription.append("L'objet s'appelle " + this.aName + ". " + this.aDescription + "\n");
		vLongDescription.append("Cette objet coûte " + this.aPrice + " @Coins et pèse " + this.aWeight + " kgs.\n\n");
		
		return vLongDescription.toString();
	}//getLongDescription()
	
}//Item
