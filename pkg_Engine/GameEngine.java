package pkg_Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import pkg_Characters.Character;
import pkg_Characters.Player;
import pkg_Commands.Command;
import pkg_Commands.CommandWords;
import pkg_Commands.Parser;
import pkg_Exceptions.NonExistentBeamedRoomException;
import pkg_Exceptions.NonExistentExitException;
import pkg_Exceptions.NonExistentItemException;
import pkg_Exceptions.NonValidCommandException;
import pkg_Exceptions.NotHavingBeamerException;
import pkg_Exceptions.TooHeavyItemException;


/**
 * Moteur de jeu.
 * @author adham
 */
public class GameEngine
{
    private HashMap<String, Room> aRooms;
	
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private int aCommandCount;
    private boolean aGameOver;
    
    
    public GameEngine()
    {
        this.aRooms = RoomFactory.build();
        this.aParser = new Parser();
        this.aCommandCount = 0;
        this.aGameOver = false;
        this.createPlayer();
    }//GameEngine()
    
    
    /**
     * Construire l'interface utilisateur
     * @param pGui Objet d'interface utilisateur
     */
    public void setGUI(UserInterface pGui)
    {
    	this.aGui = pGui;
    	this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
    	this.printWelcome();
    }//setGUI()
    
    
    /**
     * Créer le joueur
     */
    private void createPlayer()
    {
    	 // On crée le personnage principal du jeu
	        this.aPlayer = new Player(
				"Buda Buda",
		        "Buba Buda est encore jeune, il n'a que 200 ans ! Jeune pilote tout juste diplômé de l'ESIEE, il travaille au Département de Recherche de Minerais de l'entreprise Ugada Tech. Impulsif, il s'engagera à l'Armée Libre lorsque les Zoulous Galactiques envahiront le Système d'Esper.",
		        this.aRooms.get("Ugada"),
		        3
		    );
	        
	    // On lui attribue des dialogues
	        this.aPlayer.addDialogue("Mais qu'est ce que je fous là...");
	        this.aPlayer.addDialogue("Hé ho ! Y a-t-il quelqu'un ici ?");
    }//createPlayer()
    
    
    /**
     * Afficher un message de bienvenue
     */
    private void printWelcome()
    {
        this.aGui.println("Bienvenue sur Andromeda !");
        this.aGui.println("Andromeda est un jeu d'exploration spatial où vous incarnez un jeune pilote.");
        this.aGui.println("Tapez '" + CommandWords.HELP + "' si vous avez besoin d'aide.\n");
        this.printLocationInfo();
    }//printWelcome()
    
    
    /**
     * Afficher les informations de la salle courante
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription() + "\n");
    }//printLocationInfo()
    
    
    /**
     * Aller à une salle
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NonExistentExitException
     */
    private void go(final Command pCommand) throws NonValidCommandException, NonExistentExitException
    {
    	if(!pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
        String vDirection = pCommand.getSecondWord();
    	Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
    	
        if(vNextRoom == null) throw new NonExistentExitException();
    
    	this.aPlayer.goRoom(vNextRoom);
        this.printLocationInfo();
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
    }//go()
    
    
    /**
     * Afficher de l'aide
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     */
    private void help(final Command pCommand) throws NonValidCommandException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
        this.aGui.println("Vous êtes perdus aux confins de l'espace.");
        this.aGui.println("Heureusement, une intelligence artificielle vous vient en aide.\n");
        this.printLocationInfo();
        this.aGui.println("Les commandes disponibles sont : " + this.aParser.getCommandList());
    }//help()
    
    
    /**
     * Quitter le jeu
     */
    private void quit()
    {
    	this.aGui.println("Merci d'avoir joué à Andromeda ! Au revoir.\n");
    	this.aGui.enable(false);
    	this.aGameOver = true;
    }//quit()
    
    
    /**
     * Quitter le jeu
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     */
    private void quit(final Command pCommand) throws NonValidCommandException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	this.quit();
    }//quit()
    
    
    /**
     * Manger
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NonExistentItemException
     */
    private void eat(final Command pCommand) throws NonValidCommandException, NonExistentItemException
    {
    	if(pCommand.hasSecondWord()) {
    		if(pCommand.getSecondWord().equals("cookie")) {
    			this.aPlayer.setMaximumWeight(5);
    			this.aPlayer.removeItem("cookie");
    			this.aGui.println("Vous avez mangé un magnifique cookie ugadien vous permettant de porter des objets plus lourds !");
        		this.aGui.println("Quelle classe ! ☺\n");
    		}
    		else throw new NonValidCommandException();
    	}
    	else throw new NonValidCommandException();
    }//eat()
    
    
    /**
     * Regarder autour de soit
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     */
    private void look(final Command pCommand) throws NonValidCommandException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	this.printLocationInfo();
    }//look()
    
    
    /**
     * Aller en arrière
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NonExistentExitException
     */
    private void back(final Command pCommand) throws NonValidCommandException, NonExistentExitException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
	
    	this.aPlayer.goBack();
        this.printLocationInfo();
        this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
    }//back()
    
 
    /**
     * Tester le jeu grâce aux fichiers de test
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws FileNotFoundException
     */
    private void test(final Command pCommand) throws NonValidCommandException, FileNotFoundException
    {
    	if(!pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
    	Path vTestFile = Paths.get("tests/test-" + pCommand.getSecondWord() + ".txt");
    	Scanner vSc;
    	
    	if(Files.notExists(vTestFile))
    		throw new FileNotFoundException("Le fichier n'a pas été trouvé !\n");
    	
		vSc = new Scanner(new File(vTestFile.toString()));
		
		while(vSc.hasNextLine())
			interpretCommand(vSc.nextLine());
		
		vSc.close();
    }//test()
    
    
    /**
     * Prendre un objet
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NonExistentItemException
     * @throws TooHeavyItemException
     */
    private void take(final Command pCommand)
    		throws NonValidCommandException, NonExistentItemException, TooHeavyItemException
    {
    	if(!pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
		String vItemName = pCommand.getSecondWord();
		String vItemDescription = this.aPlayer.takeItem(vItemName);
		this.aGui.println("Vous avez acquis un nouvel objet !");
		this.aGui.println("+ " + vItemDescription);
    }//take()
    
    
    /**
     * Lacher un objet
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NonExistentItemException
     */
    private void drop(final Command pCommand) throws NonValidCommandException, NonExistentItemException
    {
    	if(!pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
    	String vItemName = pCommand.getSecondWord();
    	String vItemDescription = this.aPlayer.dropItem(vItemName);
    	this.aGui.println("Vous avez jeté un objet :");
    	this.aGui.println("- " + vItemDescription);
    }//drop()
    
    
    /**
     * Consulter l'inventaire
     * @param pCommand Commande entrée par l'utilisateur
     */
    private void inventory(final Command pCommand) {
    	this.aGui.println(this.aPlayer.getItemList());
    }//inventory()
    
    
    /**
     * Charger le beamer
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NotHavingBeamerException
     */
    private void charge(final Command pCommand) throws NonValidCommandException, NotHavingBeamerException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	this.aPlayer.chargeBeamer();
    	this.aGui.println("Vous avez enregistré les coordonnées spatiales de la planète grâce à votre beamer !");
    	this.aGui.println("Pour vous téléporter ensuite vers cette planète, utilisez la commande '" + CommandWords.FIRE + "'.\n" );
    }//charge()
    
    
    /**
     * Actionner le beamer
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws NotHavingBeamerException
     * @throws NonExistentBeamedRoomException
     */
    private void fire(final Command pCommand)
    		throws NonValidCommandException, NotHavingBeamerException, NonExistentBeamedRoomException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	this.aPlayer.fireBeamer();
    	this.aGui.println("Vous vous êtes téléporté avec succès !");
    	this.printLocationInfo();
    }//fire()
    
    
    /**
     * Parler à un personnage
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     * @throws Exception
     */
    private void talk(final Command pCommand) throws NonValidCommandException, Exception
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	
    	Character vCharacter = this.aPlayer.getCurrentRoom().getCharacter();

    	this.aGui.println(
			((vCharacter == null) ? this.aPlayer.getNextDialogue() : vCharacter.getNextDialogue())
			+ "\n"
    	);
    	
    	if(this.aRooms.get("Bourg-ô-Ri$h").getCharacter().getDialogueLine() > 15)
    		this.quit();
    }//talk()
    
    
    /**
     * Afficher des informations concernant le joueur
     * @param pCommand Commande entrée par l'utilisateur
     * @throws NonValidCommandException
     */
    private void me(final Command pCommand) throws NonValidCommandException
    {
    	if(pCommand.hasSecondWord()) throw new NonValidCommandException();
    	this.aGui.println("Vous vous appellez " + this.aPlayer.getName());
    	this.aGui.println("L'ESIEE a rédigé un rapport vous concernant :\n" + this.aPlayer.getDescription() + "\n");
    }//me()
    
    
    /**
     * Gestion du Game Over
     */
    private void gameOverManager()
    {
    	if(this.aCommandCount++ >= 100) {
    		this.aGui.println("Malheureusement... l'Empire des Zoulous Galactiques a réussi son assaut contre la planète Ugada.");
    		this.aGui.println("Le monde libre, dernier bastion rebel d'Andromeda, s'est effondré. Ainsi, un nouvel ordre a émergé et règne en maître dans toute la galaxie.");
    		this.aGui.println("Votre mission s'achève ici... Game Over.\n");
    		this.quit();
    	}
    }//gameOverManager()
    
    
    /**
     * Interprétation des commandes utilisateurs
     * @param pCmdLine Ligne entrée par l'utilisateur
     */
    public void interpretCommand(String pCmdLine) 
    {
    	this.gameOverManager();
    	
    	if(!this.aGameOver) {
	        this.aGui.println(pCmdLine + "\n");
	        
	        Command vCommand = this.aParser.getCommand(pCmdLine);
	        String vCommandWord = vCommand.getCommandWord();
	        
	        try {
	        	if(vCommand.isUnknown()) throw new NonValidCommandException();
		        switch(vCommandWord) {
		        	case CommandWords.HELP: this.help(vCommand); break;
		        	case CommandWords.GO: this.go(vCommand); break;
		        	case CommandWords.QUIT: this.quit(vCommand); break;
		        	case CommandWords.EAT: this.eat(vCommand); break;
		        	case CommandWords.LOOK: this.look(vCommand); break;
		        	case CommandWords.BACK: this.back(vCommand); break;
		        	case CommandWords.TEST: this.test(vCommand); break;
		        	case CommandWords.TAKE: this.take(vCommand); break;
		        	case CommandWords.DROP: this.drop(vCommand); break;
		        	case CommandWords.INVENTORY: this.inventory(vCommand); break;
		        	case CommandWords.CHARGE: this.charge(vCommand); break;
		        	case CommandWords.FIRE: this.fire(vCommand); break;
		        	case CommandWords.TALK: this.talk(vCommand); break;
		        	case CommandWords.ME: this.me(vCommand); break;
		        }
	        }
	        catch(final Exception pException) {
	        	this.aGui.println(pException.getMessage());
	        }//catch
    	}//if
    }//interpretCommand()
    
}//GameEngine
