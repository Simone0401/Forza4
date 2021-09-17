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
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		layeredPane.add(lblNewLabel);
		
		JButton loadMatch = new JButton("");
		loadMatch.setIcon(new ImageIcon("Images/newUser.png"));
		layeredPane.setLayer(loadMatch, 2);
		loadMatch.setBounds(470, 220, 303, 69);
		layeredPane.add(loadMatch);
		loadMatch.setBorderPainted(false); 
		loadMatch.setContentAreaFilled(false); 
		loadMatch.setFocusPainted(false); 
		loadMatch.setOpaque(false);
		
		JButton player = new JButton("");
		player.setIcon(new ImageIcon("Images/editUser.png"));
		layeredPane.setLayer(player, 2);
		player.setBounds(470, 320, 303, 69);
		layeredPane.add(player);
		player.setBorderPainted(false); 
		player.setContentAreaFilled(false); 
		player.setFocusPainted(false); 
		player.setOpaque(false);
		
		JButton newMatch = new JButton("");
		
		layeredPane.setLayer(newMatch, 2);
		newMatch.setIcon(new ImageIcon("Images/removeUser.png"));
		newMatch.setBounds(470, 424, 303, 69);
		layeredPane.add(newMatch);
		newMatch.setBorderPainted(false); 
		newMatch.setContentAreaFilled(false); 
		newMatch.setFocusPainted(false); 
		newMatch.setOpaque(false);
		
	}

}
