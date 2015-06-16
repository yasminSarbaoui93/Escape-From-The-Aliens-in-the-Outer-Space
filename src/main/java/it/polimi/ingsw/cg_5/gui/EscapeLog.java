package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class EscapeLog extends JFrame {
	
	private static final int WINDOW_WIDTH = 700;
	private static final int WINDOW_HEIGHT = 681;
	
	
	private Image backgroundImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	
	private final int LAYER_BACKGROUND = 1;
	private final int LAYER_LOGIN = 10;
	
	public EscapeLog() {

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT + getInsets().top);
		
		//title of the window 
		setTitle("EscapeFromAlien_Login");
		
		//we don't want to let the user to resize the windows
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		loadResources();
		initComponents();
	
	}
	
	private void loadResources() {
		//load the background image from the disk
		try {

			backgroundImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/escape.jpg"));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	private void initComponents() {
		
		layeredPane = new JLayeredPane();
		  setContentPane(layeredPane);

		//The background image is just a JLabel as big as the 
		//whole window
		backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
		backgroundLabel.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
		add(backgroundLabel);
		layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
		
		JPanel loginPanel = new JPanel();
		
		JLabel label1 = new JLabel("Enter username");
		label1.setForeground(Color.white);
		JLabel  label2= new JLabel("Enter password");
		final JTextField userName = new JTextField(20);
		final JTextField password = new JTextField(20);
		
		JButton login= new JButton("Login");
		login.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 System.out.println("You clicked the button");
				 String user=userName.getText();
					String pass=password.getText();
					System.out.println(user+ pass);
				 
			 }
		});
		loginPanel.add(label1);
		loginPanel.add(userName);
		loginPanel.add(label2);
		loginPanel.add(password);
		label2.setForeground(Color.WHITE);
		loginPanel.add(login);
		
		
		add(loginPanel);
		
		layeredPane.setLayer(loginPanel, LAYER_LOGIN);
		loginPanel.setBounds(220, 0, 250, 150);
		
		loginPanel.setBackground(Color.black);

		
		
		//System.out.println(user);
		
	
		


	}
		
}
