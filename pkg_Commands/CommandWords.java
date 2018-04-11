package pkg_Commands;


/**
 * Commandes valables dans le jeu.
 * @author adham
 */
public class CommandWords {
	public static final String GO = "aller";
	public static final String QUIT = "quitter";
	public static final String HELP = "aide";
	public static final String LOOK = "voir";
	public static final String EAT = "manger";
	public static final String BACK = "retour";
	public static final String TEST = "tester";
	public static final String TAKE = "prendre";
	public static final String DROP = "lacher";
	public static final String INVENTORY = "inventaire";
	public static final String CHARGE = "charger";
	public static final String FIRE = "tirer";
	public static final String TALK = "parler";
	public static final String ME = "moi";
	
	
    /**
     * Liste des commandes valides
     */
    private static final String sValidCommands[] = {
        CommandWords.GO, CommandWords.QUIT, CommandWords.HELP,
        CommandWords.LOOK, CommandWords.EAT, CommandWords.BACK,
        CommandWords.TEST, CommandWords.TAKE, CommandWords.DROP,
        CommandWords.INVENTORY, CommandWords.CHARGE, CommandWords.FIRE,
        CommandWords.TALK, CommandWords.ME
    };
    
    
    /**
     * Connaître l'existence de la commande
     * @param pCommand Nom de la commande
     * @return Existence de la commande
     */
    public boolean isCommand(final String pCommand)
    {
        for(String vValidCommand : CommandWords.sValidCommands)
        	if(vValidCommand.equals(pCommand)) return true;
        
    	return false;
    }//isCommand()
    

    /**
     * Obtenir la liste des commandes disponibles
     * @return Liste des commandes disponibles
     */
    public String getCommandList()
    {
    	StringBuilder vCommandList = new StringBuilder();
    	
        for(String vValidCommand : CommandWords.sValidCommands)
        	vCommandList.append(vValidCommand + " ");
        
        return vCommandList.toString();
    }//getCommandList()
    
}//CommandWords