import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlayerStatsViewer {

	private JFrame frame;
	private Player p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerStatsViewer window = new PlayerStatsViewer(null);
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
	public PlayerStatsViewer(Player p) {
		this.p = p;
		initialize();
	}
	
	/**
	 * Metodo che viene chiamato dall'esterno per far partire la finestra
	 * @throws FontFormatException
	 * @throws IOException
	 */
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

		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\blank.png"));
		layeredPane.setLayer(lblNewLabel_1, 2);
		lblNewLabel_1.setBounds(481, 178, 295, 60);
		layeredPane.add(lblNewLabel_1);
		
		JLabel plrlbl = new JLabel(this.p.getUsername());
		layeredPane.setLayer(plrlbl, 4);
		plrlbl.setFont(new Font("Kid Games", Font.PLAIN, 28));
		plrlbl.setHorizontalAlignment(SwingConstants.CENTER);
		plrlbl.setBounds(513, 193, 238, 31);
		layeredPane.add(plrlbl);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\wins.png"));
		layeredPane.setLayer(lblNewLabel_3, 3);
		lblNewLabel_3.setBounds(481, 252, 295, 42);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		layeredPane.setLayer(lblNewLabel_3_1, 3);
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\perse.png"));
		lblNewLabel_3_1.setBounds(481, 359, 295, 42);
		layeredPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\tied.png"));
		layeredPane.setLayer(lblNewLabel_3_1_1, 3);
		lblNewLabel_3_1_1.setBounds(481, 454, 295, 42);
		layeredPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\howmanymathces.png"));
		layeredPane.setLayer(lblNewLabel_4, 4);
		lblNewLabel_4.setBounds(481, 292, 295, 60);
		layeredPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\howmanymathces.png"));
		layeredPane.setLayer(lblNewLabel_4_1, 4);
		lblNewLabel_4_1.setBounds(481, 398, 295, 60);
		layeredPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\howmanymathces.png"));
		layeredPane.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(481, 495, 295, 60);
		layeredPane.add(lblNewLabel_4_2);
		
		JLabel wnlbl = new JLabel(String.valueOf(this.p.getWon()));
		wnlbl.setHorizontalAlignment(SwingConstants.CENTER);
		wnlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		layeredPane.setLayer(wnlbl, 5);
		wnlbl.setBounds(604, 305, 48, 36);
		layeredPane.add(wnlbl);
		
		JLabel tdlbl = new JLabel(String.valueOf(this.p.getTied()));
		layeredPane.setLayer(tdlbl, 5);
		tdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		tdlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		tdlbl.setBounds(604, 507, 48, 36);
		layeredPane.add(tdlbl);
		
		JLabel lostlbl = new JLabel(String.valueOf(this.p.getLost()));
		layeredPane.setLayer(lostlbl, 5);
		lostlbl.setHorizontalAlignment(SwingConstants.CENTER);
		lostlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		lostlbl.setBounds(604, 412, 48, 36);
		layeredPane.add(lostlbl);
		
		
		JButton backbutton = new JButton("");
		layeredPane.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setRolloverIcon(new ImageIcon("Images/back-over.png"));
		backbutton.setPressedIcon(new ImageIcon("Images/back-pressed.png"));
		backbutton.setBounds(10, 11, 50, 50);
		layeredPane.add(backbutton);
		backbutton.setBorderPainted(false); 
		backbutton.setContentAreaFilled(false); 
		backbutton.setFocusPainted(false); 
		backbutton.setOpaque(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Stats m = new Stats();
				try {
					m.restart();
					PlayerStatsViewer.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

}
}
