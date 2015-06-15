package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
	private final int LAYER_LOGIN = 10;
	
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
		  backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
			add(backgroundLabel);
			layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
			
			JPanel StartinPanel = new JPanel();
			
			JComboBox listMap = new JComboBox();
			listMap.addItem("GALILEI");
			listMap.addItem("FERMI");
			listMap.addItem("GALVANI");
			listMap.setBackground(Color.BLACK);
			listMap.setForeground(Color.GREEN);
			JComboBox listPlayerNumber = new JComboBox();
			listPlayerNumber.addItem("2");
			listPlayerNumber.addItem("4");
			listPlayerNumber.addItem("8");
			listPlayerNumber.setBackground(Color.BLACK);
			listPlayerNumber.setForeground(Color.GREEN);
			JComboBox typeConnection = new JComboBox();
			typeConnection.addItem("RMI");
			typeConnection.setBackground(Color.BLACK);
			typeConnection.setForeground(Color.GREEN);
		


			//JList list = new JList(listModel);
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
			add(StartinPanel);
			layeredPane.setLayer(StartinPanel, LAYER_LOGIN);
			StartinPanel.setBounds(420, 200,150, 200);
			
			StartinPanel.setBackground(Color.black);
	}



	private void loadResources() {
		//load the background image from the disk
		try {

			backgroundImage = ImageIO.read(new File("./src/main/java/provaSwing/Start.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
