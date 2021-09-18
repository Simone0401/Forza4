import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class UsersModifier {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersModifier window = new UsersModifier();
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
	public UsersModifier() {
		initialize();
	}
	
	public void restart() throws FontFormatException, IOException  {
		this.frame.dispose();
		this.initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		layeredPane.add(lblNewLabel);
		
		JButton create = new JButton("");
		create.setIcon(new ImageIcon("Images/newUser.png"));
		layeredPane.setLayer(create, 2);
		create.setBounds(470, 220, 303, 69);
		layeredPane.add(create);
		create.setBorderPainted(false); 
		create.setContentAreaFilled(false); 
		create.setFocusPainted(false); 
		create.setOpaque(false);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersModifier.this.frame.dispose();
				CreateUser cu = new CreateUser();
				try {
					cu.restart();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton edit = new JButton("");
		edit.setIcon(new ImageIcon("Images/editUser.png"));
		layeredPane.setLayer(edit, 2);
		edit.setBounds(470, 320, 303, 69);
		layeredPane.add(edit);
		edit.setBorderPainted(false); 
		edit.setContentAreaFilled(false); 
		edit.setFocusPainted(false); 
		edit.setOpaque(false);
		
		JButton remove = new JButton("");
		
		layeredPane.setLayer(remove, 2);
		remove.setIcon(new ImageIcon("Images/removeUser.png"));
		remove.setBounds(470, 424, 303, 69);
		layeredPane.add(remove);
		remove.setBorderPainted(false); 
		remove.setContentAreaFilled(false); 
		remove.setFocusPainted(false); 
		remove.setOpaque(false);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsersModifier.this.frame.dispose();
				RemoveUser ru = new RemoveUser();
				try {
					ru.restart();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}

}
