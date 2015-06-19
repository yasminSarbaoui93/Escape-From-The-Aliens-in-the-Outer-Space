package it.polimi.ingsw.cg_5.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class LogMessage extends JScrollPane {
	///VERIFICARE SE STATIC DA PROBLEMI
	private static  JTextPane textLog=new JTextPane();
	StyledDocument doc  = textLog.getStyledDocument();
	
	public LogMessage() {
		super(textLog,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		DefaultCaret caret = (DefaultCaret)textLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setBounds(800, 0, 294, 300);
		setBorder(BorderFactory.createLineBorder(Color.red));
		setBackground(Color.black);
		textLog.setBackground(Color.black);
		
	}
	
	
	



	public void updateLogMessage(String updateMessage, Color color) throws BadLocationException{
		
	javax.swing.text.Style style = textLog.addStyle("I'm a Style", null);
    StyleConstants.setForeground(style, color);
    
		doc.insertString(doc.getLength(), updateMessage+"\n",style);
	
	}
	
	

	
	
	
	

}
