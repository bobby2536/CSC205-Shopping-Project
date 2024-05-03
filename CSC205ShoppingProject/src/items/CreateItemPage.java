package items;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateItemPage {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField quantityTextField;
	private ItemDatabase itemDatabase = new ItemDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateItemPage window = new CreateItemPage();
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
	public CreateItemPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		itemDatabase.ConnectItemDatabase("@PantsBobby25361337");
		frame = new JFrame();
		frame.setBounds(100, 100, 541, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Create New Item");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setBounds(128, 22, 280, 52);
		frame.getContentPane().add(titleLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setBounds(25, 84, 153, 19);
		frame.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(25, 108, 153, 19);
		frame.getContentPane().add(nameTextField);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceLabel.setBounds(25, 148, 153, 19);
		frame.getContentPane().add(priceLabel);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(25, 168, 153, 19);
		frame.getContentPane().add(priceTextField);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quantityLabel.setBounds(25, 214, 153, 19);
		frame.getContentPane().add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(25, 235, 153, 19);
		frame.getContentPane().add(quantityTextField);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionLabel.setBounds(263, 84, 153, 19);
		frame.getContentPane().add(descriptionLabel);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		messageLabel.setBounds(25, 278, 479, 30);
		frame.getContentPane().add(messageLabel);
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setBounds(248, 105, 182, 149);
		frame.getContentPane().add(descriptionTextArea);
		
		JButton createItemButton = new JButton("Create Item");
		createItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = nameTextField.getText();
					String description = descriptionTextArea.getText();
					double price = Double.parseDouble(priceTextField.getText());
					int quantity = Integer.parseInt(quantityTextField.getText());
					if (name.isBlank() || description.isBlank()) {
						messageLabel.setText("Please Fill Out All Fields");
						messageLabel.setForeground(Color.red);
					}
					else if (itemDatabase.ItemExists(name)) {
						messageLabel.setText("Item Already Exists");
						messageLabel.setForeground(Color.red);
					}
					else {
						itemDatabase.AddItem(name, price, quantity, description);
						messageLabel.setText("Created Item!");
						messageLabel.setForeground(Color.green);
					}
					
				}
				catch (NumberFormatException e1){
					messageLabel.setText("Please Enter Numeric Amounts");
					messageLabel.setForeground(Color.red);
				}
			}
		});
		createItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		createItemButton.setBounds(156, 318, 217, 30);
		frame.getContentPane().add(createItemButton);
		
	}
}
