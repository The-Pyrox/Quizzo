package screens;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


import javax.swing.*;

import data.DatabaseConnector;

public class StartScreen extends Frame implements MouseListener{
	
	JLabel game_title,player1,player2,name1,name2;
    JTextField p1_name,p2_name;
    JButton p1_ready,p2_ready,start;
    Boolean isp1 = false,isp2 = false;
    
    DatabaseConnector d = new DatabaseConnector();
    
    String[] users = new String[2];
    
    
	  StartScreen(){
		  
		  prepareGUI();
		    
		  }
	  

		  public static void main(String args[]){
		    StartScreen f = new StartScreen();
		    
		    //d = new DatabaseConnector();
		    
		 
		  }
		  
		  
		  private void checkstart() {
			  if(isp1 && isp2) {
				 users[0] = p1_name.getText();
				 users[1] = p2_name.getText();
				 d.addUser(users);
				 start.setVisible(true);
				  
			  }
		  }
		  
		  private void prepareGUI() {

			 
			    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    this.setSize(screenSize.width,screenSize.height);
			     
			    
			    
			    
			    game_title = new JLabel("Quizzo...");
			    game_title.setBounds(screenSize.width/2 - 150 , 100 , 300, 200);
			    game_title.setFont(new Font("DpQuake", Font.BOLD,75));
			    this.add(game_title);
			   
			    player1 = new JLabel("Player 1");
			    player1.setBounds((screenSize.width*1)/8, (screenSize.height *2)/5, 80, 40);
			    this.add(player1);
			   
			    player2 = new JLabel("Player 2");
			    player2.setBounds((screenSize.width*7)/8 - 80, (screenSize.height *2)/5, 80, 40);
			    this.add(player2);
			    
			    p1_name = new JTextField("");
			    p1_name.setBounds((screenSize.width*1)/8 - 40, (screenSize.height *1)/2, 200, 40);
			    this.add(p1_name);
			    
			    p2_name = new JTextField("");
			    p2_name.setBounds((screenSize.width*7)/8 - 100, (screenSize.height *1)/2, 200, 40);
			    this.add(p2_name);
			    
			    p1_ready = new JButton("Ready");
			    p1_ready.setBounds((screenSize.width*1)/8 - 40, (screenSize.height *3)/5, 100, 40);
			    p1_ready.addMouseListener(this);
			    this.add(p1_ready);
			    
			    
			    p2_ready = new JButton("Ready");
			    p2_ready.setBounds((screenSize.width*7)/8 - 100, (screenSize.height *3)/5, 100, 40);
			    p2_ready.addMouseListener(this);
			    this.add(p2_ready);
			    
			    start = new JButton("Start");
			    start.setBounds((screenSize.width*1)/2 - 100, (screenSize.height *4)/5, 100, 40);
			    start.setVisible(false);
			    start.addMouseListener(this);
			    this.add(start);
			   
			   
			    
			   
			    
			    
			    
			   
			    this.setLayout(null);
			    this.setVisible(true);
			   
		  }


		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getComponent().equals(p1_ready)){
				isp1 = true;
				checkstart();
			}
			if(e.getComponent().equals(p2_ready)){
				isp2 = true;
				checkstart();
			}
			if(e.getComponent().equals(start)) {
				System.out.println("Hello");
				this.dispose();
				QuizScreen q = new QuizScreen();
			}
		
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
