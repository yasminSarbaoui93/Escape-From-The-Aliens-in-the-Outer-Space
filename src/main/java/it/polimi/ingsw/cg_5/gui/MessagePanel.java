package it.polimi.ingsw.cg_5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MessagePanel extends JPanel {
	
	
	private JPanel southPanel = new JPanel();
	private JTextField messageBox = new JTextField(30);
	private GameButton sendMessage = new GameButton("Send Message");//ci sara la action listener
	
	JTextArea chatBox = new JTextArea();
	GridBagConstraints left = new GridBagConstraints();
	 GridBagConstraints right = new GridBagConstraints();
	public MessagePanel(){
		
		setLayout(new BorderLayout());
		southPanel.setBackground(Color.black);
	    southPanel.setLayout(new GridBagLayout());
	    chatBox.setEditable(false);
	    chatBox.setLineWrap(true);
	    messageBox.setBackground(Color.darkGray);
	    messageBox.setForeground(Color.green);
	    chatBox.setBackground(Color.black);
	    JScrollPane chatBar=new JScrollPane(chatBox,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    chatBar.getVerticalScrollBar().setBackground(Color.BLACK);
	    add(chatBar, BorderLayout.CENTER);
	    
	    // grandezze degli elementi
	    left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);
        add(BorderLayout.SOUTH, southPanel);
        setBorder(BorderFactory.createLineBorder(Color.blue));
	}

	public GameButton getSendMessage() {
		return sendMessage;
	}
}
