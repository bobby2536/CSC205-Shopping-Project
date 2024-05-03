package items;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import items.ItemDatabase;
import javax.swing.JTextArea;

public class ItemModificationPage {

	private JFrame frame;
	private JTable itemsTable;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField quantityTextField;
	private JComboBox<String> itemNameComboBox;
	private JLabel messageLabel;
	private ItemDatabase itemDatabase = new ItemDatabase();
	private JTextArea descriptionTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemModificationPage window = new ItemModificationPage();
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
	public ItemModificationPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		itemDatabase.ConnectItemDatabase("@PantsBobby25361337");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 757, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 722, 323);
		frame.getContentPane().add(scrollPane);
		
		itemsTable = new JTable();
		itemsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Price", "Quantity", "Description"
			}
		));
		itemsTable.getColumnModel().getColumn(0).setPreferredWidth(45);
		itemsTable.getColumnModel().getColumn(0).setMaxWidth(45);
		itemsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		itemsTable.getColumnModel().getColumn(2).setMaxWidth(100);
		itemsTable.getColumnModel().getColumn(3).setMaxWidth(75);
		itemsTable.getColumnModel().getColumn(4).setPreferredWidth(144);
		scrollPane.setViewportView(itemsTable);
		
		JLabel titleLabel = new JLabel("Modify Items");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(163, 10, 406, 57);
		frame.getContentPane().add(titleLabel);
		
		itemNameComboBox = new JComboBox<String>();
		itemNameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();
			}
		});
		itemNameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		itemNameComboBox.setBounds(9, 410, 153, 45);
		frame.getContentPane().add(itemNameComboBox);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(9, 508, 153, 19);
		frame.getContentPane().add(nameTextField);
		
		JLabel itemNameLabel = new JLabel("Name:");
		itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		itemNameLabel.setBounds(9, 488, 153, 13);
		frame.getContentPane().add(itemNameLabel);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionLabel.setBounds(186, 488, 251, 13);
		frame.getContentPane().add(descriptionLabel);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quantityLabel.setBounds(9, 584, 153, 19);
		frame.getContentPane().add(quantityLabel);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(9, 555, 153, 19);
		frame.getContentPane().add(priceTextField);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLabel.setBounds(9, 537, 153, 13);
		frame.getContentPane().add(priceLabel);
		
		
		
		messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		messageLabel.setForeground(Color.GREEN);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		messageLabel.setBounds(359, 437, 372, 57);
		frame.getContentPane().add(messageLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setColumns(10);
		quantityTextField.setBounds(9, 607, 153, 19);
		frame.getContentPane().add(quantityTextField);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setBounds(186, 508, 251, 118);
		frame.getContentPane().add(descriptionTextArea);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetButton.setBounds(514, 506, 217, 30);
		frame.getContentPane().add(resetButton);
		
		JButton updateItemButton = new JButton("Update Item");
		updateItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentName = (String) itemNameComboBox.getSelectedItem();
				String name = nameTextField.getText();
				String description = descriptionTextArea.getText();
				try {
					double price = Double.parseDouble(priceTextField.getText());
					int quantity = Integer.parseInt(quantityTextField.getText());
					itemDatabase.updateItem(currentName, name, price, quantity, description);
					updateData();
					messageLabel.setText("Updated Item!");
					messageLabel.setForeground(Color.green);
				}
				catch (NumberFormatException e1){
					messageLabel.setText("Please Enter Numeric Amounts");
					messageLabel.setForeground(Color.red);
				}
				
				
			}
		});
		updateItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		updateItemButton.setBounds(515, 546, 217, 30);
		frame.getContentPane().add(updateItemButton);
		
		JButton deleteItemButton = new JButton("Delete Item");
		deleteItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemDatabase.DeleteItem((String) itemNameComboBox.getSelectedItem());
				updateData();
				messageLabel.setText("Deleted Item!");
				messageLabel.setForeground(Color.green);
			}
		});
		deleteItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deleteItemButton.setBounds(514, 586, 217, 30);
		frame.getContentPane().add(deleteItemButton);
		
		JButton newItemButton = new JButton("Create New Item");
		newItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		newItemButton.setBounds(515, 626, 217, 30);
		frame.getContentPane().add(newItemButton);
		
		updateData();
	}
	
	private void updateData() {
		try {
			ResultSet rs = itemDatabase.exportAllData();
			DefaultTableModel tblModel = (DefaultTableModel) itemsTable.getModel();	
			tblModel.setRowCount(0);
			itemNameComboBox.removeAllItems();
			
			while(rs.next()) {
				String id = String.valueOf(rs.getInt("id"));
				String name = rs.getString("name");
				String price = String.valueOf(rs.getDouble("price"));
				String quantity = String.valueOf(rs.getInt("quantity"));
				String description = rs.getString("description");
				String[] tbData = {id, name, "$" + price, quantity, description};
				tblModel.addRow(tbData);
				itemNameComboBox.addItem(name);				
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
			ResultSet rs = itemDatabase.exportRowData((String) itemNameComboBox.getSelectedItem());
			while (rs.next()) {
				nameTextField.setText(rs.getString("name"));
				priceTextField.setText(String.valueOf(rs.getDouble("price")));
				quantityTextField.setText(String.valueOf(rs.getInt("quantity")));
				descriptionTextArea.setText(rs.getString("description"));
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
