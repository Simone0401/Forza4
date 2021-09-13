import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Winned extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Winned frame = new Winned();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public Winned() {
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\WinBackground.png"));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 800, 600);
		layeredPane.add(lblNewLabel);
		JLabel win = new JLabel("HAI VINTO " + "FERRIX");
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(new Font("Kid Games", Font.PLAIN, 28));
		layeredPane.setLayer(win, 2);
		win.setBounds(161, 184, 484, 63);
		Color purple = new Color(108,0,255);
		win.setForeground(purple);
		layeredPane.add(win);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\exitButton.png"));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Kid Games", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		layeredPane.setLayer(btnNewButton, 3);
		btnNewButton.setBounds(161, 423, 215, 73);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Forza 4\\Forza4\\Images\\againButton.png"));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Kid Games", Font.PLAIN, 11));
		layeredPane.setLayer(btnNewButton_1, 3);
		btnNewButton_1.setBounds(462, 420, 183, 73);
		layeredPane.add(btnNewButton_1);
	}
}
