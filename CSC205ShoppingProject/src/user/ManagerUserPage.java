package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Login.LoginDatabase;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.sql.*;
import java.awt.Panel;

public class ManagerUserPage {

	private JFrame frame;
	private JTable usersTable;
	private LoginDatabase loginDatabase = new LoginDatabase();

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 722, 323);
		frame.getContentPane().add(scrollPane);
		
		usersTable = new JTable();
		scrollPane.setViewportView(usersTable);
		usersTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Password", "Type", "First Name", "Last Name", "Email", "Address"
			}
		));
		usersTable.getColumnModel().getColumn(0).setPreferredWidth(26);
		usersTable.getColumnModel().getColumn(0).setMinWidth(16);
		usersTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		usersTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		usersTable.getColumnModel().getColumn(7).setPreferredWidth(100);
		loginDatabase.ConnectLoginDatabase("@PantsBobby25361337");
		updateTable();
	}
	
	private void updateTable() {
		try {
			ResultSet rs = loginDatabase.exportData();
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
				DefaultTableModel tblModel = (DefaultTableModel) usersTable.getModel();
				tblModel.addRow(tbData);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName()+ ": " + e.getMessage());
			System.exit(0);
		}
		
	}
}
