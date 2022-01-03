import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;

public class CreateUser extends JLayeredPane{

	private JTextField txtUsername;
	private JTextField textField;
	private Index i;
	

	/**
	 * Create the application.
	 */
	public CreateUser(Index i) {
		this.i = i;
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(0, 0, 1274, 694);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("Images/usernamelabel.png"));
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
		
		JButton create = new JButton("New button");
		create.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String usr = textField.getText();
			Player p = new Player(usr);
			if(JSONHandler.checkPlayer(usr)) {
				JOptionPane.showMessageDialog(null, "Username già in uso!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				JSONHandler.save(p);
				JOptionPane.showMessageDialog(null, "Giocatore creato", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				
				CreateUser.this.i.switchTo("menu");
			}
			
			}
		});
			
		this.setLayer(create, 2);
		create.setIcon(new ImageIcon("Images/crea.png"));
		create.setRolloverIcon(new ImageIcon("Images/crea-over.png"));
		create.setPressedIcon(new ImageIcon("Images/crea-pressed.png"));
		create.setBounds(538, 377, 198, 61);
		this.add(create);
		create.setBorderPainted(false); 
		create.setContentAreaFilled(false); 
		create.setFocusPainted(false); 
		create.setOpaque(false);
		this.i.frame.getRootPane().setDefaultButton(create);
		
		
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
				
				UsersModifier um = new UsersModifier(CreateUser.this.i);
				CreateUser.this.i.addToCl(um, "um");
				CreateUser.this.i.switchTo("um");
			}
		});
		
		
	}
}
