

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDatabase {
	
	Connection c = null;
	Statement stmt = null;
	DecimalFormat df = new DecimalFormat("#.00");
	public void ConnectShoppingDatabase (String password) {
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
	
	/*public void CreateShoppingDatabase() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE PRODUCTS(" +
			"ID SERIAL PRIMARY KEY," +
			"ITEMNAME TEXT NOT NULL," +
			"DESCRIPTION TEXT NOT NULL," +
			"PRICE DECIMAL (10, 2)," +
			"QUANTITY INTEGER" +
			");";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Shopping table has been created.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		} 
	}
	// Insert Item into Database
	
	public void InsertShoppingDatabase() {
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "INSERT INTO PRODUCTS (" + "ID,ITEMNAME,DESCRIPTION,PRICE,QUANTITY)"+
			"VALUES(1, 'Apples', 'Red Apples', 1.00, 4);";
			stmt.executeLargeUpdate(sql);
			 sql = "INSERT INTO PRODUCTS (" + "ID,ITEMNAME,DESCRIPTION,PRICE,QUANTITY)"+
					"VALUES(2, 'Cereal', 'Cinnamon Toast Crunch', 5.00, 1);";
			stmt.executeLargeUpdate(sql);
			 sql = "INSERT INTO PRODUCTS (" + "ID,ITEMNAME,DESCRIPTION,PRICE,QUANTITY)"+
					"VALUES(3, 'Bananas', 'Yellow Bananas', 2.50, 6);";
			stmt.executeLargeUpdate(sql);
			 sql = "INSERT INTO PRODUCTS (" + "ID,ITEMNAME,DESCRIPTION,PRICE,QUANTITY)"+
					"VALUES(4, 'Chicken', 'Chicken Wings', 10.00, 1);";
			stmt.executeLargeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
			System.out.println("Just add 4 elements to the table");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}*/
	
	//Select items in ShoppingDatabase
	
	
	
	
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
	
	
}
