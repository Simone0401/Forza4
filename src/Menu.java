import java.awt.EventQueue;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}
	
	public void restart() throws FontFormatException, IOException {
		this.frame.dispose();
		this.main(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);
		
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
		loadMatch.setIcon(new ImageIcon("Images/loadMatch.png"));
		layeredPane.setLayer(loadMatch, 2);
		loadMatch.setBounds(470, 320, 303, 69);
		layeredPane.add(loadMatch);
		loadMatch.setBorderPainted(false); 
		loadMatch.setContentAreaFilled(false); 
		loadMatch.setFocusPainted(false); 
		loadMatch.setOpaque(false);
		
		JButton player = new JButton("");
		player.setIcon(new ImageIcon("Images/players.png"));
		layeredPane.setLayer(player, 2);
		player.setBounds(470, 424, 303, 69);
		layeredPane.add(player);
		player.setBorderPainted(false); 
		player.setContentAreaFilled(false); 
		player.setFocusPainted(false); 
		player.setOpaque(false);
		
	
		
		JButton newMatch = new JButton("");
		newMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game g = new game();
					g.restart();
					Menu.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layeredPane.setLayer(newMatch, 2);
		newMatch.setIcon(new ImageIcon("Images/newMatch.png"));
		newMatch.setBounds(470, 220, 303, 69);
		layeredPane.add(newMatch);
		newMatch.setBorderPainted(false); 
		newMatch.setContentAreaFilled(false); 
		newMatch.setFocusPainted(false); 
		newMatch.setOpaque(false);
	}
}
