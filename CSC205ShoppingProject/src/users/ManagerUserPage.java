package users;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.LoginDatabase;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.sql.*;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextPane;
import java.awt.Color;

public class ManagerUserPage {

	private JFrame frame;
	private JTable usersTable;
	private LoginDatabase loginDatabase = new LoginDatabase();
	private JComboBox<String> usernameComboBox;
	private JComboBox<String> typeComboBox;
	private JTextField passwordTextField;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;
	private JTextField lastNameTextField;
	private JTextField firstNameTextField;
	private JLabel messageLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUserPage window = new ManagerUserPage();
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
	public ManagerUserPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		loginDatabase.ConnectLoginDatabase("@PantsBobby25361337");
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 757, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 722, 323);
		frame.getContentPane().add(scrollPane);
		
		usersTable = new JTable();
		usersTable.setEnabled(false);
		scrollPane.setViewportView(usersTable);
		usersTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Password", "Type", "First Name", "Last Name", "Email", "Address"
			}
		));
		
		JLabel titleLabel = new JLabel("Manage User Accounts");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(163, 10, 406, 57);
		frame.getContentPane().add(titleLabel);
		
		JButton newAccountButton = new JButton("Create New Account");
		newAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		newAccountButton.setBounds(516, 626, 217, 30);
		frame.getContentPane().add(newAccountButton);
	
		usernameComboBox = new JComboBox<String>();
		usernameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();
			}
		});
		usernameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameComboBox.setBounds(10, 410, 153, 45);
		frame.getContentPane().add(usernameComboBox);
		
		JButton deleteAccountButton = new JButton("Delete Account");
		deleteAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginDatabase.deleteUser((String) usernameComboBox.getSelectedItem());
				updateData();
				messageLabel.setText("Account Deleted! ");
			}
		});
		deleteAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deleteAccountButton.setBounds(515, 586, 217, 30);
		frame.getContentPane().add(deleteAccountButton);
		
		JButton updateInfoButton = new JButton("Update Information");
		updateInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentUsername = (String) usernameComboBox.getSelectedItem();
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				String type = (String) typeComboBox.getSelectedItem();
				String firstname = firstNameTextField.getText();
				String lastname = lastNameTextField.getText();
				String address = addressTextField.getText();
				String email = emailTextField.getText();
				
				loginDatabase.updateData(currentUsername, username, password, type, firstname, lastname, email, address);
				updateData();
				messageLabel.setText("Information Updated! ");
			}
		});
		updateInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		updateInfoButton.setBounds(516, 546, 217, 30);
		frame.getContentPane().add(updateInfoButton);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLabel.setBounds(10, 537, 153, 13);
		frame.getContentPane().add(passwordLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(10, 555, 153, 19);
		frame.getContentPane().add(passwordTextField);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameLabel.setBounds(10, 488, 153, 13);
		frame.getContentPane().add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(10, 508, 153, 19);
		frame.getContentPane().add(usernameTextField);
		
		JLabel accountTypeLabel = new JLabel("Account Type:");
		accountTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		accountTypeLabel.setBounds(10, 584, 153, 19);
		frame.getContentPane().add(accountTypeLabel);
		
		typeComboBox = new JComboBox<String>();
		typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Manager", "Employee", "Customer"}));
		typeComboBox.setBounds(10, 608, 153, 18);
		frame.getContentPane().add(typeComboBox);
		
		JLabel emailTextLabel = new JLabel("Email:");
		emailTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailTextLabel.setBounds(187, 537, 251, 13);
		frame.getContentPane().add(emailTextLabel);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(187, 557, 251, 19);
		frame.getContentPane().add(emailTextField);
		
		JLabel addressTextLabel = new JLabel("Address:");
		addressTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressTextLabel.setBounds(187, 589, 251, 13);
		frame.getContentPane().add(addressTextLabel);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(187, 607, 251, 19);
		frame.getContentPane().add(addressTextField);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetButton.setBounds(515, 506, 217, 30);
		frame.getContentPane().add(resetButton);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(187, 508, 251, 19);
		frame.getContentPane().add(lastNameTextField);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameLabel.setBounds(187, 491, 251, 13);
		frame.getContentPane().add(lastNameLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		firstNameTextField.setBounds(187, 462, 251, 19);
		frame.getContentPane().add(firstNameTextField);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameLabel.setBounds(187, 442, 251, 13);
		frame.getContentPane().add(firstNameLabel);
		
		messageLabel = new JLabel("");
		messageLabel.setForeground(new Color(0, 255, 0));
		messageLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		messageLabel.setBounds(448, 437, 284, 57);
		frame.getContentPane().add(messageLabel);
		usersTable.getColumnModel().getColumn(0).setPreferredWidth(26);
		usersTable.getColumnModel().getColumn(0).setMinWidth(16);
		usersTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		usersTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		usersTable.getColumnModel().getColumn(7).setPreferredWidth(100);
		updateData();
	}
	
	private void updateData() {
		try {
			ResultSet rs = loginDatabase.exportAllData();
			DefaultTableModel tblModel = (DefaultTableModel) usersTable.getModel();	
			tblModel.setRowCount(0);
			usernameComboBox.removeAllItems();
			
			while(rs.next()) {
				String id = String.valueOf(rs.getInt("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				String type = rs.getString("type");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String[] tbData = {id, username, password, type, firstname, lastname, email, address};			
				tblModel.addRow(tbData);
				usernameComboBox.addItem(username);
			}
			
			messageLabel.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}		
	}
	
	private void resetTextFields() {
		try {
			ResultSet rs = loginDatabase.exportRowData((String) usernameComboBox.getSelectedItem());
			while(rs.next()) {
				usernameTextField.setText(rs.getString("username"));
				passwordTextField.setText(rs.getString("password"));
				emailTextField.setText(rs.getString("email"));
				addressTextField.setText(rs.getString("address"));
				firstNameTextField.setText(rs.getString("firstname"));
				lastNameTextField.setText(rs.getString("lastname"));
				typeComboBox.setSelectedItem(loginDatabase.getUserType((String) usernameComboBox.getSelectedItem()));
				
			}
			messageLabel.setText("");
					
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
	}
}
