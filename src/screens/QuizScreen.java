package screens;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import classes.Question;
import classes.User;
import data.DatabaseConnector;

public class QuizScreen extends Frame implements KeyListener {
	
	JLabel p1_name,p2_name,questionLabel;
	
	DatabaseConnector d = new DatabaseConnector();
	User[] users = new User[2];
	
	int p1_score,p2_score,count;
	Integer[] q_id = new Integer[7];
	
	JButton option1,option2,option3,option4;
	
	Question question;
	
	QuizScreen(){
		this.users = d.getUsers();
		prepareGUI();
		
		question = d.getQuestion("LOW");
		setQuestion();
		
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
	    
	    questionLabel = new JLabel("");
	    questionLabel.setBounds((screenSize.width*1)/2,(screenSize.height *1)/2 ,700,40);
	    this.add(questionLabel);
	    
	    option1 = new JButton("");
	    option1.setBounds((screenSize.width*2)/5, (screenSize.height *3)/5, 100, 40);
	    this.add(option1);
	    
	    option2 = new JButton("");
	    option2.setBounds((screenSize.width*3)/5, (screenSize.height *3)/5, 100, 40);
	    this.add(option2);
	    
	    option3 = new JButton("");
	    option3.setBounds((screenSize.width*2)/5, (screenSize.height *4)/5, 100, 40);
	    this.add(option3);
	    
	    option4 = new JButton("");
	    option4.setBounds((screenSize.width*3)/5, (screenSize.height *4)/5, 100, 40);
	    this.add(option4);
	    
	    
	    
	    
	    hello.setBounds(screenSize.width/2 - 150 , 100 , 300, 200);
	    hello.setFont(new Font("DpQuake", Font.BOLD,75));
	    
	    this.setLayout(null);
	    this.setVisible(true);
	}
	
	public void setQuestion() {
		questionLabel.setText(question.getQuestion());
		option1.setText(question.getOption1());
		option2.setText(question.getOption2());
		option3.setText(question.getOption3());
		option4.setText(question.getOption4());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
			case 'q':
				break;
			case 'w':
				break;
			case 'e':
				break;
			case 'r':
				break;
			case 'v':
				break;
			case 'b':
				break;
			case 'n':
				break;
			case 'm':
				break;
			default:
				
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
