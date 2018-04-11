package pkg_Commands;


/**
 * Une commande entrée par l'utilisateur
 * @author adham
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    
    /**
     * @param pCommandWord Nom de la commande
     * @param pSecondWord Second mot de la commande
     */
    public Command (final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command()
    

    /**
     * Obtenir le nom de la commande
     * @return Nom de la commande
     */
    public String getCommandWord ()
    {
        return this.aCommandWord;
    }//getCommandWord()
    
    
    /**
     * Obtenir le second mot de la commande
     * @return Second mot de la commande
     */
    public String getSecondWord ()
    {
        return this.aSecondWord;
    }//getSecondWord()
    
    
    /**
     * Connaitre l'existence d'un second mot
     * @return Existence d'un second mot
     */
    public boolean hasSecondWord ()
    {
        return this.aSecondWord != null;
    }//hasSecondWord()
    
    
    /**
     * Connaitre l'existence de la commande
     * @return Existence de la commande
     */
    public boolean isUnknown ()
    {
        return this.aCommandWord == null;
    }//isUnknown()
    
}//Command
