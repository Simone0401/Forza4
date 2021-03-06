package freeorg.Forza4;
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
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

/**
 * Classe che implementa la schermata per la scelta dell'utente da modificare.
 * @version 1.10 16 Jan 2022
 * @author Ferri Francesco
 *
 */
public class EditUser extends JLayeredPane{

	private Index i; //Oggetto che consente il cambio di schermate.
	private HashMap <String,Player> players ;  //Dizionario contenente i giocatori
	private JList list; //JList contenente i giocatori
	private Object[] usernames; //lista contenente i giocatori
	private Handler handler; //Oggetto per la gestione della memoria
	

	/**
	 * Metodo costruttore.
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	public EditUser(Index i) throws FontFormatException, IOException {
		this.handler = new JSONHandler();
		this.i = i;
		this.players = new HashMap <>();
		Map<String, Player> users = this.handler.getPlayers();
		
		for( String username : users.keySet()) {
			
			Player p = this.handler.getPlayer(username);
			this.players.put(p.getUsername(), p);
			
		}
		
		initialize();
	}
	



	/**
	 * Inizializza il frame.
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
		
		
		this.usernames = (players.keySet().toArray());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1);
		this.setLayer(splitPane, 2);
		splitPane.setBounds(407, 255, 470, 307);
		this.add(splitPane);
		splitPane.setEnabled( false );
		
		this.list = new JList(usernames);
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(font.deriveFont(Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		splitPane.setRightComponent(null);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon("Images/whoedit.png"));
		this.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(479, 184, 295, 60);
		this.add(lblNewLabel_4_2);;

		
		
		JButton modifica = new JButton("");
		modifica.setIcon(new ImageIcon("Images/modifica.png"));
		modifica.setRolloverIcon(new ImageIcon("Images/modifica-over.png"));
		modifica.setPressedIcon(new ImageIcon("Images/modifica-pressed.png"));
		this.setLayer(modifica, 2);
		modifica.setBounds(487, 604, 304, 69);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		this.i.frame.getRootPane().setDefaultButton(modifica);
		
		modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					int index = EditUser.this.list.getSelectedIndex();
					if ( index==-1) {
						JOptionPane.showMessageDialog(null, "Scegli un utente da modificare!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else {
						UserEdited ue;
						try {
							ue = new UserEdited(players.get(usernames[index]),EditUser.this.i);
							EditUser.this.i.addToCl(ue, "ue");
							EditUser.this.i.switchTo("ue");
						} catch (FontFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
						
			}	
			
		});
		this.add(modifica);
		
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
				
				UsersModifier m = new UsersModifier(EditUser.this.i);
				EditUser.this.i.addToCl(m, "menu");
				EditUser.this.i.switchTo("menu");
				
				}
		});
		
	}
	}
