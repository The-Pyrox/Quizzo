package screens;


import java.awt.*;

import javax.swing.*;

import classes.User;
import data.DatabaseConnector;

public class QuizScreen extends Frame {
	
	JLabel p1_name,p2_name;
	
	DatabaseConnector d = new DatabaseConnector();
	User[] users = new User[2];
	
	int p1_score,p2_score,count;
	Integer[] q_id = new Integer[7];
	
	QuizScreen(){
		this.users = d.getUsers();
		prepareGUI();
		
		
		
	   
	}
	
	private void prepareGUI() {
		JLabel hello;
		hello = new JLabel("Hello");
		this.add(hello);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setSize(screenSize.width,screenSize.height);
	    
	    p1_name = new JLabel(users[0].getName());
	    p1_name.setBounds((screenSize.width*1)/8 - 40, (screenSize.height *1)/5, 200, 40);
	    this.add(p1_name);
	    
	    p2_name = new JLabel(users[1].getName());
	    p2_name.setBounds((screenSize.width*7)/8 - 100, (screenSize.height *1)/5, 200, 40);
	    this.add(p2_name);
	    
	    
	    hello.setBounds(screenSize.width/2 - 150 , 100 , 300, 200);
	    hello.setFont(new Font("DpQuake", Font.BOLD,75));
	    
	    this.setLayout(null);
	    this.setVisible(true);
	}
	
	

}
