package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
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
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class EscapeFromAlienGame extends JFrame{
	
	private static final int WINDOW_WIDTH = 1100;
	private static final int WINDOW_HEIGHT = 713;
	
	private Image mapImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	private ViewController viewController;
	DtoPanel dtoPanel= new DtoPanel();
	final JTextPane d=new JTextPane();
	StyledDocument doc = d.getStyledDocument();
	
	public void updateDocument(String s) throws BadLocationException{
		// TODO da VERIFICARE
		javax.swing.text.Style style = d.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.green);
		this.doc.insertString(doc.getLength(), s, style);
	}
	
	
	public EscapeFromAlienGame(ViewController viewController) {
		this.viewController=viewController;
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
			
			d.setForeground(Color.RED);
			layeredPane.setLayer(Publish, 10);
			
			
			 final JPanel LogMessage = new JPanel();
			 LogMessage.setBounds(300,500, 280, 150);
			JButton moveButton= new JButton("Move");
			final JButton attackButton= new JButton("Attack");
			JButton drawCard=new JButton("DrawCard");
			JButton bluffButton= new JButton("Bluff Sector");
			JButton useCardButton= new JButton("UseCard");
			JButton endTurn= new JButton("endTurn");

		
			
			moveButton.addActionListener(new ButtonListener(this.viewController){
				 public void actionPerformed(ActionEvent e){ 
					 String result=null;
						String sector= JOptionPane.showInputDialog("Sector to move");
						System.out.println(sector);
			try {
				PlayerDTO playerDTO =this.getViewController().getView().getRmiClient().moveRequest(sector,this.getViewController().getView().getPlayerID(), this.getViewController().getView().getNumberGame());
				viewController.getView().setCharacter(playerDTO.getYourCharacter());
				dtoPanel.updateDtoPanel(viewController.getView().getCharacter());
				result= playerDTO.getMessageToSend();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
					d.setForeground(Color.RED);
					// provo a fare subscribe
					//StyledDocument doc = d.getStyledDocument();
						

				        javax.swing.text.Style style = d.addStyle("I'm a Style", null);
				        StyleConstants.setForeground(style, Color.BLACK);
				        try {
							doc.insertString(doc.getLength(), result+"\n",style);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						
					 
				 }
			});
			
			endTurn.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button Endturn");
					
					 
					String sector= JOptionPane.showInputDialog("Sector where you want to move.");
					System.out.println(sector);
					d.setForeground(Color.RED);
					 StyledDocument doc = d.getStyledDocument();
						

				        javax.swing.text.Style style = d.addStyle("I'm a Style", null);
				        StyleConstants.setForeground(style, Color.green);
				        try {
							doc.insertString(doc.getLength(), "EndTurn\n",style);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						
					 
				 }
			});
			
			
			
			attackButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button Attack");
					//JOptionPane.showMessageDialog(Publish, "A basic JOptionPane message dialog");
					 d.setForeground(Color.green);
					 StyledDocument doc = d.getStyledDocument();
		

				        javax.swing.text.Style style = d.addStyle("I'm a Style", null);
				        StyleConstants.setForeground(style, Color.red);
				        try {
							doc.insertString(doc.getLength(), "attackeeeee",style);
							
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					 
				 }
			});
			
			drawCard.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button drawCard");
					
					 d.setForeground(Color.green);
					 StyledDocument doc = d.getStyledDocument();
		

				        javax.swing.text.Style style = d.addStyle("I'm a Style", null);
				        StyleConstants.setForeground(style, Color.red);
				        try {
							doc.insertString(doc.getLength(), "DrawCard",style);
							
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					 
				 }
			});
			
			bluffButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 System.out.println("You clicked the button Bluff");
					 
					 
					String sector= JOptionPane.showInputDialog("Sector to bluff");
					System.out.println(sector);
					d.setForeground(Color.RED);
					 StyledDocument doc = d.getStyledDocument();
						

				        javax.swing.text.Style style = d.addStyle("I'm a Style", null);
				        StyleConstants.setForeground(style, Color.green);
				        try {
							doc.insertString(doc.getLength(), "bluff\n",style);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						
					 
				 }
			});
			
			
			Publish.setLayout( new GridLayout(4,2));
			Publish.add(moveButton);
			Publish.add(attackButton);
			Publish.add(drawCard);
			Publish.add(bluffButton);	
			Publish.add(useCardButton);
			Publish.add(bluffButton);	
			Publish.add(endTurn);
			
			
			 //LogMessage.setBackground(Color.GRAY);
			 
			//JScrollBar fr = new JScrollBar(d);
		
			JScrollPane sp = new JScrollPane(d,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			sp.setSize(new Dimension(120, 120));
			add(sp);
			sp.setForeground(Color.GREEN);
			sp.setBorder(BorderFactory.createLineBorder(Color.black));
			sp.setBounds(800,10, 280, 300);
			d.setBackground(Color.lightGray);
			layeredPane.setLayer(d, 10);
			

	
			add(Publish);
			
			//------------------------------------------------------------------------- PANNELLO DTO--------------------------------------------------------------------
			
			layeredPane.setLayer(dtoPanel, 10);
			add(dtoPanel);
			
			
			
			
			
			
			
		
			//------------------------------------------------------------------------FINE PANNELLO DTO---------------------------------------------------------------
			
	
		
	}
	
	}
