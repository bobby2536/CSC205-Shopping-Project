package items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDatabase {
	
	Connection c = null;
	Statement stmt = null;
	
	public void ConnectItemDatabase (String password) {		
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
	
	public void CreateItemDatabase () {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE ITEMS(" +
			"ID SERIAL PRIMARY KEY," +
			"NAME TEXT NOT NULL," +
			"PRICE DOUBLE PRECISION NOT NULL," +
			"QUANTITY INT NOT NULL," +
			"DESCRIPTION TEXT NOT NULL" +
			");";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Item table has been created.");
			
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
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void AddItem (String name, double price, int quantity, String description) {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY, DESCRIPTION)"
					+ "VALUES ("
					+ "'" + name + "'" + ',' 
					+ price + ',' 
					+ quantity + ',' 
					+ "'" + description + "'" + ");";
			stmt.executeLargeUpdate(sql);
			stmt.close();
			c.commit();
			System.out.println("Just added element to the items table");
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void DeleteItem (String name) {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE FROM ITEMS WHERE name = " + "'" + name + "';";
			stmt.executeLargeUpdate(sql);			
			stmt.close();
			c.commit();
			System.out.println("Just deleted element in the item table");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public boolean ItemExists(String inputItem) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from items;");
			
			while (rs.next()) {
				String item = rs.getString("name");
				if (item.equals(inputItem)) {
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
	
	public void updateItem(String currentItem, String name, double price, int quantity, String description) {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "UPDATE ITEMS "
					+ "SET name = " + "'" + name + "'" + ", "
					+ "price = " + "'" + price + "'" + ", "
					+ "quantity = " + "'" + quantity + "'" + ", "
					+ "description = " + "'" + description + "'"
					+ " WHERE name = " + "'" + currentItem + "'" + ";";
			stmt.executeLargeUpdate(sql);			
			stmt.close();
			c.commit();
			System.out.println("Just updated element in the items table");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public ResultSet exportAllData() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS ORDER BY CAST(id AS int);");
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
	
	public ResultSet exportRowData (String itemName) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from items where name = " + "'" + itemName + "';");
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

}
