package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		loginDatabase.ConnectLoginDatabase("@PantsBobby25361337");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
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
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblPassword.setBounds(76, 211, 188, 30);
		frame.getContentPane().add(lblPassword);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (loginDatabase.ValidUsernamePassword(username, password)) {
					System.out.println("Login Successful");
					System.out.println(loginDatabase.getUserType(username));
				}
				else {
					System.out.println("Login Failed");
				}
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.setBounds(80, 292, 119, 30);
		frame.getContentPane().add(loginButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetButton.setBounds(237, 292, 119, 30);
		frame.getContentPane().add(resetButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exitButton.setBounds(395, 292, 119, 30);
		frame.getContentPane().add(exitButton);
	}
}
