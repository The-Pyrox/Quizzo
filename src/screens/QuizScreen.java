package screens;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import classes.Question;
import classes.User;
import data.DatabaseConnector;

public class QuizScreen extends Frame implements KeyListener {
	
	JLabel p1_name,p2_name,questionLabel,p1_labelscore,p2_labelscore;
	DatabaseConnector d = new DatabaseConnector();
	User[] users = new User[2];
	int p1_score = 0,p2_score = 0;
	Boolean p1_ready = false,p2_ready = false;
	ArrayList<Integer> q_id = new ArrayList();
 	JButton option1,option2,option3,option4;
	JProgressBar p1_bar,p2_bar,progressBar;
	Question question;
	String severity = "LOW";
	int counter = 10;
	ActionListener listener;
	Timer timer ;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 BevelBorder bevel = new BevelBorder(BevelBorder.RAISED);
    
	QuizScreen(){
		this.users = d.getUsers();
		prepareGUI();
		startgame();
	}
	
	private void prepareGUI() {
		
		this.setSize(screenSize.width,screenSize.height);
	    
	    p1_bar = new JProgressBar(JProgressBar.VERTICAL, 0, 200);
	    p1_bar.setBounds((screenSize.width*1)/8 - 80, (screenSize.height *4)/5 - 600, 5, 600);
	    p1_bar.setValue(0);
	    this.add(p1_bar);
         
        p2_bar = new JProgressBar(JProgressBar.VERTICAL, 0, 200);
	    p2_bar.setBounds((screenSize.width*7)/8 + 80, (screenSize.height *4)/5 - 600, 5, 600);
	    p2_bar.setValue(0);
	    this.add(p2_bar);
	    
	    p1_labelscore = new JLabel("0");
	    p1_labelscore.setBounds((screenSize.width*1)/8 -80, (screenSize.height *8)/9 , 100, 40);
	    this.add(p1_labelscore);
	    
	    p2_labelscore = new JLabel("0");
	    p2_labelscore.setBounds((screenSize.width*7)/8 +80, (screenSize.height *8)/9 , 100, 40);
	    this.add(p2_labelscore);
	    
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
	    option1.setBounds((screenSize.width*1)/5, (screenSize.height *3)/5, 400, 100);
	    option1.setFont(new java.awt.Font("Lemon/Milk light", Font.BOLD, 16));
	    option1.setOpaque(true);
	    option1.setBackground(Color.WHITE);
	    option1.setForeground(Color.BLUE);
	    this.add(option1);
	    
	    option2 = new JButton("");
	    option2.setBounds((screenSize.width*3)/5, (screenSize.height *3)/5, 400, 100);
	    option2.setFont(new java.awt.Font("Lemon/Milk light", Font.BOLD, 16));
	    option2.setOpaque(true);
	    option2.setBackground(Color.WHITE);
	    option2.setForeground(Color.BLUE);
	    this.add(option2);
	    
	    option3 = new JButton("");
	    option3.setBounds((screenSize.width*1)/2 -600, (screenSize.height *4)/5, 400, 100);
	    option3.setFont(new java.awt.Font("Lemon/Milk light", Font.BOLD, 16));
	    option3.setOpaque(true);
	    option3.setBackground(Color.WHITE);
	    option3.setForeground(Color.BLUE);
	    this.add(option3);
	    
	    option4 = new JButton("");
	    option4.setBounds((screenSize.width*1)/2 +200, (screenSize.height *4)/5, 400, 100);
	    option4.setFont(new java.awt.Font("Lemon/Milk light", Font.BOLD, 16));
	    option4.setOpaque(true);
	    option4.setBackground(Color.WHITE);
	    option4.setForeground(Color.BLUE);
	    this.add(option4);
	    
	    progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 10);
     	progressBar.setBounds(100, 50, (screenSize.width) -200, 30);
     	
     	timer = new Timer(1000, listener);
        
     	listener = new ActionListener() {
            
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				counter--;
               progressBar.setValue(counter);
               if (counter<1) {
              	 	JOptionPane.showMessageDialog(null, "No one couldnt answer the question.");
              	 	p1_ready = true;
              	 	p2_ready = true;
              	 	checkready();
               } 
  				
  			}
          };
       
        this.addKeyListener(this);
	    
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
		int multiplier ;
		if(severity.equals("LOW")) {
			multiplier = 2;
		}else if(severity.equals("MID")) {
			multiplier = 4;
		}else {
			multiplier = 6;
		}
		if(e.getKeyChar() == 'q' || e.getKeyChar() == 'w' || e.getKeyChar() == 'e' || e.getKeyChar() == 'r') {
			switch(e.getKeyChar()) {
			case 'q':
				option1.setBorder(bevel);
				if(question.getCorrectOption().equals(question.getOption1())) {
					p1_score = p1_score + counter*multiplier;
				}
				break;
			case 'w':
				if(question.getCorrectOption().equals(question.getOption2())) {
					p1_score = p1_score + counter*multiplier;
				}
				break;
			case 'e':
				if(question.getCorrectOption().equals(question.getOption3())) {
					p1_score = p1_score + counter*multiplier;
				}
				break;
			case 'r':
				if(question.getCorrectOption().equals(question.getOption4())) {
					p1_score = p1_score + counter*multiplier;
				}
				break;
			default :
			}
			
			p1_bar.setValue(p1_score);
			p1_ready = true;
			p1_labelscore.setText(String.valueOf(p1_score));
			checkready();
		}else if(e.getKeyChar() == 'v' || e.getKeyChar() == 'b' || e.getKeyChar() == 'n' || e.getKeyChar() == 'm') {
			switch(e.getKeyChar()) {
			case 'v':
				if(question.getCorrectOption().equals(question.getOption1())) {
					p2_score = p2_score + counter*multiplier;
				}
				break;
			case 'b':
				if(question.getCorrectOption().equals(question.getOption2())) {
					p2_score = p2_score + counter*multiplier;
				}
				break;
			case 'n':
				if(question.getCorrectOption().equals(question.getOption3())) {
					p2_score = p2_score + counter*multiplier;
				}
				break;
			case 'm':
				if(question.getCorrectOption().equals(question.getOption4())) {
					p2_score = p2_score + counter*multiplier;
				}
				break;
			default:
				
			}
			
			p2_bar.setValue(p2_score);
			p2_ready = true;
			p2_labelscore.setText(String.valueOf(p2_score));
			checkready();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void startgame() {
		question = d.getQuestion(severity,q_id);
		setQuestion();
		initProgressBar();
	}
	
	public void initProgressBar() {
		progressBar.setValue(10);
        progressBar.setVisible(true);
        counter = 10;
        timer.addActionListener(listener);
        timer.restart();
        this.add(progressBar);
	}
	
	public void checkready() {
		
		if(p1_ready && p2_ready) {
			p1_ready = false;
			p2_ready = false;
			q_id.add(Integer.valueOf(question.getQuestionId()));
			if(q_id.size() == 3) {
				severity = "MID";
			}else if(q_id.size() == 5) {
				severity = "HIGH";
			}
			timer.stop();
			timer.removeActionListener(listener);
			if(q_id.size() == 6) {
				users[0].setScore(p1_score);
				users[1].setScore(p2_score);
				d.setResults(users);
				this.dispose();
				ResultScreen r = new ResultScreen();			
			}else {
				startgame();
			}
		}
	}
	
	

}
