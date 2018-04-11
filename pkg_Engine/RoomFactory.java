package pkg_Engine;


import java.util.HashMap;

import pkg_Characters.Character;
import pkg_Engine.Room;
import pkg_Items.BeamerItem;
import pkg_Items.Item;


/**
 * Constructeur de salles
 * @author adham
 */
public class RoomFactory
{
	private static HashMap<String, Room> sRooms;
	
	
	/**
	 * Construire les salles du jeu
	 * @return Une liste de salles
	 */
	public static HashMap<String, Room> build()
	{
		if(RoomFactory.sRooms == null) {
			RoomFactory.sRooms = RoomFactory.createRooms();
			
			RoomFactory.setExits(sRooms);
			RoomFactory.setItems(sRooms);
			RoomFactory.setCharacters(sRooms);
			RoomFactory.createDialogues(sRooms);
			
			return RoomFactory.sRooms;
		}
		else return RoomFactory.sRooms;
	}//build()
	
	
	/**
	 * Obtenir une salle du jeu
	 * @param pRoomName Nom de la salle
	 * @return Une salle du jeu
	 */
	public static Room get(final String pRoomName)
	{
		if(RoomFactory.sRooms == null) RoomFactory.build();
		return RoomFactory.sRooms.get(pRoomName);
	}//get()
	
	
	/**
	 * Créer les salles du jeu
	 * @return Une liste de salles du jeu
	 */
	private static HashMap<String, Room> createRooms()
	{
		HashMap<String, Room> vRoom = new HashMap<String, Room>();
		
	    vRoom.put("Ugada", new Room(
	    	"Ugada, capitale du Monde Libre et chef-lieu du Système d'Ugada",
	    	"ugada.jpg"
	    ));
	    vRoom.put("Esper", new Room(
	    	"Esper, chef-lieu du Système d'Esper",
	    	"esper.jpg"
	    ));
	    vRoom.put("Armor", new Room(
	    	"Armor, chef-lieu du Système d'Armor",
	    	"armor.jpg"
	    ));
	    vRoom.put("Pont de Confluence", new Room(
	    	"Le pont de Confluence est le seul lien direct qui existe entre le Monde libre et l'Empire des Zoulous Galactiques. Il a été finalisé très récemment par les Zoulous afin d'acheminer plus facilement des soldats et de la logistique.",
	    	"pont.jpg"
	    ));
	    vRoom.put("Isolation", new Room(
	    	"Isolation, chef-lieu du Système Isolé",
	    	"isolation.jpg"
	    ));
	    vRoom.put("Sùùdt", new Room(
	    	"Sùùdt, chef-lieu du Système Sudiste",
	    	"suudt.jpg"
	    ));
	    vRoom.put("Solaar", new Room(
	    	"Solaar, chef-lieu du Système de Solaar",
	    	"solaar.jpg"
	    ));
	    vRoom.put("Hageaxiaud", new Room(
	    	"Hageaxiaud, chef-lieu du Système de Korse",
	    	"hageaxiaud.jpg"
	    ));
	    vRoom.put("Nuf", new Room(
	    	"Nuf, chef-lieu du Système de Nuf",
	    	"nuf.png"
	    ));
	    vRoom.put("Bourg-ô-Ri$h", new Room(
	    	"Bourg-ô-Ri$h, capital de l'Empire et chef-lieu du Système Capital",
	    	"bourg-o-rish.jpg"
	    ));
	    vRoom.put("Maunay", new Room(
	    	"Maunay, chef-lieu du Système de Maunay",
	    	"maunay.jpg"
	    ));
	    vRoom.put("Militaria", new Room(
	    	"Militaria, chef-lieu du QG Militaire",
	    	"militaria.jpg"
	    ));
	    vRoom.put("Droupeau", new Room(
	    	"Droupeau, chef-lieu du Système de Droupeau",
	    	"droupeau.jpg"
	    ));
	    vRoom.put("Takse", new Room(
	    	"Takse, chef-lieu du Système de Taksation",
	    	"takse.jpg"
	    ));
	    vRoom.put("Brrrorr", new Room(
	    	"Brrrorr, chef-lieu du Système de Brrrorr",
	    	"brrrorr.jpg"
	    ));
	    
	    return vRoom;
	}//createRooms()
	
	
	/**
	 * Définir les sorties
	 * @param pRooms Liste de salles du jeeu
	 */
	private static void setExits(final HashMap<String, Room> pRooms)
	{
		// Sorties d'Ugada
	        pRooms.get("Ugada").setExit("nord-ouest", pRooms.get("Solaar"));
	        pRooms.get("Ugada").setExit("nord-est", pRooms.get("Esper"));
	        pRooms.get("Ugada").setExit("sud-est", pRooms.get("Isolation"));
	        pRooms.get("Ugada").setExit("sud", pRooms.get("Sùùdt"));

        // Sorties d'Esper
        	pRooms.get("Esper").setExit("sud-ouest", pRooms.get("Ugada"));
        	pRooms.get("Esper").setExit("est", pRooms.get("Armor"));
        
        // Sorties d'Armor
            pRooms.get("Armor").setExit("ouest", pRooms.get("Esper"));
            pRooms.get("Armor").setExit("est", pRooms.get("Pont de Confluence"));	
        
        // Sorties du Pont de Confluence
            pRooms.get("Pont de Confluence").setExit("ouest", pRooms.get("Armor"));
            pRooms.get("Pont de Confluence").setExit("est", pRooms.get("Nuf"));
        
        // Sorties d'Isolation
            pRooms.get("Isolation").setExit("nord-ouest", pRooms.get("Ugada"));
        
        // Sorties de Suudt
            pRooms.get("Sùùdt").setExit("nord", pRooms.get("Ugada"));
            pRooms.get("Sùùdt").setExit("sud", pRooms.get("Hageaxiaud"));
        
        // Sorties de Solaar
            pRooms.get("Solaar").setExit("sud-est", pRooms.get("Ugada"));
        
        // Sorties d'Hageaxiaud
	        pRooms.get("Hageaxiaud").setExit("nord", pRooms.get("Sùùdt"));
	        
	    // Sorties de Nuf
	        pRooms.get("Nuf").setExit("nord-est", pRooms.get("Brrrorr"));
	        pRooms.get("Nuf").setExit("sud", pRooms.get("Takse"));
	        pRooms.get("Nuf").setExit("est", pRooms.get("Droupeau"));
	        pRooms.get("Nuf").setExit("ouest", pRooms.get("Armor"));
	        
	    // Sorties de Bourg o Rish
	        pRooms.get("Bourg-ô-Ri$h").setExit("nord", pRooms.get("Brrrorr"));
	        pRooms.get("Bourg-ô-Ri$h").setExit("sud", pRooms.get("Maunay"));
	        pRooms.get("Bourg-ô-Ri$h").setExit("ouest", pRooms.get("Droupeau"));
	    
	    // Sorties de Maunay
	    // ** Il s'agit d'une trapdoor car elle ne permet pas d'accéder à Bourg o Rish par le nord !
	        pRooms.get("Maunay").setExit("nord-ouest", pRooms.get("Takse"));
	        
	    // Sorties de Militaria
	        pRooms.get("Militaria").setExit("sud", pRooms.get("Droupeau"));
	        
	    // Sorties de Droupeau
	        pRooms.get("Droupeau").setExit("nord", pRooms.get("Militaria"));
	        pRooms.get("Droupeau").setExit("ouest", pRooms.get("Armor"));
	        pRooms.get("Droupeau").setExit("est", pRooms.get("Bourg-ô-Ri$h"));
	        
	    // Sorties de Takse
	        pRooms.get("Takse").setExit("nord", pRooms.get("Nuf"));
	        pRooms.get("Takse").setExit("sud-est", pRooms.get("Maunay"));
	        
	    // Sorties de Brrrorr
	        pRooms.get("Brrrorr").setExit("sud", pRooms.get("Bourg-ô-Ri$h"));
	        pRooms.get("Brrrorr").setExit("sud-ouest", pRooms.get("Nuf"));
	        
	}//setExits()
	
	
	/**
	 * Attribuer les items
	 * @param pRooms Liste de salles du jeu
	 */
	private static void setItems(final HashMap<String, Room> pRooms)
	{
		// Définition des différents items du jeu
			Item vEpeeDamocles = new Item("Épée", "Une épée redoutable", 100, 5);
			Item vCarburant = new Item("Carburant", "Un bidon d'essence qui vous permettra de voyager", 20, 2);
			Item vCookieMagique = new Item("Cookie", "Un cookie magique qui te vous permet d'augmenter vos capacités", 0, 0);
			Item vBeamer = new BeamerItem();
			Item vPasseport = new Item("Passeport", "Un papier vous permettant de pouvoir accéder à l'Empire des Zoulous Galactiques", 100, 0);
		
		// Attribution des items aux différentes planètes du jeu
	        pRooms.get("Ugada").setItem("epee", vEpeeDamocles);
	        pRooms.get("Ugada").setItem("carburant", vCarburant);
	        pRooms.get("Pont de Confluence").setItem("passeport", vPasseport);
	        pRooms.get("Nuf").setItem("cookie", vCookieMagique);
	        pRooms.get("Militaria").setItem("beamer", vBeamer);
	        
	}//setItems()
	
	
	/**
	 * Attribuer les personnages
	 * @param pRooms Liste de salles du jeu
	 */
	private static void setCharacters(final HashMap<String, Room> pRooms)
	{
		Character vPolicier = new Character(
			"Policier",
			"Un policier"
		);
		
		Character vCodou = new Character(
			"Codou Chaka Ier",
			"Codou Chaka est connu pour être un empereur impitoyable et avide de profits. Sa fortune personnelle excède les quadrilliards d'@coins ; de plus, il est le propriétaire de la quasi totalité des entreprises de la galaxie. Son pouvoir est tel qu'il contrôle d'une main de fer la large constellation des Zoulous Galactiques et compte étendre son influence au sein même du Monde libre."
		);
		
		pRooms.get("Nuf").setCharacter(vPolicier);
		pRooms.get("Bourg-ô-Ri$h").setCharacter(vCodou);
	}//createCharacters()
	
	
	/**
	 * Créer les dialogues
	 * @param pRooms Liste de salles du jeu
	 */
	private static void createDialogues(final HashMap<String, Room> pRooms)
	{
		// Dialogues du policier
			pRooms.get("Nuf").getCharacter().addDialogue("Salut petit ! Je suis le plus grand policier de Nuf. J'ai pour mission de surveiller le pont de Confluencia que tu viens de traverser.");
			pRooms.get("Nuf").getCharacter().addDialogue("Circule, il y a rien à voir ici !");
		
		// Dialogues de Codou
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Horreur, un Ugadien sur mes terres ! Que l'on m'abatte cette vermine sur le champ !");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Il arrive toujours à parler ?! Que l'on m'exécute ce monstre...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Tais-toi bon sang !!");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Arrête de parler, mes tympans ne supporteront pas longtemps !");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Mais... arr-");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Argh.");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Tu sais...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("... il y a une chose que je dois t'annoncer...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("c'est que...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("ce jeu n'étant pas achevé, il n'y aucune façon de le gagner. :P");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Tu t'obstines à me parler ?! Mais pourquoi donc ?");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Ca ne sert absolument à rien de continuer...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("1...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("2...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("3...");
			pRooms.get("Bourg-ô-Ri$h").getCharacter().addDialogue("Très bien. C'est bon j'ai compris... tu as gagné ! Bravo.");
	}//createDialogues()
	
}//RoomFactory
