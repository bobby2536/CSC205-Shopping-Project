package Login;

import java.sql.*;

public class LoginDatabase {
	
	Connection c = null;
	Statement stmt = null;
	
	public void ConnectLoginDatabase (String password) {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject", "postgres", password);
			System.out.println("Connected to database");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void CreateLoginDatabase() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE LOGIN(" +
			"ID SERIAL PRIMARY KEY," +
			"USERNAME TEXT NOT NULL," +
			"PASSWORD TEXT NOT NULL," +
			"TYPE TEXT NOT NULL" +
			");";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Login table has been created.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void CloseDatabase() {
		try {
			c.close();
			System.out.print("Disconnected from database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void AddLoginDatabase(String username, String password, String type) {
		if (type.equalsIgnoreCase("Manager") || type.equalsIgnoreCase("Employee") || type.equalsIgnoreCase("Customer")) {
			try {
				c.setAutoCommit(false);
				stmt = c.createStatement();
				String sql = "INSERT INTO LOGIN (USERNAME, PASSWORD, TYPE) "+
					"VALUES (" + 
					"'" + username + "'" + ',' +
					"'" + password + "'" + ',' +
					"'" + type + "'" + ");";
				stmt.executeLargeUpdate(sql);
				stmt.close();
				c.commit();
				System.out.println("Just added element to the login table");
					
			} 
			catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getClass().getName()+ ": " + e.getMessage());
				System.exit(0);
			}
		}
		else {
			System.out.println("Invalid Type Input");
		}
	}
	
	public boolean ValidUsernamePassword(String inputUsername, String inputPassword) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login;");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String type = rs.getString("type");
				if (username.equals(inputUsername) && password.equals(inputPassword)) {
					return true;
				}
			}
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}
	
	public boolean UsernameExists(String inputUsername) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login;");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String type = rs.getString("type");
				if (username.equals(inputUsername)) {
					return true;
				}
			}
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
			return false;
		}
	}
	
	public String getUserType(String inputUsername) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String type = rs.getString("type");
				if (username.equals(inputUsername)) {
					return type;
				}
			}
			return "Not Found";
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
			return "Error";
		}
	}
	
}
