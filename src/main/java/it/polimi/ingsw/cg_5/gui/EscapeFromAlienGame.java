package it.polimi.ingsw.cg_5.gui;
import it.polimi.ingsw.cg_5.model.Alien;
import it.polimi.ingsw.cg_5.view.ViewController;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**Main screan of the game. It cointains all the needed panels and prints the right map.
 * @author Andrea
 *
 */
public class EscapeFromAlienGame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MessagePanel getMessagePanel() {
		return messagePanel;
	}




	private static final int WINDOW_WIDTH = 1100;
	private static final int WINDOW_HEIGHT = 713;
	
	private Image mapImage;
	private Image circleImage;
	private Image alienImage;
	private Image humanImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	private ViewController viewController;

	private ButtonPanel buttonPanel = new ButtonPanel();
	private MessagePanel messagePanel= new MessagePanel();

    private DtoPanel dtoPanel= new DtoPanel();
    private LogMessage logPanel = new LogMessage();
	private JLabel image ;
	private JLabel piece ;
	private PlayerCardPanel playerCardPanel= new PlayerCardPanel();
	
	

	
        
	
	




	

	public EscapeFromAlienGame(ViewController viewController)  {

		
		this.viewController=viewController;
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT + getInsets().top);
		this.setBackground(Color.BLACK);
		//title of the window 

		setTitle("EscapeFromAlien_Game");
		

		//we don't want to let the user to resize the windows
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
		loadResources(viewController);
		initComponents();
		
		this.image = new JLabel(new ImageIcon(circleImage));
		
		 this.getLayeredPane().setLayer(image, 10000);
		 add(image);
		 image.setVisible(true);
		 image.setBounds(100, 100, 60, 60);
		 
		 this.piece = new JLabel(new ImageIcon(humanImage));
			
		 this.getLayeredPane().setLayer(piece, 10000);
		 add(piece);
		 piece.setVisible(true);
		 piece.setBounds(367, 262, 60, 60);
		
	}
	
	/**Prints the circle that identifies the selection of a sector, given the coordinates.
	 * @param x
	 * @param y
	 */
	public void printCircle(int x, int y) {
		this.image.setBounds(x, y, 60, 60);
	 
	}
	/**Given the coordinates, it prints on the screan the piece on the current sector.
	 * @param x
	 * @param y
	 */
	public void printPiece(int x, int y) {
		
		
		if(this.viewController.getView().getCharacter()!=null){
		this.piece.setBounds(x, y, 60, 60);
		if(this.viewController.getView().getCharacter().getClass()== Alien.class)
			piece.setIcon(new ImageIcon(alienImage));
		}
	 
	}
	

	/**Used to upload all the images that will be used on this frame.
	 * @param viewController
	 */
	private void loadResources(ViewController viewController) {
		try {

			circleImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/selected.png"));
			alienImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/Outer_Space_Alien_32.png"));
			humanImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/running37.png"));
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("GALILEI"));
			mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galilei.png"));
			
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("FERMI"))
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/fermi.png"));
		
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("GALVANI")){
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galvani.png"));
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		

	}
	
	public ViewController getViewController() {
		return viewController;
	}

	
	/**Initialization of all the components concenrning this jFrame
	 * 
	 */
	private void initComponents() {
	
		layeredPane = new JLayeredPane();
		setContentPane(layeredPane);		
		backgroundLabel = new JLabel(new ImageIcon(mapImage));
		backgroundLabel.setBounds(0,0,801, 591);
		add(backgroundLabel);
		layeredPane.setLayer(backgroundLabel, 0);
			
		/*************************START COMMAND PANEL********************************/			
			
		layeredPane.setLayer(buttonPanel, 10);
		Mouse mouse = new Mouse(this);
		this.addMouseListener(mouse);

		/****************************ADDING LISTENER*********************************/
		
		buttonPanel.getEndTurn().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ENDTURN",""));
		buttonPanel.getAttackButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ATTACK",""));
		buttonPanel.getDrawCard().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DRAW",""));
		buttonPanel.getUseCardButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"USECARD",""));
		buttonPanel.getDiscard().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DISCARD",""));	
		messagePanel.getSendMessage().addActionListener(new MessageListener(this.viewController,messagePanel));
		add(buttonPanel);

		/***************************END COMMAND PANEL*******************************/
			
		layeredPane.setLayer(logPanel, 10);
			
		/*******************************DTO PANEL***********************************/
	
		layeredPane.setLayer(dtoPanel, 10);
		add(dtoPanel);
		add(logPanel);
		add(messagePanel);
		layeredPane.setLayer(messagePanel, 10);
		messagePanel.setBounds(0, 591, 801, 90);
		add(playerCardPanel);
		layeredPane.setLayer(playerCardPanel, 10);
		
	}


	public LogMessage getLogPanel() {
		return logPanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}
	public PlayerCardPanel getPlayerCardPanel() {
		return playerCardPanel;
	}

	public DtoPanel getDtoPanel() {
		return dtoPanel;
	}
	
}
