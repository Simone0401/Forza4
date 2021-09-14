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

public class Winned extends JFrame {

	private JPanel contentPane;
	private game g;
	File font_file = new File("Font/Kid_Games.ttf");
	Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	
	/**
	public static void main(String[] args) throws FontFormatException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Winned frame = new Winned(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	

	/**
	 * Create the frame.
	 */
	
	public void close() {
		this.dispose();
	}
	
	public Winned(game g, String usr)throws FontFormatException, IOException {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 800, 600);
		contentPane.add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/WinBackground.png"));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 800, 600);
		layeredPane.add(lblNewLabel);
		JLabel win = new JLabel("HAI VINTO " + usr);
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(win, 2);
		win.setBounds(161, 184, 484, 63);
		Color purple = new Color(108,0,255);
		win.setForeground(purple);
		layeredPane.add(win);
		
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon("Images/exitButton.png"));
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Kid Games", Font.PLAIN, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Winned.this.close();
			}
		});
		layeredPane.setLayer(btnExit, 3);
		btnExit.setBounds(161, 423, 215, 73);
		layeredPane.add(btnExit);
		
		JButton btnAgain = new JButton("");
		btnAgain.setIcon(new ImageIcon("Images/againButton.png"));
		btnAgain.setForeground(Color.BLACK);
		btnAgain.setFont(new Font("Kid Games", Font.PLAIN, 11));
		btnAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.restart();
					Winned.this.close();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layeredPane.setLayer(btnAgain, 3);
		btnAgain.setBounds(462, 420, 183, 73);
		layeredPane.add(btnAgain);
		this.setVisible(true);
	}
	

}
