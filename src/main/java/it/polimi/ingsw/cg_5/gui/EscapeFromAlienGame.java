package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.model.Alien;
import it.polimi.ingsw.cg_5.model.Human;
import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class EscapeFromAlienGame extends JFrame{
	
	private static final int WINDOW_WIDTH = 1100;
	private static final int WINDOW_HEIGHT = 713;
	
	private Image mapImage;
	private Image circleImage;
	private Image alienImage;
	private Image humanImage;
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	private ViewController viewController;
	DtoPanel dtoPanel= new DtoPanel();
	LogMessage logPanel = new LogMessage();
	JLabel image ;
	JLabel piece ;

	
	
	
        
	
	
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
	
	public void printCircle(int x, int y) {
		this.image.setBounds(x, y, 60, 60);
	 
	}
	public void printPiece(int x, int y) {
		
		
		if(this.viewController.getView().getCharacter()!=null){
		this.piece.setBounds(x, y, 60, 60);
		if(this.viewController.getView().getCharacter().getClass()== Alien.class)
			piece.setIcon(new ImageIcon(alienImage));
		}
	 
	}
	

	private void loadResources(ViewController viewController) {
		//load the background image from the disk
		try {
			circleImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/selected.png"));
			alienImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/Outer_Space_Alien_32.png"));
			humanImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/running37.png"));
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="GALILEI");
			mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galilei.png"));
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="FERMI"){
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/fermi.png"));
			}
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="GALVANI"){
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galvani.png"));
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		

	}
	
	public ViewController getViewController() {
		return viewController;
	}

	private void initComponents() {
		
		///PROVA JBUTTON
		
		
		
		layeredPane = new JLayeredPane();
		  setContentPane(layeredPane);
		
		  backgroundLabel = new JLabel(new ImageIcon(mapImage));
			backgroundLabel.setBounds(0,0,801, 591);
			add(backgroundLabel);
			/////prova button
			
			
			layeredPane.setLayer(backgroundLabel, 0);
			
			
			//-----------------start comandPanel--------------//
			final JPanel Publish = new JPanel();
			Publish.setBorder(BorderFactory.createLineBorder(Color.blue));			
			Publish.setBounds(800,421, 294, 264);
			Publish.setBackground(Color.WHITE);
			
			layeredPane.setLayer(Publish, 10);
			
			
		
			JButton moveButton= new JButton("Move");
			JButton attackButton= new JButton("Attack");
			JButton drawCard=new JButton("DrawCard");
			JButton bluffButton= new JButton("Bluff Sector");
			JButton useCardButton= new JButton("UseCard");
			JButton endTurn= new JButton("endTurn");
			JButton discard = new JButton("Discard");
			
			Color buttonBackGColor= Color.BLACK;
			Color buttonColor = Color.ORANGE;
			Border buttonBorder = new LineBorder(Color.blue, 1);
			moveButton.setBackground(buttonBackGColor);
			moveButton.setForeground(buttonColor);
			moveButton.setBorder(buttonBorder);
			drawCard.setBackground(buttonBackGColor);
			drawCard.setForeground(buttonColor);
			drawCard.setBorder(buttonBorder);
			bluffButton.setBackground(buttonBackGColor);
			bluffButton.setForeground(buttonColor);
			bluffButton.setBorder(buttonBorder);
			useCardButton.setBackground(buttonBackGColor);
			useCardButton.setForeground(buttonColor);
			useCardButton.setBorder(buttonBorder);
			endTurn.setBackground(buttonBackGColor);
			endTurn.setForeground(buttonColor);
			endTurn.setBorder(buttonBorder);
			discard.setBackground(buttonBackGColor);
			discard.setForeground(buttonColor);
			discard.setBorder(buttonBorder);
			attackButton.setBackground(buttonBackGColor);
			attackButton.setForeground(buttonColor);
			attackButton.setBorder(buttonBorder);
			Mouse mouse = new Mouse(this);
			this.addMouseListener(mouse);
			
			
			// adding listener
			
			endTurn.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ENDTURN",""));
			attackButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ATTACK",""));
			drawCard.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DRAW",""));
			
			useCardButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"USECARD",""));
			discard.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DISCARD",""));
			Publish.setLayout( new GridLayout(4,2));
			
			Publish.add(moveButton);
			Publish.add(attackButton);
			Publish.add(drawCard);
			Publish.add(bluffButton);	
			Publish.add(useCardButton);
			Publish.add(bluffButton);	
			Publish.add(endTurn);
			Publish.add(discard);
			
			add(Publish);
			
			//-----------------end comandPanel--------------//
			
			layeredPane.setLayer(logPanel, 10);
			

	
			
			
			//------------------------------------------------------------------------- PANNELLO DTO--------------------------------------------------------------------
			
			layeredPane.setLayer(dtoPanel, 10);
			add(dtoPanel);
			add(logPanel);
			
			
		
			//------------------------------------------------------------------------FINE PANNELLO DTO---------------------------------------------------------------
			
			
		
	}



	public LogMessage getLogPanel() {
		return logPanel;
	}



	public DtoPanel getDtoPanel() {
		return dtoPanel;
	}
	
	}
