package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "); 
				users[i].setId(rs.getInt(1));
				users[i].setName(rs.getString(2));
				i--;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		return users;
	}
	
	
		
}


