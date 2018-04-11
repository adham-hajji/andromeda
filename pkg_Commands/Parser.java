package pkg_Commands;


import java.util.StringTokenizer;


/**
 * Parser de commandes.
 * @author adham
 */
public class Parser 
{
    private CommandWords aCommandWords;

    
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    }//Parser()

    
    /**
     * Convertir la ligne entrée en une commande exploitable.
     * @param pInputLine Ligne entrée par l'utilisateur
     * @return Commande entrée par l'utilisateur
     */
    public Command getCommand(final String pInputLine) 
    {
    	StringTokenizer tokenizer = new StringTokenizer(pInputLine);
        String vWord1;
        String vWord2;

        // On attribue à chaque variable le premier et second mot de la commande
	        if(tokenizer.hasMoreTokens())
	        	vWord1 = tokenizer.nextToken();
	        else vWord1 = null;
	
	        if(tokenizer.hasMoreTokens())
	            vWord2 = tokenizer.nextToken();
	        else vWord2 = null;

        // On teste si la commande est valide puis on agit en conséquence
	        if(this.aCommandWords.isCommand(vWord1))
	        	return new Command(vWord1, vWord2);
	        else return new Command(null, vWord2);
	
    }//getCommand()

    
    /**
     * Obtenir la liste des commandes disponibles.
     * @return Liste des commandes disponibles.
     */
    public String getCommandList()
    {
        return this.aCommandWords.getCommandList();
    }//getCommandList()

} // Parser
