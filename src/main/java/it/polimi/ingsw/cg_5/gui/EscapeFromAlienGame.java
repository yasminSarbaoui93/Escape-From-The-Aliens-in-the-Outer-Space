package it.polimi.ingsw.cg_5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EscapeFromAlienGame extends JFrame{
	
	private static final int WINDOW_WIDTH = 1100;
	private static final int WINDOW_HEIGHT = 713;
	
	private Image mapImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	
	public EscapeFromAlienGame() {

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

			mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galilei.jpg"));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	private void initComponents() {
		
		layeredPane = new JLayeredPane();
		  setContentPane(layeredPane);
		
		  backgroundLabel = new JLabel(new ImageIcon(mapImage));
			backgroundLabel.setBounds(0,0,801, 685);
			add(backgroundLabel);
			
			layeredPane.setLayer(backgroundLabel, 0);
			
			final JPanel Publish = new JPanel();
			
			Publish.setBounds(800,500, 280, 150);
			Publish.setBackground(Color.WHITE);
			final JTextArea d=new JTextArea(100, 100);
			d.setForeground(Color.RED);
			layeredPane.setLayer(Publish, 10);
			
			
			 final JPanel LogMessage = new JPanel();
			 LogMessage.setBounds(300,500, 280, 150);
			JButton submitButton= new JButton("Submit");
			final JButton attackButton= new JButton("Attack");
			System.out.println(submitButton.getPreferredSize()); 

			
			
			submitButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button");
					 JLabel label1 = new JLabel("Enter sectorToMove");
					 JTextField Sector = new JTextField(20);
					 
					 JButton confirmMove= new JButton("Confirm");
					 JPanel panel = new JPanel();
					  panel.add(label1);
					  panel.add(Sector);
					  panel.add(confirmMove);
					  
					//JOptionPane.S
					String ddd= JOptionPane.showInputDialog("Sector where you want to move.");
					System.out.println(ddd);
					d.setForeground(Color.RED);
					 d.append("PRESSED SUBMIT\n");
						
					 
				 }
			});
			
			
			
			attackButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button Attack");
					//JOptionPane.showMessageDialog(Publish, "A basic JOptionPane message dialog");
					 d.setForeground(Color.green);
					 d.append("Pressed Attack \n");
						
					 
				 }
			});
			
			
			submitButton.setBorderPainted(true);
			Publish.setLayout( new GridLayout(4,2));
			Publish.add(attackButton);
			Publish.add(new JButton("DrawCard"));
			Publish.add(new JButton("UseCard"));
			Publish.add(new JButton("Bluff"));	
			Publish.add(new JButton("EndTurn"));
			Publish.add(new JButton("EndTurn"));
			Publish.add(new JButton("Move"));
			Publish.add(submitButton);
			 //LogMessage.setBackground(Color.GRAY);
			 
			//JScrollBar fr = new JScrollBar(d);
			
			JScrollPane sp = new JScrollPane(d,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			d.setLineWrap(true);
			add(sp);
			sp.setForeground(Color.GREEN);
			sp.setBorder(BorderFactory.createLineBorder(Color.black));
			sp.setBounds(800,10, 280, 300);
			d.setBackground(Color.lightGray);
			layeredPane.setLayer(d, 10);
			
			//LogMessage.setBounds(800,0, 280, 150);
		
			//add(LogMessage);
			//layeredPane.setLayer(LogMessage, 100);
			
			
			
			
			//add(submitButton);
			add(Publish);
			//BorderLayout border	= new BorderLayout();
			
			
		
	}
	}
