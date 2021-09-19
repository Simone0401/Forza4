import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Winned  {
	private JFrame frame;
	private game g;
	File font_file = new File("Font/Kid_Games.ttf");
	Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);

	
	

	
	public void close() {
		this.frame.dispose();
	}
	
	public Winned(game g, String usr)throws FontFormatException, IOException {
		
		
		
		this.frame = new JFrame();
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.frame.setBounds(100, 100, 720, 380);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		this.frame.setResizable(false);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 720, 380);
		this.frame.getContentPane().add(layeredPane);
		this.frame.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/WinBackground.png"));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0,720 , 380);
		layeredPane.add(lblNewLabel);
		JLabel win = new JLabel("HAI VINTO " + usr);
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(win, 2);
		win.setBounds(139, 100, 484, 63);
		Color purple = new Color(108,0,255);
		win.setForeground(purple);
		layeredPane.add(win);
		
		JButton btnExit = new JButton("");
		btnExit.setBorderPainted(false); 
		btnExit.setContentAreaFilled(false); 
		btnExit.setFocusPainted(false); 
		btnExit.setOpaque(false);
		btnExit.setIcon(new ImageIcon("Images/exitButton.png"));
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Kid Games", Font.PLAIN, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Winned.this.close();
				Menu m = new Menu();
				try {
					m.restart();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layeredPane.setLayer(btnExit, 3);
		btnExit.setBounds(139, 237, 183, 73);
		layeredPane.add(btnExit);
		
		JButton btnAgain = new JButton("");
		btnAgain.setBorderPainted(false); 
		btnAgain.setContentAreaFilled(false); 
		btnAgain.setFocusPainted(false); 
		btnAgain.setOpaque(false);
		btnAgain.setIcon(new ImageIcon("Images/againButton.png"));
		btnAgain.setForeground(Color.BLACK);
		btnAgain.setFont(new Font("Kid Games", Font.PLAIN, 11));
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.reset();
					Winned.this.close();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layeredPane.setLayer(btnAgain, 3);
		btnAgain.setBounds(412, 237, 183, 73);
		layeredPane.add(btnAgain);
		this.frame.setVisible(true);
	}
	

}
