

import java.sql.*;

public class LoginDatabase {
	
	Connection c = null;
	Statement stmt = null;
	
	public void ConnectLoginDatabase (String password) {
		try {
			Class.forName("org.postgresql.Driver");
			/*c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finalproject", "postgres", password);*/
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userdb", "postgres", "password");
			
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
			"TYPE TEXT NOT NULL," +
			"FIRSTNAME TEXT NOT NULL," +
			"LASTNAME TEXT NOT NULL," +
			"EMAIL TEXT NOT NULL," +
			"ADDRESS TEXT NOT NULL" +
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
	
	public void AddLoginDatabase(String username, String password, String type, String firstname, String lastname, String email, String address) {
		if (type.equalsIgnoreCase("Manager") || type.equalsIgnoreCase("Employee") || type.equalsIgnoreCase("Customer")) {
			try {
				c.setAutoCommit(false);
				stmt = c.createStatement();
				String sql = "INSERT INTO LOGIN (USERNAME, PASSWORD, TYPE, FIRSTNAME, LASTNAME, EMAIL, ADDRESS) "+
					"VALUES (" + 
					"'" + username + "'" + ',' +
					"'" + password + "'" + ',' +
					"'" + type + "'" + ',' +
					"'" + firstname + "'" + ',' +
					"'" + lastname + "'" + ',' +
					"'" + email + "'" + ',' +
					"'" + address + "'" +
					");";
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
				String username = rs.getString("username");
				String password = rs.getString("password");
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
				String username = rs.getString("username");
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
				String username = rs.getString("username");
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
	
	public ResultSet exportAllData() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN ORDER BY CAST(id AS int);");
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
			ResultSet data = null;
			return data;
		}
	}
	
	public ResultSet exportRowData (String username) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login where username = " + "'" + username + "';");
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
			ResultSet data = null;
			return data;
		}
	}
	
	public void updateData(String currentUsername, String newUsername, String newPassword, String newType, String newFirstName, String newLastName, String newEmail, String newAddress) {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "UPDATE LOGIN "
					+ "SET username = " + "'" + newUsername + "'" + ", "
					+ "password = " + "'" + newPassword + "'" + ", "
					+ "type = " + "'" + newType + "'" + ", "
					+ "firstname = " + "'" + newFirstName + "'" + ", "
					+ "lastname = " + "'" + newLastName + "'" + ", "
					+ "email = " + "'" + newEmail + "'" + ", "
					+ "address = " + "'" + newAddress + "'"
					+ " WHERE username = " + "'" + currentUsername + "'" + ";";
			stmt.executeLargeUpdate(sql);			
			stmt.close();
			c.commit();
			System.out.println("Just updated element in the login table");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void deleteUser (String username) {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE FROM LOGIN WHERE username = " + "'" + username + "';";
			stmt.executeLargeUpdate(sql);			
			stmt.close();
			c.commit();
			System.out.println("Just deleted element in the login table");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	} 
}


