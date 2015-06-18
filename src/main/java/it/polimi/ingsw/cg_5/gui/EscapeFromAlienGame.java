package it.polimi.ingsw.cg_5.gui;

import it.polimi.ingsw.cg_5.connection.PlayerDTO;
import it.polimi.ingsw.cg_5.view.ViewController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
	LogMessage logPanel = new LogMessage();
	
	
	
	
	public EscapeFromAlienGame(ViewController viewController) {
		
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
	
	}
	


	private void loadResources(ViewController viewController) {
		//load the background image from the disk
		try {
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="GALILEI");
			mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galilei.jpg"));
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="FERMI"){
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/fermi.jpg"));
			}
			if(viewController.getStartOptions().getListMap().getSelectedItem()=="GALVANI"){
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galvani.jpg"));
			}

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
			JButton buttonFake = new JButton();
			
			Border buttonBorder = new LineBorder(Color.blue, 1);
			moveButton.setBackground(Color.BLACK);
			moveButton.setForeground(Color.blue);
			moveButton.setBorder(buttonBorder);
			drawCard.setBackground(Color.BLACK);
			drawCard.setForeground(Color.blue);
			drawCard.setBorder(buttonBorder);
			bluffButton.setBackground(Color.BLACK);
			bluffButton.setForeground(Color.blue);
			bluffButton.setBorder(buttonBorder);
			useCardButton.setBackground(Color.BLACK);
			useCardButton.setForeground(Color.blue);
			useCardButton.setBorder(buttonBorder);
			endTurn.setBackground(Color.BLACK);
			endTurn.setForeground(Color.blue);
			endTurn.setBorder(buttonBorder);
			discard.setBackground(Color.BLACK);
			discard.setForeground(Color.blue);
			discard.setBorder(buttonBorder);
			attackButton.setBackground(Color.BLACK);
			attackButton.setForeground(Color.blue);
			attackButton.setBorder(buttonBorder);
			
			
			// adding listener
			moveButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"MOVE"));
			endTurn.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ENDTURN"));
			attackButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ATTACK"));
			drawCard.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DRAW"));
			bluffButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"BLUFF"));
			useCardButton.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"USECARD"));
			discard.addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DISCARD"));
			Publish.setLayout( new GridLayout(4,2));
			
			Publish.add(moveButton);
			Publish.add(attackButton);
			Publish.add(drawCard);
			Publish.add(bluffButton);	
			Publish.add(useCardButton);
			Publish.add(bluffButton);	
			Publish.add(endTurn);
			
			Publish.add(discard);
			Publish.add(buttonFake);
			layeredPane.setLayer(logPanel, 10);
			

	
			add(Publish);
			
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
