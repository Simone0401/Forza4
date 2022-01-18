import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

public class Tied {
	
	Index i;
	private JFrame frame;
	private game g;
	File font_file = new File("Font/Kid_Games.ttf");
	Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);

	/**
	 * Metodo che chiude la finestra
	 */
	public void close() {
		this.frame.dispose();
	}
	
	/**
	 * Create the application.
	 */
	public Tied(game g, Index i)throws FontFormatException, IOException {
		this.i = i;
		this.frame = new JFrame("FORZA 4");
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.frame.setBounds(100, 100, 730, 380);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		this.frame.setResizable(false);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 730, 380);
		this.frame.getContentPane().add(layeredPane);
		this.frame.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/winned.png"));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0,730 , 380);
		layeredPane.add(lblNewLabel);
		JLabel win = new JLabel("PAREGGIO");
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(new Font("Kid Games", Font.PLAIN, 31));
		layeredPane.setLayer(win, 2);
		win.setBounds(130, 75, 484, 63);
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
				Tied.this.close();
				Tied.this.i.removelisteners();
				Menu m = new Menu(Tied.this.i);
				Tied.this.i.addToCl(m, "m");
				Tied.this.i.switchTo("m");
			}
		});
		layeredPane.setLayer(btnExit, 3);
		btnExit.setBounds(81, 237, 183, 73);
		layeredPane.add(btnExit);
		
		JButton gridview = new JButton("");
		gridview.setBorderPainted(false); 
		gridview.setContentAreaFilled(false); 
		gridview.setFocusPainted(false); 
		gridview.setOpaque(false);
		gridview.setIcon(new ImageIcon("Images/viewButton.png"));
		gridview.setForeground(Color.BLACK);
		gridview.setFont(new Font("Kid Games", Font.PLAIN, 11));
		gridview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tied.this.close();
			}
		});
		layeredPane.setLayer(gridview, 3);
		gridview.setBounds(281, 237, 183, 73);
		layeredPane.add(gridview);
		
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
					Tied.this.close();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layeredPane.setLayer(btnAgain, 3);
		btnAgain.setBounds(481, 237, 183, 73);
		layeredPane.add(btnAgain);
		this.frame.setVisible(true);
	}
	

}