package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classes.Question;
import classes.User;

public class DatabaseConnector {
	
	Connection con;
	 String mDriver = "com.mysql.jdbc.Driver";
     String mUrl = "jdbc:mysql://localhost:3306/quiz";
	
	public DatabaseConnector(){
		try {
		    Class.forName(mDriver);  
		    	con = DriverManager.getConnection(mUrl,"root","poojan28");  
		    	con.setAutoCommit(true);
		}catch(Exception e){ System.out.println(e);}  
		   
	}
	
	
	public void addUser(User users[]) {
		String query = " insert into user_info (user_name)"
		        + " values ";
		for(int i = 0 ; i < 2 ; i++) {
			String addquery = "('"+ users[i].getName() +"')";
			try {
				PreparedStatement preparedStmt = con.prepareStatement(query + addquery);
				preparedStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public User[] getUsers() {
		User[] users = new User[2];
		Statement stmt;
		int i = 1;
		try {
			
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM user_info ORDER BY ID DESC LIMIT 2");
			while(rs.next()) {
				users[i] = new User(); 
				users[i].setId(rs.getInt(1));
				users[i].setName(rs.getString(2));
				i--;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		
		return users;
	}
	
	public void setResults(User[] users) {
		String query = " insert into score (user_id,score)"
		        + " values ";
		for(int i = 0 ; i < 2 ; i++) {
			String addquery = "("+ users[i].getId() +","+ users[i].getScore()+")";
			try {
				PreparedStatement preparedStmt = con.prepareStatement(query + addquery);
				preparedStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public User[] getResults() {
		User[] users = new User[2];
		Statement stmt;
		int i = 1;
		try {
			
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM user_info i,score s WHERE s.user_id = i.ID ORDER BY s.ID DESC LIMIT 2");
			while(rs.next()) {
				users[i] = new User(); 
				users[i].setId(Integer.valueOf(rs.getString("ID")));
				users[i].setName(rs.getString("user_name"));
				users[i].setScore(Integer.valueOf(rs.getString("score")));
				i--;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		
		return users;
		
	}
	
	public Question getQuestion(String severity,ArrayList<Integer> q_id) {
		Question question = new Question();
		String not_id = "",query;
		for(int i=0;i<q_id.size();i++) {
			if(i == q_id.size()-1) {
				not_id = not_id + String.valueOf(q_id.get(i));  
			}else {
				not_id = not_id + String.valueOf(q_id.get(i))+"," ;  
			}  
		}
		if(q_id.size() == 0) {
			query = "SELECT q.ID,q.question_string,o.option1,o.option2,o.option3,o.option4,c.correct_option FROM question q,options o,correct_option c WHERE q.ID = o.question_ID AND o.ID = c.option_ID AND upper(q.severity) = '"+ severity+"'";                   
			
		}else {
			query = "SELECT q.ID,q.question_string,o.option1,o.option2,o.option3,o.option4,c.correct_option FROM question q,options o,correct_option c WHERE q.ID = o.question_ID AND o.ID = c.option_ID AND upper(q.severity) = '"+ severity+"' AND q.ID NOT IN ("+ not_id+")";                   
			
		}
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				question.setQuestion_ID(rs.getString("ID"));
				question.setQuestion(rs.getString("question_string"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setCoorectOption(rs.getString(rs.getString("correct_option")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
	}
	
	
		
}


