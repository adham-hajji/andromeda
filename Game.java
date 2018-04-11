import pkg_Engine.GameEngine;
import pkg_Engine.UserInterface;

public class Game
{
	private UserInterface aGui;
	private GameEngine aGameEngine;

	
	public Game()
	{
		this.aGameEngine = new GameEngine();
		this.aGui = new UserInterface(this.aGameEngine);
		this.aGameEngine.setGUI(this.aGui);
	}//Game()
	
	
	public static void main(final String[] args)
	{
		new Game();
	}//main()
	
}//Game