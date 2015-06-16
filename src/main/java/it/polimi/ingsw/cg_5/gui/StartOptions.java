package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StartOptions extends JFrame {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 443;
	
	
	private Image backgroundImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	
	private final int LAYER_BACKGROUND = 1;
	private final int LAYER_START_WINDOW = 10;
	
	public StartOptions() {

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT + getInsets().top);
		
		
		//title of the window 
		setTitle("Starting Options");
		
		//we don't want to let the user to resize the windows
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		loadResources();
		initComponents();
	
	}
	
	
	
	private void initComponents() {
		layeredPane = new JLayeredPane();
		  setContentPane(layeredPane);
		  
		  //impostazione sfondo
		  backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
			add(backgroundLabel);
			layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
			
			//inserisco un pannello   varie scelte del giocatore  inerenti alla nuova partita che vorra giocare
			JPanel StartinPanel = new JPanel();
			
			// con JComboBox possiamo fare imenu a tendina
			final JComboBox<String> listMap = new JComboBox<String>();
			listMap.addItem("GALILEI");
			listMap.addItem("FERMI");
			listMap.addItem("GALVANI");
			listMap.setBackground(Color.BLACK);
			listMap.setForeground(Color.GREEN);
			final JComboBox<String> listPlayerNumber = new JComboBox<String>();
			listPlayerNumber.addItem("2");
			listPlayerNumber.addItem("3");
			listPlayerNumber.addItem("4");
			listPlayerNumber.addItem("5");
			listPlayerNumber.addItem("6");
			listPlayerNumber.addItem("7");
			listPlayerNumber.addItem("8");
			listPlayerNumber.setBackground(Color.BLACK);
			listPlayerNumber.setForeground(Color.GREEN);
			final JComboBox<String> typeConnection = new JComboBox<String>();
			typeConnection.addItem("RMI");
			typeConnection.setBackground(Color.BLACK);
			typeConnection.setForeground(Color.GREEN);
		


		
			JLabel label1 = new JLabel("Choose The Map");
			JLabel label2 = new JLabel("Max Number of Players");
			JLabel label3 = new JLabel("Choose type of Connection");
			label1.setForeground(Color.white);
			label2.setForeground(Color.white);
			label3.setForeground(Color.white);
			
			StartinPanel.add(label1);
			StartinPanel.add(listMap);
			StartinPanel.add(label2);
			StartinPanel.add(listPlayerNumber);
			StartinPanel.add(label3);
			StartinPanel.add(typeConnection);
			JButton startButton = new JButton("Start the Game");
			add(StartinPanel);
			
			// alla press del bottone Start Game
			
			startButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String  choosenMap= (String) listMap.getSelectedItem();
					String  maxNumberPlayers= (String) listPlayerNumber.getSelectedItem();
					String 	connessionType= (String) typeConnection.getSelectedItem();
					// dainserire logica comunicazione
					System.out.println(choosenMap+maxNumberPlayers+connessionType);
				}
				
				
			});
			
			
			
			StartinPanel.add(startButton);
			layeredPane.setLayer(StartinPanel, LAYER_START_WINDOW );
			StartinPanel.setBounds(500, 200,150, 200);
			
			StartinPanel.setBackground(Color.black);
	}


   // caricamento dell'immagine background situato nello  stesso package del  codice gui
	private void loadResources() {
	
		try {

			backgroundImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/Start.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
