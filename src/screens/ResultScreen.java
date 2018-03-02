package screens;

import java.awt.*;

import javax.swing.*;

import classes.User;
import data.DatabaseConnector;

public class ResultScreen extends Frame {
	JLabel p1_name,p2_name,p1_score,p2_score,winner,winner_name;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	DatabaseConnector d = new DatabaseConnector();
	User[] users = new User[2];
    
	
	ResultScreen(){
		users = d.getResults();
		prepareGUI();
		setwinner();
	}
	
	private void prepareGUI() {
		int width = screenSize.width;
		int height = screenSize.height;
		
		this.setSize(width,height);
		
		p1_name = new JLabel(users[0].getName());
		p1_name.setBounds((width * 2) /5, (height * 2)/5, 100, 40);
		this.add(p1_name);
		
		p2_name = new JLabel(users[1].getName());
		p2_name.setBounds((width * 3) /5, (height * 2)/5, 100, 40);
		this.add(p2_name);
		
		p1_score = new JLabel(String.valueOf(users[0].getScore()));
		p1_score.setBounds((width * 2) /5, (height * 3)/5, 100, 40);
		this.add(p1_score);

		p2_score = new JLabel(String.valueOf(users[1].getScore()));
		p2_score.setBounds((width * 3) /5, (height * 3)/5, 100, 40);
		this.add(p2_score);
		
		winner = new JLabel("The winner is :");
		winner.setBounds((width *1)/2, (height *4)/5, 200, 40);
		this.add(winner);
		
		winner_name = new JLabel("");
		winner_name.setBounds((width *1)/2,(height * 9)/10, 100, 40);
		this.add(winner_name);
		
		this.setLayout(null);
	    this.setVisible(true);
	
	}
	
	private void setwinner() {
		if(users[0].getScore() > users[1].getScore()) {
			winner_name.setText(users[0].getName());
		}else {
			winner_name.setText(users[1].getName());
		}
	}

}
