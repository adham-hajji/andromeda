package pkg_Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pkg_Commands.CommandWords;


/**
 * Interface utilisateur
 * @author adham
 */
public class UserInterface implements ActionListener
{
	// Dimensions de l'interface
	    final static private int WIDTH = 800;
	    final static private int HEIGHT = 300;
	
    final private Font aFont = new Font("Century Gothic", Font.PLAIN, 12);
	
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private Vector<JButton> aButtons;
    
    
    /**
     * @param pGameEngine Moteur du jeu
     */
    public UserInterface(final GameEngine pGameEngine)
    {
        this.aEngine  = pGameEngine;
        this.aButtons = new Vector<JButton>();
        this.createGUI();
    } // UserInterface()

    
    /**
     * Afficher du texte
     * @param pText Texte à afficher
     */
    public void print(final String pText)
    {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    }//print()

    
    /**
     * Afficher une ligne de texte
     * @param pText Texte à afficher
     */
    public void println(final String pText)
    {
        this.print(pText + "\n");
    }//println()

    
    /**
     * Afficher une image
     * @param pImageName Nom de l'image à afficher
     */
    public void showImage(final String pImageName)
    {
        Path vImagePath = Paths.get("images/" + pImageName);

        if (Files.notExists(vImagePath)) System.out.println("Image non trouvée.");
        else {
        	Image vScaledImage = new ImageIcon(vImagePath.toString()).getImage().getScaledInstance(UserInterface.WIDTH, -1, Image.SCALE_SMOOTH);
            ImageIcon vIcon = new ImageIcon(vScaledImage);
            this.aImage.setIcon(vIcon);
            this.aMyFrame.pack();
        }
    }
    
    
    /**
     * Modifier le statut de la barre d'entrée utilisateur
     * @param pState Statut de la barre d'entrée
     */
    public void enable(final boolean pState)
    {
        this.aEntryField.setEditable(pState);
        if (!pState) this.aEntryField.getCaret().setBlinkRate(0);
    }//enable()

    
    /**
     * Créer l'interface utilisateur
     */
    private void createGUI()
    {
        this.aImage = new JLabel();       
        
        // Création de la fenêtre
	        this.aMyFrame = new JFrame("Andromeda");   
	        this.aMyFrame.setSize(new Dimension(UserInterface.WIDTH, -1));
	        this.aMyFrame.setResizable(false);
	        this.aMyFrame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            	System.exit(0);
	            }
	        });
	        
        // Création de la sortie textuelle (scrollable)
	        this.aLog = new JTextArea();
	        this.aLog.setEditable(false);
	        this.aLog.setFont(this.aFont);
	        this.aLog.setBackground(Color.BLACK);
	        this.aLog.setForeground(Color.ORANGE);
	        JScrollPane vListScroller = new JScrollPane(this.aLog);
	        vListScroller.setPreferredSize(new Dimension(UserInterface.WIDTH, UserInterface.HEIGHT));
        
        // Création de la barre d'entrée
	        this.aEntryField = new JTextField();
	        this.aEntryField.setBackground(Color.BLACK);
	        this.aEntryField.setForeground(Color.ORANGE);
	        this.aEntryField.setFont(this.aFont);
	        this.aEntryField.addActionListener(this);
	        this.aEntryField.requestFocus();
        
        // Création des boutons
	        this.aButtons.add(new JButton(CommandWords.HELP));
	        this.aButtons.add(new JButton(CommandWords.EAT));
	        this.aButtons.add(new JButton(CommandWords.LOOK));
	        this.aButtons.add(new JButton(CommandWords.QUIT));
	        this.aButtons.add(new JButton(CommandWords.BACK));
	        this.aButtons.add(new JButton(CommandWords.TAKE));
	        this.aButtons.add(new JButton(CommandWords.DROP));
	        this.aButtons.add(new JButton(CommandWords.INVENTORY));
	        this.aButtons.add(new JButton(CommandWords.CHARGE));
	        this.aButtons.add(new JButton(CommandWords.FIRE));
	        this.aButtons.add(new JButton(CommandWords.TALK));
	        this.aButtons.add(new JButton(CommandWords.ME));
	        
	        for(JButton vButton : this.aButtons) {
	            vButton.setBackground(Color.ORANGE);
	            vButton.setForeground(Color.BLACK);
	            vButton.setFont(this.aFont);
	            vButton.addActionListener(this);
	        }
        
        // Création d'un menu
	        JPanel vMenu = new JPanel();
	        vMenu.setLayout(new BoxLayout(vMenu, BoxLayout.Y_AXIS));
	        vMenu.setBackground(Color.BLACK);
	        for(JButton vButton : this.aButtons) vMenu.add(vButton);

        // Création d'un layout de fenêtre
	        JPanel vPanel = new JPanel();
	        vPanel.setLayout(new BorderLayout());
	        vPanel.setBackground(Color.BLACK);
	        vPanel.add(this.aImage, BorderLayout.PAGE_START);
	        vPanel.add(vListScroller, BorderLayout.CENTER);
	        vPanel.add(this.aEntryField, BorderLayout.PAGE_END);
	        vPanel.add(vMenu, BorderLayout.LINE_END);

        // 
	        this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);
	        this.aMyFrame.pack();
	        this.aMyFrame.setVisible(true);
    }//createGUI()

    
    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(final ActionEvent pEvent) 
    {
    	if(pEvent.getSource() == this.aEntryField)
    		this.processCommand();
    	else switch(pEvent.getActionCommand()) {
			case CommandWords.HELP: this.aEngine.interpretCommand(CommandWords.HELP); break;
			case CommandWords.EAT: this.aEngine.interpretCommand(CommandWords.EAT); break;
			case CommandWords.LOOK: this.aEngine.interpretCommand(CommandWords.LOOK); break;
			case CommandWords.QUIT: this.aEngine.interpretCommand(CommandWords.QUIT); break;
			case CommandWords.BACK: this.aEngine.interpretCommand(CommandWords.BACK); break;
			case CommandWords.TAKE: this.aEngine.interpretCommand(CommandWords.TAKE); break;
			case CommandWords.DROP: this.aEngine.interpretCommand(CommandWords.DROP); break;
			case CommandWords.INVENTORY: this.aEngine.interpretCommand(CommandWords.INVENTORY); break;
			case CommandWords.CHARGE: this.aEngine.interpretCommand(CommandWords.CHARGE); break;
			case CommandWords.FIRE: this.aEngine.interpretCommand(CommandWords.FIRE); break;
			case CommandWords.TALK: this.aEngine.interpretCommand(CommandWords.TALK); break;
			case CommandWords.ME: this.aEngine.interpretCommand(CommandWords.ME); break;
		}
    }//actionPerformed()

    
    /**
     * Traiter une commande
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText("");
        this.aEngine.interpretCommand(vInput);
    }//processCommand()
    
} // UserInterface 
