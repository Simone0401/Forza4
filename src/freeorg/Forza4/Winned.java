package freeorg.Forza4;
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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa una finestra di dialogo che annuncia il vincitore.
 * @version 1.10 8 Jan 2022
 * @author Ferri Francesco
 *
 */
public class Winned  {
	
	private Index i; //Oggetto che consente il cambio di schermate.
	private JDialog dialog; //Oggetto JDialog per la schermata.
	private Game g; //Schermata di gioco
	File font_file ; //file contenente il font
	Font font ; //font da utilizzare


	
	

	/**
	 * Metodo che chiude la finestra.
	 */
	public void close() {
		this.dialog.dispose();
	}
	
	/**
	 * Metodo costruttore.
	 * @param g Schermata di gioco.
	 * @param usr Username del vincitore.
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 */
	public Winned(Game g, String usr, Index i)throws FontFormatException, IOException {
		
		this.font_file = new File("Font/Kid_Games.ttf");
		this.font = Font.createFont(Font.TRUETYPE_FONT, font_file);

		this.i = i;
		
		
		
		
		this.dialog = new JDialog(this.i.frame,"VITTORIA",true);
		
		this.dialog.setUndecorated(true);
		this.dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.dialog.setBounds(100, 100, 730, 380);
		this.dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.dialog.getContentPane().setLayout(null);
		this.dialog.setResizable(false);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 730, 380);
		this.dialog.getContentPane().add(layeredPane);
		this.dialog.setLocationRelativeTo(null);
		this.dialog.toFront();
		this.dialog.requestFocus();
		
		
		
		File font_file = new File("Font/Kid_Games.ttf"); 
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file); 
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/winned.png"));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0,730 , 380);
		layeredPane.add(lblNewLabel);
		JLabel win = new JLabel("HAI VINTO " + usr);
		win.setHorizontalAlignment(SwingConstants.CENTER);
		win.setFont(font.deriveFont(Font.PLAIN, 28));
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
		btnExit.setRolloverIcon(new ImageIcon("Images/exitButton-over.png"));
		btnExit.setPressedIcon(new ImageIcon("Images/exitButton-pressed.png"));
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(font.deriveFont(Font.PLAIN, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Winned.this.close();
				Winned.this.i.removelisteners();
				Menu m = new Menu(Winned.this.i);
				Winned.this.i.addToCl(m, "m");
				Winned.this.i.switchTo("m");
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
		gridview.setRolloverIcon(new ImageIcon("Images/viewButton-over.png"));
		gridview.setPressedIcon(new ImageIcon("Images/viewButton-pressed.png"));
		gridview.setForeground(Color.BLACK);
		gridview.setFont(font.deriveFont(Font.PLAIN, 11));
		gridview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Winned.this.close();
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
		btnAgain.setRolloverIcon(new ImageIcon("Images/againButton-over.png"));
		btnAgain.setPressedIcon(new ImageIcon("Images/againButton-pressed.png"));
		btnAgain.setForeground(Color.BLACK);
		btnAgain.setFont(font.deriveFont(Font.PLAIN, 11));
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
		btnAgain.setBounds(481, 237, 183, 73);
		layeredPane.add(btnAgain);
		this.dialog.setVisible(true);
	}
	

}
