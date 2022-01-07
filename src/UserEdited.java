import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

public class UserEdited extends JLayeredPane {

	Index i;
	private JTextField txtUsername;
	private JTextField textField;
	private Player p;

	

	/**
	 * Create the application.
	 */
	public UserEdited(Player p, Index i) {
		this.i = i;
		this.p = p;
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
		
		JLabel lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("Images/newUsername.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Kid Games", Font.PLAIN, 41));
		this.setLayer(lblNewLabel_1, 2);
		lblNewLabel_1.setBounds(485, 220, 295, 60);
		this.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Kid Games", Font.PLAIN, 27));
		this.setLayer(textField, 2);
		textField.setBounds(475, 291, 326, 61);
		this.add(textField);
		textField.setColumns(10);
		
		
		
		textField.addKeyListener(new KeyListener() {
		    @Override
		    public void keyTyped(KeyEvent e) {

		    }

		    @Override
		    public void keyPressed(KeyEvent e) {
		    	
		    }

		    @Override
		    public void keyReleased(KeyEvent e) {      		//il font non ha lettere minuscole, questo frammento di codice rende tutto il testo nel textfield maiuscolo
		        int pos = textField.getCaretPosition();
		        textField.setText(textField.getText().toUpperCase());
		        textField.setCaretPosition(pos);
		    }
		});
		
		JButton modifica = new JButton("New button");
		
			
		this.setLayer(modifica, 2);
		modifica.setIcon(new ImageIcon("Images/modifica.png"));
		modifica.setBounds(538, 377, 198, 61);
		this.add(modifica);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		this.i.frame.getRootPane().setDefaultButton(modifica);
		
		modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JSONHandler.checkPlayer(textField.getText())) {
				JOptionPane.showMessageDialog(null, "Username già in uso!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JSONHandler.updatePlayer(textField.getText(), UserEdited.this.p);
					JSONObject playerJSON = JSONHandler.getPlayer(textField.getText());
					Player newp = new Player((String) playerJSON.get("username"),
			 				 (int) (long) playerJSON.get("won"),
			 				 (int) (long) playerJSON.get("tied"),
			 				 (int) (long) playerJSON.get("lost"));
					JSONHandler.updateMatch(UserEdited.this.p, newp);
				JOptionPane.showMessageDialog(null, "Giocatore modificato", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				
				editUser eu = new editUser(UserEdited.this.i);
				UserEdited.this.i.addToCl(eu, "eu");
				UserEdited.this.i.switchTo("eu");
				
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
				
				editUser m = new editUser(UserEdited.this.i);
				UserEdited.this.i.addToCl(m, "m");
				UserEdited.this.i.switchTo("m");
				
			}
		});
		
		
	}
}
