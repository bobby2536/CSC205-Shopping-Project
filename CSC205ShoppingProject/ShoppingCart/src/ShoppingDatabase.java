

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDatabase {
	
	Connection c = null;
	Statement stmt = null;
	DecimalFormat df = new DecimalFormat("#.00");
	private List<Products> products;
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
	
	public void CreateShoppingDatabase() {
	/*	try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE PRODUCTS(" +
			"ID SERIAL PRIMARY KEY," +
			"ITEMNAME TEXT NOT NULL," +
			"DESCRIPTION TEXT NOT NULL," +
			"PRICE DECIMAL (10, 2)," +
			"QUANTITY INTEGER," +
			"IMAGE_PATH TEXT" +
			");";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Shopping table has been created.");
			
		} */
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE CART(" +
					"USERNAME TEXT NOT NULL," +
					"item_id INT NOT NULL," +
					"QUANTITY INT NOT NULL," +
					"PRIMARY KEY (username, item_id)," +
					"FOREIGN KEY (username) REFERENCES login(username),"+
					"FOREIGN KEY (item_id) REFERENCES products(id)"+
					");";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Shopping Cart table has been created.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		} 
	}
	
	public void addToCart(String username, int itemId, int quantity) {
	    try {
	        String sql = "INSERT INTO cart (username, item_id, quantity) VALUES (?, ?, ?)";
	        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setInt(2, itemId);
	            pstmt.setInt(3, quantity);
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	// Insert Item into Database
	
	public void InsertShoppingDatabase() {
	    try {
	        c.setAutoCommit(false);
	        stmt = c.createStatement();
	        String sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	                     "VALUES ('Apples', 'Red Apples', 1.00, 4, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Apples2.jpg')";
	        stmt.executeUpdate(sql);

	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	              "VALUES ('Cereal', 'Cinnamon Toast Crunch', 5.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Cereal.jpg')";
	        stmt.executeUpdate(sql);

	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	              "VALUES ('Bananas', 'Yellow Bananas', 2.50, 6, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Bananas2.jpg')";
	        stmt.executeUpdate(sql);

	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	              "VALUES ('Chicken', 'Chicken Wings', 10.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Chicken.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Milk', 'Milk', 3.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Milk.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Chips', 'Plain Lays', 4.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Chips.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Cookies', 'Chocolate Chip', 6.00, 6, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Cookies.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Ice Cream', 'Vanilla Ice Cream', 5.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Ice Cream.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Juice', 'Apple Juice', 7.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Juice.jpg')";
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO PRODUCTS (ITEMNAME, DESCRIPTION, PRICE, QUANTITY, IMAGE_PATH) " +
	        		"VALUES ('Candy', 'Skittles', 3.00, 1, 'C:/Users/Kreed/OneDrive/Desktop/ShoppingDatabasePics/Candy.jpg')";
	        stmt.executeUpdate(sql);

	        stmt.close();
	        c.commit();
	        c.close();
	        System.out.println("Just added 10 elements to the table");

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	    }
	}
	
	
	
	//Select items in ShoppingDatabase
	public void SelectShoppingDatabase() {
		try {
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from PRODUCTS");
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("ITEMNAME");
			String description = rs.getString("DESCRIPTION");
			Double price = rs.getDouble("PRICE");
			int quantity = rs.getInt("QUANTITY");
			
			String formattedPrice = df.format(price);
			
			System.out.println("ID: " + id);
			System.out.println("NAME: " + name);
			System.out.println("DESCRIPTION: " + description);
			System.out.println("PRICE: " + formattedPrice);
			System.out.println("QUANTITY: " + quantity);
			
		}
		
		}catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	    }
	}
	
	//Update 
	public void UpdateShoppingDatabase() {
	try {c.setAutoCommit(false);
	stmt = c.createStatement();
	String sql = "UPDATE PRODUCTS set DESCRIPTION = 'Cheerios' where ID = 5;";
	stmt.executeUpdate(sql);
	c.commit();
		
	ResultSet rs = stmt.executeQuery("select * from PRODUCTS");
	while(rs.next()) {
		int id = rs.getInt("ID");
		String name = rs.getString("ITEMNAME");
		String description = rs.getString("DESCRIPTION");
		Double price = rs.getDouble("PRICE");
		int quantity = rs.getInt("QUANTITY");
		
		String formattedPrice = df.format(price);
		
		System.out.println("ID: " + id);
		System.out.println("NAME: " + name);
		System.out.println("DESCRIPTION: " + description);
		System.out.println("PRICE: " + formattedPrice);
		System.out.println("QUANTITY: " + quantity);
		
	}
	
	}catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
	}
	
	// Delete 
	public void DeleteShoppingDatabase() {
	try {c.setAutoCommit(false);
	stmt = c.createStatement();
	String sql = "DELETE from PRODUCTS where ID = 1;";
	stmt.executeUpdate(sql);
	c.commit();
		
	ResultSet rs = stmt.executeQuery("select * from PRODUCTS");
	while(rs.next()) {
		int id = rs.getInt("ID");
		String name = rs.getString("ITEMNAME");
		String description = rs.getString("DESCRIPTION");
		Double price = rs.getDouble("PRICE");
		int quantity = rs.getInt("QUANTITY");
		
		String formattedPrice = df.format(price);
		
		System.out.println("ID: " + id);
		System.out.println("NAME: " + name);
		System.out.println("DESCRIPTION: " + description);
		System.out.println("PRICE: " + formattedPrice);
		System.out.println("QUANTITY: " + quantity);
		
	}
	
	}catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
	}
	
	public void RefreshShoppingDatabase() {
        try {
            // Clear the existing products list
            // Fetch the latest products from the database
            List<Products> products = new ArrayList<>();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String itemName = rs.getString("ITEMNAME");
                String description = rs.getString("DESCRIPTION");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String imagePath = rs.getString("IMAGE_PATH");
                Products product = new Products(id, itemName, description, price, quantity, imagePath);
                products.add(product);
            }
            rs.close();
            stmt.close();
            // Update the existing products list with the refreshed data
            // This assumes you have a member variable named 'products' in your class
            this.products = products;
            System.out.println("Database refreshed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error refreshing database: " + e.getMessage());
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
	
	
}
