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
	private JLabel backgroundLabel;
	private JLayeredPane layeredPane;
	private ViewController viewController;
	private DtoPanel dtoPanel= new DtoPanel();
	private LogMessage logPanel = new LogMessage();
	private ButtonPanel buttonPanel = new ButtonPanel();
	private MessagePanel messagePanel= new MessagePanel();
	
        
	
	
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}



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
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("GALILEI"));
			mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/galilei.jpg"));
			
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("FERMI"))
				mapImage = ImageIO.read(new File("./src/main/java/it/polimi/ingsw/cg_5/gui/fermi.jpg"));
		
			if(viewController.getStartOptions().getListMap().getSelectedItem().equals("GALVANI")){
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
			/////prova button
			JButton prova = new JButton();
			layeredPane.add(prova);
			prova.setContentAreaFilled(false);
			
			prova.setOpaque(false);
			prova.setBounds(287, 275, 24, 37);
			prova.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
					System.out.println("I05");
					
				}
			});
			
			layeredPane.setLayer(backgroundLabel, 0);
			
			
			//-----------------start comandPanel--------------//
			
			
			layeredPane.setLayer(buttonPanel, 10);
			
			
			
			
			// adding listener
			buttonPanel.getMoveButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"MOVE"));
			buttonPanel.getEndTurn().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ENDTURN"));
			buttonPanel.getAttackButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"ATTACK"));
			buttonPanel.getDrawCard().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DRAW"));
			buttonPanel.getBluffButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"BLUFF"));
			buttonPanel.getUseCardButton().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"USECARD"));
			buttonPanel.getDiscard().addActionListener(new GameButtonListener(this.viewController,this.dtoPanel,this.logPanel,"DISCARD"));
			
			
			
			add(buttonPanel);
			
			//-----------------end comandPanel--------------//
			
			layeredPane.setLayer(logPanel, 10);
			

	
			
			
			//------------------------------------------------------------------------- PANNELLO DTO--------------------------------------------------------------------
			
			layeredPane.setLayer(dtoPanel, 10);
			add(dtoPanel);
			add(logPanel);
			add(messagePanel);
			layeredPane.setLayer(messagePanel, 10);
			messagePanel.setBounds(0, 500, 801, 100);
		
			//------------------------------------------------------------------------FINE PANNELLO DTO---------------------------------------------------------------
			
			
		
	}



	public LogMessage getLogPanel() {
		return logPanel;
	}

	


	public DtoPanel getDtoPanel() {
		return dtoPanel;
	}
	
	}
