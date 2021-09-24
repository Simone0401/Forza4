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

public class UserEdited {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField textField;
	private Player p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserEdited window = new UserEdited(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserEdited(Player p) {
		this.p = p;
		initialize();
	}
	
	/**
	 * Metodo che viene chiamato dall'esterno per far partire la finestra
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void restart() throws FontFormatException, IOException  {
		this.frame.dispose();
		this.initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("FORZA 4");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));

		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1274, 694);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("",SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("Images/newUsername.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Kid Games", Font.PLAIN, 41));
		layeredPane.setLayer(lblNewLabel_1, 2);
		lblNewLabel_1.setBounds(485, 220, 295, 60);
		layeredPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Kid Games", Font.PLAIN, 27));
		layeredPane.setLayer(textField, 2);
		textField.setBounds(475, 291, 326, 61);
		layeredPane.add(textField);
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
		
			
		layeredPane.setLayer(modifica, 2);
		modifica.setIcon(new ImageIcon("Images/modifica.png"));
		modifica.setBounds(538, 377, 198, 61);
		layeredPane.add(modifica);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		frame.getRootPane().setDefaultButton(modifica);
		frame.getRootPane().setDefaultButton(modifica);
		
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
				try {
					editUser eu = new editUser();
					eu.restart();
					UserEdited.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		
		
		JButton backbutton = new JButton("");
		layeredPane.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setRolloverIcon(new ImageIcon("Images/back-over.png"));
		backbutton.setPressedIcon(new ImageIcon("Images/back-pressed.png"));
		backbutton.setBounds(10, 11, 50, 50);
		layeredPane.add(backbutton);
		backbutton.setBorderPainted(false); 
		backbutton.setContentAreaFilled(false); 
		backbutton.setFocusPainted(false); 
		backbutton.setOpaque(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editUser m = new editUser();
				try {
					m.restart();
					UserEdited.this.frame.dispose();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}
}
