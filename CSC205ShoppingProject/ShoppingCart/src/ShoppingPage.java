import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ShoppingPage extends JFrame implements ActionListener {
	 private List<Products> products;
	    private JPanel productListPanel;
	    private JTextField searchField;
	    private JButton searchButton;
	    private JButton browseButton;
	    private JButton viewCartButton;
	    private JButton plusButton;
	    private JButton minusButton;
	    private JPanel browsePanel;
	    private JPanel searchPanel;
	    private String username;
	    private Connection connection;
		private Statement stmt = null;
		DecimalFormat df = new DecimalFormat("#.00");
		private Cart cart;

    public ShoppingPage(Connection connection, String username) {
        super("Shopping Page");
        this.connection = connection;
        cart = new Cart();
        this.username = username;
        initialize();
        
       
    }
  
   

    private void initialize() {
    	setSize(1000, 500);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        plusButton = new JButton("+");
        minusButton = new JButton("-");
        plusButton.addActionListener(this);
        minusButton.addActionListener(this);
        
     
        
     
        
        // Create welcome panel
        JPanel welcomePanel = new JPanel(new BorderLayout());
        
        // Add welcome label to the welcome panel
        JLabel welcomeLabel = new JLabel("Welcome to Shopping Page!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);
        
     // Create panel for browse button and search bar
        JPanel browseAndSearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
     // Create browse button
        browseButton = new JButton("Browse Products");
        browseButton.addActionListener(this);
        browseAndSearchPanel.add(browseButton);
        
     // Create View Cart button
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(this);
        browseAndSearchPanel.add(viewCartButton);
        

        
     // Create search components
        searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        getContentPane().add(searchPanel, BorderLayout.SOUTH);
        
     // Add browse button and search bar to the panel
        browseAndSearchPanel.add(searchPanel);

        // Add the browse button and search bar panel to the welcome panel
        welcomePanel.add(browseAndSearchPanel, BorderLayout.CENTER);
        
        // Add the welcome panel to the NORTH position of the frame
        getContentPane().add(welcomePanel, BorderLayout.SOUTH);
        
     // Create product list panel
        productListPanel = new JPanel();
        productListPanel.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(productListPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        
    }
    
   
    private void fetchAndDisplayProducts(List<Products> products) {
        productListPanel.removeAll();

        for (Products product : products) {
            JPanel panel = new JPanel(new BorderLayout());

            JLabel imageLabel = new JLabel(new ImageIcon(product.getImagePath()));
            imageLabel.setPreferredSize(new Dimension(100, 100));

            JLabel detailsLabel = new JLabel(
                    "<html><body>" +
                            "<h2>" + product.getItemName() + "</h2>" +
                            "<p>Description: " + product.getDescription() + "</p>" +
                            "<p>Price: $" + String.format("%.2f", product.getPrice()) + "</p>" +
                            "<p>Quantity: " + product.getQuantity() + "</p>" +
                            "</body></html>"
            );

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(detailsLabel, BorderLayout.SOUTH);

            productListPanel.add(panel);
            productListPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing between product panels
        }

        revalidate(); // Refresh the UI
        repaint();
    }

    public List<Products> searchProducts(String query) {
        List<Products> searchResults = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM PRODUCTS WHERE LOWER(ITEMNAME) LIKE '%" + query.toLowerCase() + "%'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("ITEMNAME");
                String description = rs.getString("DESCRIPTION");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String imagePath = rs.getString("IMAGE_PATH");

                Products product = new Products(id, name, description, price, quantity, imagePath);
                searchResults.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return searchResults;
    }
   

    private List<Products> fetchProducts() {
        List<Products> products = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/userdb", "postgres", "password")) {

            String query = "SELECT * FROM PRODUCTS";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

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
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return products;
    }

    private void browseProducts(List<Products> products) {
        productListPanel.removeAll(); // Clear existing products

        for (Products product : products) {
            JPanel panel = new JPanel(new BorderLayout());

            JLabel imageLabel = new JLabel(new ImageIcon(product.getImagePath()));
            imageLabel.setPreferredSize(new Dimension(100, 100));

            JLabel detailsLabel = new JLabel(
                    "<html><body>" +
                            "<h2>" + product.getItemName() + "</h2>" +
                            "<p>Description: " + product.getDescription() + "</p>" +
                            "<p>Price: $" + String.format("%.2f", product.getPrice()) + "</p>" +
                            "<p>Quantity: " + product.getQuantity() + "</p>" +
                            "</body></html>"
            );
            
            JButton addButton = new JButton("Add to Cart");
            addButton.setPreferredSize(new Dimension(100, 30)); // Adjust the width and height as needed
            
            addButton.setActionCommand("Add to Cart - " + product.getItemName());
            addButton.addActionListener(this); // Add action listener to handle button clicks
            
            panel.add(imageLabel, BorderLayout.WEST);
            panel.add(detailsLabel, BorderLayout.CENTER);
            panel.add(addButton, BorderLayout.SOUTH);
            
            productListPanel.add(panel);
            productListPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing between product panels
        }

        revalidate(); // Refresh the UI
        repaint();
    }
    
    private void updateQuantity(boolean increase, int selectedIndex) {
        Products product = products.get(selectedIndex);
        cart.updateQuantity(product, increase ? product.getQuantity() + 1 : product.getQuantity() - 1);
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plusButton || e.getSource() == minusButton) {
            // Update the quantity of the corresponding item in the cart
        	int selectedIndex = productListPanel.getComponentZOrder((Component)e.getSource());
            updateQuantity(e.getSource() == plusButton, selectedIndex);
        } else if (e.getSource() == browseButton) {
            products = fetchProducts();
            if (products != null && !products.isEmpty()) {
                browseProducts(products);
            } else {
                JOptionPane.showMessageDialog(this, "No products found!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == searchButton) {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                List<Products> searchResults = searchProducts(query);
                fetchAndDisplayProducts(searchResults);
            } else {
                fetchAndDisplayProducts(products);
            }
        } else if (e.getSource() == viewCartButton) {
            // View Cart button clicked, display cart contents
            cart.displayCart();
        } else {
            // Handle "Add to Cart" button clicks
            for (Products product : products) {
                if (e.getActionCommand().equals("Add to Cart - " + product.getItemName())) {
                    cart.addItem(product);
                    JOptionPane.showMessageDialog(this, product.getItemName() + " added to cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    

    
 

    public static void main(String[] args) {
        try {
            // Establish database connection
          final Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/userdb", "postgres", "password");

            // Create ShoppingPage instance with the connection and username
            String username = "Test";
            ShoppingPage shoppingPage = new ShoppingPage(conn, username);

            // Add window listener to close the connection when the window is closed
            shoppingPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Make the ShoppingPage frame visible
            shoppingPage.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }}
