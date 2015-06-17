package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LogMessage extends JScrollPane {
	///VERIFICARE SE STATIC DA PROBLEMI
	private   JTextPane textLog;
	StyledDocument doc  = textLog.getStyledDocument();
	
	public LogMessage() {
		super();
		textLog= new JTextPane();
		setBounds(800, 10, 280, 300);
	}
	
	
	
	public void updateLogMessage(String updateMessage) throws BadLocationException{
		
	javax.swing.text.Style style = textLog.addStyle("I'm a Style", null);
    StyleConstants.setForeground(style, Color.green);
    
		doc.insertString(doc.getLength(), updateMessage+"\n",style);
	
	}

	
	
	
	

}
