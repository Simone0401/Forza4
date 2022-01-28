import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
/**
 * Classe che implementa la schermata per la modifica di un utente
 * @author Administrator
 *
 */
public class UserEdited extends JLayeredPane {

	Index i;
	private JTextField txtUsername;
	private JTextField textField;
	private Player p;
	private Handler handler = new JSONHandler();
	

	/**
	 * Metodo costruttore.
	 * @param p Giocatore da modificare.
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	public UserEdited(Player p, Index i) throws FontFormatException, IOException {
		this.i = i;
		this.p = p;
		initialize();
	}
	
	

	/**
	 * Initializza il frame.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	private void initialize() throws FontFormatException, IOException {
		
		
		this.setBounds(0, 0, 1280, 720);
		
		File font_file = new File("Font/Kid_Games.ttf"); 
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file); 
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1280, 720);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("Images/newUsername.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(font.deriveFont( Font.PLAIN, 41));
		this.setLayer(lblNewLabel_1, 2);
		lblNewLabel_1.setBounds(485, 220, 295, 60);
		this.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(font.deriveFont(Font.PLAIN, 27));
		this.setLayer(textField, 2);
		textField.setBounds(485, 301, 326, 61);
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
		modifica.setRolloverIcon(new ImageIcon("Images/modifica-over.png"));
		modifica.setPressedIcon(new ImageIcon("Images/modifica-pressed.png"));
		
		modifica.setBounds(543, 392, 198, 61);
		this.add(modifica);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		this.i.frame.getRootPane().setDefaultButton(modifica);
		
		modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(handler.checkPlayer(textField.getText())) {
					JOptionPane.showMessageDialog(null, "Username già in uso!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					handler.updatePlayer(textField.getText(), UserEdited.this.p);
					Player newp = handler.getPlayer(textField.getText());
					handler.updateMatch(UserEdited.this.p, newp);
					
					
					JOptionPane.showMessageDialog(null, "Giocatore modificato", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					
					EditUser eu;
					try {
						eu = new EditUser(UserEdited.this.i);
						UserEdited.this.i.addToCl(eu, "eu");
						UserEdited.this.i.switchTo("eu");
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
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
				
				EditUser m;
				try {
					m = new EditUser(UserEdited.this.i);
					UserEdited.this.i.addToCl(m, "m");
					UserEdited.this.i.switchTo("m");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
	}
}
