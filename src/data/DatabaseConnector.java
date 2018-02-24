package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Question;
import classes.User;

public class DatabaseConnector {
	
	Connection con;
	 String mDriver = "com.mysql.jdbc.Driver";
     String mUrl = "jdbc:mysql://localhost:3306/quiz";
	
	public DatabaseConnector(){
		try {
		    	Class.forName(mDriver);  
		    	con = DriverManager.getConnection(mUrl,"root","root");  
		    	con.setAutoCommit(true);
		}catch(Exception e){ System.out.println(e);}  
		    	
	}
	
	
	public void addUser(String users[]) {
		String query = " insert into user_info (user_name)"
		        + " values ";
		for(int i = 0 ; i < 2 ; i++) {
			String addquery = "('"+ users[i] +"')";
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
	
	public Question getQuestion(String severity) {
		Question question = new Question();
		
		try {
			String query = "SELECT q.ID,q.question,o.option1,o.option2,o.option3,o.option4,c.correct_option FROM question q,option o,correct_option c WHERE q.ID = o.question_ID AND o.ID = c.option_ID AND upper(q.severity) = '"+ severity+"'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				question.setQuestion_ID(rs.getString("ID"));
				question.setQuestion(rs.getString("question"));
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


