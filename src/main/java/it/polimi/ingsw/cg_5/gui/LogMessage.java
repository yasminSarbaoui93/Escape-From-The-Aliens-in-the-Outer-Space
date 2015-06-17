package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LogMessage extends JScrollPane {
	///VERIFICARE SE STATIC DA PROBLEMI
	private static  JTextPane textLog=new JTextPane();
	StyledDocument doc  = textLog.getStyledDocument();
	
	public LogMessage() {
		super(textLog,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setBounds(800, 0, 294, 300);
		setBorder(BorderFactory.createLineBorder(Color.red));
		setBackground(Color.gray);
		textLog.setBackground(Color.DARK_GRAY);
		
	}
	
	
	
	public void updateLogMessage(String updateMessage, Color color) throws BadLocationException{
		
	javax.swing.text.Style style = textLog.addStyle("I'm a Style", null);
    StyleConstants.setForeground(style, color);
    
		doc.insertString(doc.getLength(), updateMessage+"\n",style);
	
	}

	
	
	
	

}
