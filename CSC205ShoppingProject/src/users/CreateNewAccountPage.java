package users;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import login.LoginDatabase;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateNewAccountPage {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JPasswordField retypePasswordTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private LoginDatabase loginDatabase = new LoginDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewAccountPage window = new CreateNewAccountPage("Employee");
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
	public CreateNewAccountPage(String type) {
		initialize(type);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String accountType) {
		loginDatabase.ConnectLoginDatabase("@PantsBobby25361337");
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Create New Account");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setBounds(125, 20, 280, 52);
		frame.getContentPane().add(titleLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameLabel.setBounds(25, 89, 153, 19);
		frame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLabel.setBounds(25, 148, 153, 19);
		frame.getContentPane().add(passwordLabel);
		
		JLabel accountTypeLabel = new JLabel("Account Type:");
		accountTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		accountTypeLabel.setBounds(188, 89, 153, 19);
		frame.getContentPane().add(accountTypeLabel);
		
		JLabel retypePasswordLabel = new JLabel("Retype Password:");
		retypePasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		retypePasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		retypePasswordLabel.setBounds(25, 207, 153, 19);
		frame.getContentPane().add(retypePasswordLabel);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameLabel.setBounds(188, 148, 153, 19);
		frame.getContentPane().add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameLabel.setBounds(188, 207, 153, 19);
		frame.getContentPane().add(lastNameLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailLabel.setBounds(351, 89, 153, 19);
		frame.getContentPane().add(emailLabel);
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressLabel.setBounds(351, 148, 153, 19);
		frame.getContentPane().add(addressLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(25, 108, 153, 19);
		frame.getContentPane().add(usernameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(25, 168, 153, 19);
		frame.getContentPane().add(passwordTextField);
		
		retypePasswordTextField = new JPasswordField();
		retypePasswordTextField.setBounds(25, 235, 153, 19);
		frame.getContentPane().add(retypePasswordTextField);
		
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(188, 168, 153, 19);
		frame.getContentPane().add(firstNameTextField);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(188, 235, 153, 19);
		frame.getContentPane().add(lastNameTextField);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(351, 168, 153, 19);
		frame.getContentPane().add(addressTextField);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(351, 108, 153, 19);
		frame.getContentPane().add(emailTextField);
		
		JComboBox<String> typeComboBox = new JComboBox<String>();
		typeComboBox.setBounds(188, 107, 153, 18);
		if (accountType.equals("Manager")) {
			typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Manager", "Employee", "Customer"}));
		}
		else if(accountType.equals("Employee") || accountType.equals("Customer")) {
			typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Customer"}));
		}
		frame.getContentPane().add(typeComboBox);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		messageLabel.setBounds(25, 278, 479, 30);
		frame.getContentPane().add(messageLabel);
		
		JButton createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String username = usernameTextField.getText().trim();
				String password = String.valueOf(passwordTextField.getPassword()).trim();
				String retypePassword = String.valueOf(retypePasswordTextField.getPassword()).trim();
				String type = (String) typeComboBox.getSelectedItem();
				String firstname = firstNameTextField.getText().trim();
				String lastname = lastNameTextField.getText().trim();
				String address = addressTextField.getText().trim();
				String email = emailTextField.getText().trim();
				
				if (username.isEmpty() || password.isEmpty() || retypePassword.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || address.isEmpty() || username.isEmpty() || email.isEmpty()) {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Please Fill Out All Fields");
				}
				else if (loginDatabase.UsernameExists(username)) {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Username Already Exists");
				}
				else if (!password.equals(retypePassword)) {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Passwords Don't Match");
				}
				else {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Account Has Been Created!");
					loginDatabase.AddLoginDatabase(username, password, type, firstname, lastname, email, address);
				}
			}
		});
		createAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		createAccountButton.setBounds(156, 318, 217, 30);
		frame.getContentPane().add(createAccountButton);
	}
}
