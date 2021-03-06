package freeorg.Forza4;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
import javax.swing.JScrollPane;
/**
 * Classe che implementa la schermata per scegliere di quale utente vogliono essere viste le statistiche.
 * @version 1.10 11 Jan 2022
 * @author Ferri Francesco
 *
 */
public class Stats extends JLayeredPane{

	private HashMap <String,Player> players; //dizionario contente gli username dei giocatori
	private JList list; // JList contente gli username dei giocatori
	private Object[] usernames; //lista contente gli username dei giocatori
	private Index i; //Oggetto che consente il cambio di schermate.
	private Handler handler ; //Oggetto per la gestione della memoria
	

	/**
	 * Metodo Costruttore
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	public Stats(Index i) throws FontFormatException, IOException {
		this.players = new HashMap <>();
		this.handler = new JSONHandler();
		this.i=i;
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
		
		
		
		this.setBounds(0, 0, 1280,720);
		
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
		
		
		JButton visualizza = new JButton("");
		visualizza.setIcon(new ImageIcon("Images/view.png"));
		visualizza.setRolloverIcon(new ImageIcon("Images/view-over.png"));
		visualizza.setPressedIcon(new ImageIcon("Images/view-pressed.png"));
		this.setLayer(visualizza, 2);
		visualizza.setBounds(487, 604, 304, 69);
		visualizza.setBorderPainted(false); 
		visualizza.setContentAreaFilled(false); 
		visualizza.setFocusPainted(false); 
		visualizza.setOpaque(false);
		visualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Stats.this.list.getSelectedIndex()!=-1) {
				PlayerStatsViewer psw;
				try {
					psw = new PlayerStatsViewer(players.get(usernames[Stats.this.list.getSelectedIndex()]),Stats.this.i);
					Stats.this.i.addToCl(psw, "psw");
					Stats.this.i.switchTo("psw");
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Scegli un giocatore!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		this.add(visualizza);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Images/who.png"));
		this.setLayer(lblNewLabel_1, 3);
		lblNewLabel_1.setBounds(491, 184, 295, 60);
		this.add(lblNewLabel_1);
		
		
		
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
				
				Stats.this.i.switchTo("menu");
			}
		});
		
			
		
	}
}