

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPage {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private LoginDatabase loginDatabase = new LoginDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
		/*loginDatabase.ConnectLoginDatabase("@PantsBobby25361337");*/
		loginDatabase.ConnectLoginDatabase("password");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel loginTitle = new JLabel("Login");
		loginTitle.setFont(new Font("Tahoma", Font.PLAIN, 59));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setBounds(167, 23, 217, 94);
		frame.getContentPane().add(loginTitle);
		
		usernameField = new JTextField();
		usernameField.setBounds(271, 150, 227, 30);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		usernameLabel.setBounds(76, 150, 188, 30);
		frame.getContentPane().add(usernameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(271, 211, 227, 30);
		frame.getContentPane().add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		passwordLabel.setBounds(76, 211, 188, 30);
		frame.getContentPane().add(passwordLabel);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(147, 271, 302, 30);
		frame.getContentPane().add(messageLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			
			 public void actionPerformed(ActionEvent e) {
	                String username = usernameField.getText();
	                String password = String.valueOf(passwordField.getPassword());
	                if (loginDatabase.ValidUsernamePassword(username, password)) {
	                    System.out.println("Login Successful");
	                    System.out.println(loginDatabase.getUserType(username));
	                    String userType = loginDatabase.getUserType(username);
	                    if (userType.equals("Customer")) {
	                        frame.dispose(); // Close the login window
	                        Connection conn = null;
	                        try {
	                            conn = DriverManager.getConnection(
	                                    "jdbc:postgresql://localhost:5432/userdb", "postgres", "password");
	                            ShoppingPage shoppingPage = new ShoppingPage(conn, username);
	                            shoppingPage.setVisible(true);
	                        } catch (SQLException ex) {
	                            ex.printStackTrace();
	                            // Handle database connection error
	                        } finally {
	                            // Close the connection if necessary
	                            if (conn != null) {
	                                try {
	                                    conn.close();
	                                } catch (SQLException ex) {
	                                    ex.printStackTrace();
	                                    // Handle connection close error
	                                }
	                            }
	                        }
	                    } else {
	                        // Handle login for other user types
	                        System.out.println("Login Successful for " + userType);
	                    }
	                } else {
	                    System.out.println("Login Failed");
	                    // Update UI to indicate login failure
	                }
	            }
	        });
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.setBounds(76, 327, 119, 30);
		frame.getContentPane().add(loginButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetButton.setBounds(235, 327, 119, 30);
		frame.getContentPane().add(resetButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exitButton.setBounds(394, 327, 119, 30);
		frame.getContentPane().add(exitButton);
		
		JButton newAccount = new JButton("Create New Account");
		newAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		newAccount.setBounds(187, 379, 217, 30);
		frame.getContentPane().add(newAccount);
		
		
	}
}
