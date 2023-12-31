package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**Scren of the starting game. It allowds to insert username and the favourite game configurations.
 * @author Andrea
 *
 */
public class StartOptions extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 443;
	private Image backgroundImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	private ViewController viewController;
	JLabel label1 = new JLabel("Choose The Map");
	final JComboBox<String> listMap = new JComboBox<String>();


	public JComboBox<String> getListMap() {
		return listMap;
	}


	private final int LAYER_BACKGROUND = 1;
	private final int LAYER_START_WINDOW = 10;
	public ViewController getViewController() {
		return viewController;
	}



	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}
	
	public StartOptions(ViewController viewController) {
		this.viewController=viewController;
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT + getInsets().top);
		
		
		//title of the window 
		setTitle("Starting Options");
		
		//we don't want to let the user to resize the windows
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		loadResources();
		initComponents();
	
	}
	
	
	
	/** metodo che si occupa di caricare tutti i componenti che sono utilizzati nella schermata
	 * 
	 */
	private void initComponents() {
		layeredPane = new JLayeredPane();
		setContentPane(layeredPane);
		backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
		add(backgroundLabel);
		layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
		final JPanel StartinPanel = new JPanel();
			
		 listMap.addItem("GALILEI");
		 listMap.addItem("GALVANI");
		 listMap.addItem("FERMI");
			
		 listMap.setBackground(Color.BLACK);
		 listMap.setForeground(Color.GREEN);
		 final JComboBox<String> listPlayerNumber = new JComboBox<String>();
		 
		 listPlayerNumber.addItem("2");
		 listPlayerNumber.addItem("4");
		 
		 listPlayerNumber.addItem("3");
		
		 listPlayerNumber.addItem("5");
		 listPlayerNumber.addItem("6");
		 listPlayerNumber.addItem("7");
		 listPlayerNumber.addItem("8");
		 listPlayerNumber.setBackground(Color.BLACK);
		 listPlayerNumber.setForeground(Color.GREEN);
		 final JComboBox<String> typeConnection = new JComboBox<String>();
		 typeConnection.addItem("RMI");
		 typeConnection.addItem("SOCKET");
		
		 
			
		 typeConnection.setBackground(Color.BLACK);
		 typeConnection.setForeground(Color.GREEN);
		
		 final JTextField userLabel = new JTextField (10) ;
		 JLabel label1 = new JLabel("Choose The Map");
		 JLabel label2 = new JLabel("Max Number of Players");
		 JLabel label3 = new JLabel("Choose type of Connection");
		 label1.setForeground(Color.white);
		 label2.setForeground(Color.white);
		 label3.setForeground(Color.white);
		 
		 StartinPanel.add(userLabel);
		 StartinPanel.add(label1);
		 StartinPanel.add(listMap);
		 StartinPanel.add(label2);
		 StartinPanel.add(listPlayerNumber);
		 StartinPanel.add(label3);
		 StartinPanel.add(typeConnection);
		 JButton startButton = new JButton("Start the Game");
		 add(StartinPanel);
		
		startButton.addActionListener(new StartButtonListener (this.viewController,userLabel,
				listMap,listPlayerNumber,typeConnection));
						
		StartinPanel.add(startButton);
		layeredPane.setLayer(StartinPanel, LAYER_START_WINDOW );
		StartinPanel.setBounds(500, 180,150, 220);
		
		StartinPanel.setBackground(Color.black);
	}


   
	/** Uploading of the images.
	 * 
	 */
	private void loadResources() {
	
		try {

			backgroundImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/Start.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
