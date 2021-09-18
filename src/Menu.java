import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
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
		loadMatch.setBounds(470, 287, 303, 69);
		layeredPane.add(loadMatch);
		loadMatch.setBorderPainted(false); 
		loadMatch.setContentAreaFilled(false); 
		loadMatch.setFocusPainted(false); 
		loadMatch.setOpaque(false);
		
		JButton player = new JButton("");
		player.setIcon(new ImageIcon("Images/players.png"));
		layeredPane.setLayer(player, 2);
		player.setBounds(470, 380, 303, 69);
		layeredPane.add(player);
		player.setBorderPainted(false); 
		player.setContentAreaFilled(false); 
		player.setFocusPainted(false); 
		player.setOpaque(false);
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsersModifier um = new UsersModifier();
				try {
					um.restart();
					Menu.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton stats = new JButton("");
		stats.setIcon(new ImageIcon("Images/stats.png"));
		layeredPane.setLayer(stats, 2);
		stats.setBounds(470, 472, 303, 69);
		layeredPane.add(stats);
		stats.setBorderPainted(false); 
		stats.setContentAreaFilled(false); 
		stats.setFocusPainted(false); 
		stats.setOpaque(false);
		
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Stats s = new Stats();
				try {
					s.restart();
					
					Menu.this.frame.dispose();
					} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		JButton newMatch = new JButton("");
		newMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usersPool up = new usersPool();
				up.show();
				
				Menu.this.frame.setVisible(false);}
		});
		layeredPane.setLayer(newMatch, 2);
		newMatch.setIcon(new ImageIcon("Images/newMatch.png"));
		newMatch.setBounds(470, 196, 303, 69);
		layeredPane.add(newMatch);
		newMatch.setBorderPainted(false); 
		newMatch.setContentAreaFilled(false); 
		newMatch.setFocusPainted(false); 
		newMatch.setOpaque(false);
	}
}
