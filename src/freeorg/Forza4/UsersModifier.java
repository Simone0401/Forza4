package freeorg.Forza4;
import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
/**
 * Classe che implementa la schermata di gestione degli utenti
 * @author Administrator
 *
 */
public class UsersModifier extends JLayeredPane{

	Index i;

	
	/**
	 * Metodo Costruttore.
	 * @param i Oggetto che consente il cambio di schermate.
	 */
	public UsersModifier(Index i) {
		this.i = i;
		initialize();
	}
	



	/**
	 * Inizializza il frame.
	 */
	private void initialize() {
		
	
		this.setBounds(0, 0, 1280, 720);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1280, 720);
		this.add(lblNewLabel);
		
		JButton create = new JButton("");
		create.setIcon(new ImageIcon("Images/newUser.png"));
		create.setRolloverIcon(new ImageIcon("Images/newUser-over.png"));
		create.setPressedIcon(new ImageIcon("Images/newUser-pressed.png"));
		this.setLayer(create, 2);
		create.setBounds(490, 230, 303, 69);
		this.add(create);
		create.setBorderPainted(false); 
		create.setContentAreaFilled(false); 
		create.setFocusPainted(false); 
		create.setOpaque(false);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CreateUser cu;
				try {
					cu = new CreateUser(UsersModifier.this.i);
					UsersModifier.this.i.addToCl(cu, "cu");
					UsersModifier.this.i.switchTo("cu");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton edit = new JButton("");
		edit.setIcon(new ImageIcon("Images/usermodificatore.png"));
		edit.setRolloverIcon(new ImageIcon("Images/usermodificatore-over.png"));
		edit.setPressedIcon(new ImageIcon("Images/usermodificatore-pressed.png"));
		this.setLayer(edit, 2);
		edit.setBounds(490, 330, 303, 69);
		this.add(edit);
		edit.setBorderPainted(false); 
		edit.setContentAreaFilled(false); 
		edit.setFocusPainted(false); 
		edit.setOpaque(false);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditUser eu;
				try {
					eu = new EditUser(UsersModifier.this.i);
					UsersModifier.this.i.addToCl(eu, "eu");
					UsersModifier.this.i.switchTo("eu");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton remove = new JButton("");
		
		this.setLayer(remove, 2);
		remove.setIcon(new ImageIcon("Images/removeUser.png"));
		remove.setRolloverIcon(new ImageIcon("Images/removeUser-over.png"));
		remove.setPressedIcon(new ImageIcon("Images/removeUser-pressed.png"));
		remove.setBounds(490, 430, 303, 69);
		this.add(remove);
		remove.setBorderPainted(false); 
		remove.setContentAreaFilled(false); 
		remove.setFocusPainted(false); 
		remove.setOpaque(false);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RemoveUser ru;
				try {
					ru = new RemoveUser(UsersModifier.this.i);
					UsersModifier.this.i.addToCl(ru, "ru");
					UsersModifier.this.i.switchTo("ru");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
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
				
				Menu m = new Menu(UsersModifier.this.i);
				UsersModifier.this.i.addToCl(m, "m");
				UsersModifier.this.i.switchTo("m");
				
			}
		});
		
	}

}
