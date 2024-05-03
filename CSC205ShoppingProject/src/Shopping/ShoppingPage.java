import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ShoppingPage extends JFrame implements ActionListener {

   
    private String username;
    private JLabel titleLabel;
    private JButton browseButton;
    private ShoppingDatabase shoppingDatabase = new ShoppingDatabase();
    private List<Products> products;

    
   // private LinkedList<Product> cart; 
    
    
    public ShoppingPage() {
        super("Shopping Page");
        initialize();
    }
    
    public ShoppingPage(String username) {
    	
        this.username = username;
        }
    private List<Products> fetchProducts() {
        List<Products> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/userdb", "postgres", "password")) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS;");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String itemName = rs.getString("ITEMNAME");
                String description = rs.getString("DESCRIPTION");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");

                Products product = new Products(id, itemName, description, price, quantity);
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }

        return products;
    }
    private void initialize() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        titleLabel = new JLabel("Welcome to the Shopping Page!");
        titleLabel.setBounds(100, 20, 200, 30);
        getContentPane().add(titleLabel);

        browseButton = new JButton("Browse Products");
        browseButton.setBounds(150, 80, 100, 20);
        browseButton.addActionListener(this);
        getContentPane().add(browseButton);

        setVisible(true);
    }

   

   



        private void browseProducts(List<Products> products) {
            // Create a new window or panel to display product information
            JFrame productFrame = new JFrame("Products");
            productFrame.setSize(600, 400);

            // Use a JList to display the product list in a scrollable manner
            DefaultListModel<Products> productListModel = new DefaultListModel<>();
            for (Products product : products) {
                productListModel.addElement(product);
            }
            
            JList<Products> productList = new JList<>(productListModel);
            productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            productList.addListSelectionListener(new ProductListSelectionListener(products));

            JScrollPane scrollPane = new JScrollPane(productList);
            productFrame.add(scrollPane);

            // Display additional product details upon selection
            productList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedIndex = e.getFirstIndex();
                        if (selectedIndex >= 0) {
                            Products selectedProduct = products.get(selectedIndex);

                            // Retrieve additional details from the database
                            String additionalDetails = retrieveAdditionalProductDetails(selectedProduct.getId());

                            String details = "Selected Product Details:\n" +
                                    "ID: " + selectedProduct.getId() + "\n" +
                                    "Item Name: " + selectedProduct.getItemName() + "\n" +
                                    "Description: " + selectedProduct.getDescription() + "\n" +
                                    "Price: $" + String.format("%.2f", selectedProduct.getPrice()) + "\n" +
                                    "Quantity: " + selectedProduct.getQuantity() + "\n" +
                                    additionalDetails;

                            JOptionPane.showMessageDialog(productFrame, details);
                        }
                    }
                }
            });

            productFrame.setVisible(true);
        }
        private class ProductListSelectionListener implements ListSelectionListener {
            private List<Products> products;

            public ProductListSelectionListener(List<Products> products) {
                this.products = products;
            }
      


		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}


      
    
   

    
    public static void main(String[] args) {
        new ShoppingPage();
        
    }
    }
        }

	
   
      
    


