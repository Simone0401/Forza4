import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class game {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game window = new game();
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
	public game() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/icon.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/match_background.png"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		layeredPane.add(lblNewLabel);
		
		JLabel lblp1 = new JLabel("FRANCESCO");
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp1.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(lblp1, 1);
		lblp1.setBounds(40, 120, 224, 36);
		Color purple = new Color(108,0,255);
		lblp1.setForeground(purple);
		layeredPane.add(lblp1);
		
		JLabel lblp2 = new JLabel("SIMONE");
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(lblp2, 1);
		lblp2.setBounds(1016, 589, 224, 36);
		Color myellow = new Color(255,204,0);
		lblp2.setForeground(myellow);
		layeredPane.add(lblp2);
		
		JButton btnColumn0 = new JButton("");
		btnColumn0.setOpaque(false);
		btnColumn0.setContentAreaFilled(false);
		btnColumn0.setBorderPainted(false);
		layeredPane.setLayer(btnColumn0, 1);
		btnColumn0.setBounds(366, 135, 67, 471);
		layeredPane.add(btnColumn0);
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		
		
	}
}
