import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class CartListPage {

	private JFrame frame;
	private JTable cartTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartListPage window = new CartListPage();
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
	public CartListPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 292, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Shopping Carts");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 10, 258, 57);
		frame.getContentPane().add(titleLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 258, 275);
		frame.getContentPane().add(scrollPane);
		
		cartTable = new JTable();
		cartTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Quantity"
			}
		));
		scrollPane.setViewportView(cartTable);
		
		JComboBox<String> usernameComboBox = new JComboBox<String>();
		usernameComboBox.setBounds(63, 77, 152, 37);
		frame.getContentPane().add(usernameComboBox);
	}
}
