package screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class QuizScreen {
	
	JFrame frame = new JFrame();
	
	QuizScreen(){
		JLabel hello;
		hello = new JLabel("Hello");
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setSize(screenSize.width,screenSize.height);
	     
	    
	    
	    
	    hello.setBounds(screenSize.width/2 - 150 , 100 , 300, 200);
	    hello.setFont(new Font("DpQuake", Font.BOLD,75));
	    frame.add(hello);
	    frame.setLayout(null);
	    frame.setVisible(true);
	   
	}
	
	

}
