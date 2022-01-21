import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class Menu extends JLayeredPane {

	private Index i;


	/**
	 * Create the application.
	 */
	public Menu(Index i)  {
		this.i= i;
		initialize();
		
	}
	
	/**
	 * Metodo che viene chiamato dall'esterno per far partire la finestra
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void restart() throws FontFormatException, IOException {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(0, 0, 1274, 694);
		
		WindowAdapter close = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Menu.this.i.frame.dispose();
			}
		};
		this.i.removelisteners();
		this.i.frame.addWindowListener(close);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		this.add(lblNewLabel);
		
		JButton loadMatch = new JButton("");
		loadMatch.setIcon(new ImageIcon("Images/loadMatch.png"));
		loadMatch.setRolloverIcon(new ImageIcon("Images/loadMatch-over.png"));
		loadMatch.setPressedIcon(new ImageIcon("Images/loadMatch-pressed.png"));
		this.setLayer(loadMatch, 2);
		loadMatch.setBounds(470, 287, 303, 69);
		this.add(loadMatch);
		loadMatch.setBorderPainted(false); 
		loadMatch.setContentAreaFilled(false); 
		loadMatch.setFocusPainted(false); 
		loadMatch.setOpaque(false);
		loadMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoadMatch lm;
				try {
					lm = new LoadMatch(Menu.this.i);
					Menu.this.i.addToCl(lm, "ldmtch");
					Menu.this.i.switchTo("ldmtch");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton player = new JButton("");
		player.setIcon(new ImageIcon("Images/players.png"));
		player.setRolloverIcon(new ImageIcon("Images/players-over.png"));
		player.setPressedIcon(new ImageIcon("Images/players-pressed.png"));
		this.setLayer(player, 2);
		player.setBounds(470, 380, 303, 69);
		this.add(player);
		player.setBorderPainted(false); 
		player.setContentAreaFilled(false); 
		player.setFocusPainted(false); 
		player.setOpaque(false);
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsersModifier um = new UsersModifier(Menu.this.i);
				Menu.this.i.addToCl(um, "um");
				Menu.this.i.switchTo("um");
			}
		});
		
		JButton stats = new JButton("");
		stats.setIcon(new ImageIcon("Images/stats.png"));
		stats.setRolloverIcon(new ImageIcon("Images/stats-over.png"));
		stats.setPressedIcon(new ImageIcon("Images/stats-pressed.png"));
		this.setLayer(stats, 2);
		stats.setBounds(470, 472, 303, 69);
		this.add(stats);
		stats.setBorderPainted(false); 
		stats.setContentAreaFilled(false); 
		stats.setFocusPainted(false); 
		stats.setOpaque(false);
		
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Stats s;
				try {
					s = new Stats(Menu.this.i);
					Menu.this.i.addToCl(s, "s");
					Menu.this.i.switchTo("s");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	
		
		JButton newMatch = new JButton("");
		newMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsersPool up;
				try {
					up = new UsersPool(Menu.this.i);
					Menu.this.i.addToCl(up, "up");
					Menu.this.i.switchTo("up");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
		});
		this.setLayer(newMatch, 2);
		newMatch.setIcon(new ImageIcon("Images/newMatch.png"));
		newMatch.setRolloverIcon(new ImageIcon("Images/newMatch-over.png"));
		newMatch.setPressedIcon(new ImageIcon("Images/newMatch-pressed.png"));
		newMatch.setBounds(470, 196, 303, 69);
		this.add(newMatch);
		newMatch.setBorderPainted(false); 
		newMatch.setContentAreaFilled(false); 
		newMatch.setFocusPainted(false); 
		newMatch.setOpaque(false);
	}
}
