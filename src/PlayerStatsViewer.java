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

public class PlayerStatsViewer extends JLayeredPane {

	private Index i;
	private Player p;

	
	/**
	 * Create the application.
	 */
	public PlayerStatsViewer(Player p,Index i) {
		this.p = p;
		this.i = i;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	
		this.setBounds(0, 0, 1274, 694);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Images/blank.png"));
		this.setLayer(lblNewLabel_1, 2);
		lblNewLabel_1.setBounds(481, 178, 295, 60);
		this.add(lblNewLabel_1);
		
		JLabel plrlbl = new JLabel(this.p.getUsername());
		this.setLayer(plrlbl, 4);
		plrlbl.setFont(new Font("Kid Games", Font.PLAIN, 28));
		plrlbl.setHorizontalAlignment(SwingConstants.CENTER);
		plrlbl.setBounds(513, 193, 238, 31);
		this.add(plrlbl);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("Images/wins.png"));
		this.setLayer(lblNewLabel_3, 3);
		lblNewLabel_3.setBounds(481, 252, 295, 42);
		this.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		this.setLayer(lblNewLabel_3_1, 3);
		lblNewLabel_3_1.setIcon(new ImageIcon("Images/perse.png"));
		lblNewLabel_3_1.setBounds(481, 359, 295, 42);
		this.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setIcon(new ImageIcon("Images/tied.png"));
		this.setLayer(lblNewLabel_3_1_1, 3);
		lblNewLabel_3_1_1.setBounds(481, 454, 295, 42);
		this.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("Images/howmanymathces.png"));
		this.setLayer(lblNewLabel_4, 4);
		lblNewLabel_4.setBounds(481, 292, 295, 60);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon("Images/howmanymathces.png"));
		this.setLayer(lblNewLabel_4_1, 4);
		lblNewLabel_4_1.setBounds(481, 398, 295, 60);
		this.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon("Images/howmanymathces.png"));
		this.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(481, 495, 295, 60);
		this.add(lblNewLabel_4_2);
		
		JLabel wnlbl = new JLabel(String.valueOf(this.p.getWon()));
		wnlbl.setHorizontalAlignment(SwingConstants.CENTER);
		wnlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		this.setLayer(wnlbl, 5);
		wnlbl.setBounds(604, 305, 48, 36);
		this.add(wnlbl);
		
		JLabel tdlbl = new JLabel(String.valueOf(this.p.getTied()));
		this.setLayer(tdlbl, 5);
		tdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		tdlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		tdlbl.setBounds(604, 507, 48, 36);
		this.add(tdlbl);
		
		JLabel lostlbl = new JLabel(String.valueOf(this.p.getLost()));
		this.setLayer(lostlbl, 5);
		lostlbl.setHorizontalAlignment(SwingConstants.CENTER);
		lostlbl.setFont(new Font("Kid Games", Font.PLAIN, 30));
		lostlbl.setBounds(604, 412, 48, 36);
		this.add(lostlbl);
		
		
		JButton backbutton = new JButton("");
		this.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setRolloverIcon(new ImageIcon("Images/back-over.png"));
		backbutton.setPressedIcon(new ImageIcon("Images/back-pressed.png"));
		backbutton.setBounds(10, 11, 50, 50);
		this.add(backbutton);
		backbutton.setBorderPainted(false); 
		backbutton.setContentAreaFilled(false); 
		backbutton.setFocusPainted(false); 
		backbutton.setOpaque(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Stats s = new Stats(PlayerStatsViewer.this.i);
				PlayerStatsViewer.this.i.addToCl(s, "s");
				PlayerStatsViewer.this.i.switchTo("s");
				}
			
		});

}
}
