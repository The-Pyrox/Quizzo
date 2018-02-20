package screens;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.*;

public class StartScreen {
	  StartScreen(){
		    JFrame frame = new JFrame();
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    frame.setSize(screenSize.width,screenSize.height);
		    
		    
		    Label game_title,player1,player2,name1,name2;
		    
		    game_title = new Label("Quizzo...");
		    game_title.setBounds(screenSize.width/2 - 150 , 100 , 300, 200);
		    game_title.setFont(new Font("DpQuake", Font.BOLD,75));
		    frame.add(game_title);
		   
		    player1 = new Label("Player 1");
		    player1.setBounds((screenSize.width*1)/8, (screenSize.height *2)/5, 80, 40);
		    frame.add(player1);
		   
		    
		    
		    
		   
		    frame.setLayout(null);
		    frame.setVisible(true);
		  }

		  public static void main(String args[]){
		    StartScreen f = new StartScreen();
		  }
}
