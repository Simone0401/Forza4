import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class WinMessage {
	private Player p1;
	private Player p2;
	private JLayeredPane layeredPane;
	
	public WinMessage(Player p1,Player p2,JLayeredPane layeredPane) {
		this.p1 = p1;
		this.p2 = p2;
		this.layeredPane = layeredPane;
		
	}
	
	public void show (int who) {
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("Images/WinBackground.png"));
		layeredPane.setLayer(lblNewLabel_2, 3);
		lblNewLabel_2.setBounds(241, 50, 800, 600);
		layeredPane.add(lblNewLabel_2);
		if(who==1) {
			p1.addWon();
			p2.addLost();
			JLabel win = new JLabel("HAI VINTO " + p1.getUsername().toUpperCase());
			win.setHorizontalAlignment(SwingConstants.CENTER);
			win.setFont(new Font("Kid Games", Font.PLAIN, 28));
			layeredPane.setLayer(win, 4);
			win.setBounds(416, 302, 484, 63);
			Color purple = new Color(108,0,255);
			win.setForeground(purple);
			layeredPane.add(win);
		}
		else {
			JLabel win = new JLabel("HAI VINTO" + p2.getUsername().toUpperCase());
			win.setHorizontalAlignment(SwingConstants.CENTER);
			win.setFont(new Font("Kid Games", Font.PLAIN, 28));
			layeredPane.setLayer(win, 4);
			win.setBounds(416, 302, 484, 63);
			Color purple = new Color(108,0,255);
			win.setForeground(purple);
			layeredPane.add(win);
		}
	}
	
}
