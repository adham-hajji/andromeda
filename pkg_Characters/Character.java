package pkg_Characters;


import java.util.ArrayList;

import pkg_Exceptions.NotValidStringException;


/**
 * Un personnage non jouable du jeu.
 * @author Adham Hajji
 */
public class Character
{
	private String aName;
	private String aDescription;
	private ArrayList<String> aDialogues;
	private int aDialogueLine;
	
	
	/**
	 * 
	 * @param pName Nom du PNJ
	 * @param pDescription Description du PNJ
	 * @param pDialogues Une liste de phrases prononçables par le PNJ
	 */
	public Character(final String pName, final String pDescription, final ArrayList<String> pDialogues)
	{	
		this.aName = pName;
		this.aDescription = pDescription;
		this.aDialogues = pDialogues;
		this.aDialogueLine = 0;
	}//Character()
	
	
	/**
	 * 
	 * @param pName Nom du PNJ
	 * @param pDescription Description du PNJ
	 */
	public Character(final String pName, final String pDescription) {
		this(pName, pDescription, new ArrayList<String>());
	}//Character()
	
	
	/**
	 * Définir le nom du PNJ
	 * @param pName Nom du PNJ
	 * @throws NotValidStringException
	 */
	public void setName(final String pName) throws NotValidStringException {
		this.aName = pName;
	}//setName()
	
	
	/**
	 * Définir la description du PNJ
	 * @param pDescription Description du PNJ
	 */
	public void setDescription(final String pDescription) {
		this.aDescription = pDescription;
	}//setDescription()
	
	
	/**
	 * Obtenir le nom du PNJ
	 * @return Nom du PNJ
	 */
	public String getName() {
		return this.aName;
	}//getName()
	
	
	/**
	 * Obtenir la description du PNJ
	 * @return Description du PNJ
	 */
	public String getDescription() {
		return this.aDescription;
	}//getDescription()
	
	
	/**
	 * Ajouter un dialogue
	 * @param pDialogue Dialogue à ajouter
	 */
	public void addDialogue(final String pDialogue) {
		this.aDialogues.add(pDialogue);
	}//addDialogue()
	
	
	/**
	 * Obtenir le prochain dialogue
	 * @return Prochain dialogue
	 */
	public String getNextDialogue()
	{
		int vDialoguesSize = this.aDialogues.size();
		
		if(this.aDialogueLine < vDialoguesSize) {
			String vDialogue = this.aDialogues.get(this.aDialogueLine);
			this.aDialogueLine++;
			return vDialogue;
		}
		else return this.aDialogues.get(vDialoguesSize - 1);
	}//getNextDialogue()
	
	
	/**
	 * Obtenir la ligne du dialogue
	 * @return Ligne du dialogue
	 */
	public int getDialogueLine() {
		return this.aDialogueLine;
	}//getDialogueLine()
	
}//Character