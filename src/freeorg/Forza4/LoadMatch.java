package freeorg.Forza4;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
/**
 * Classe che implementa la schermata per la scelta della partita da caricare
 * @author Ferri Francesco
 *
 */
public class LoadMatch extends JLayeredPane {
	
	private HashMap <String,Match> matches = new HashMap <>(); 
	private JList list;
	private Object[] partite;
	private ArrayList<Object> vs = new ArrayList<>();
	private Index i;
	private Handler handler = new JSONHandler();


	/**
	 * Metodo costruttore
	 * @param i Oggetto che consente il cambio di schermate.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	public LoadMatch(Index i) throws FontFormatException, IOException {
		this.i = i;
		Player p;
		
		Map<String, Match> matchs = this.handler.getMatches();
		for(String mn : matchs.keySet()) {
			
			Match match = this.handler.getMatch(mn);
			
			this.matches.put(mn, match);
			
		}
		initialize();
	}
	
	


	/**
	 * Inizializza il frame.
	 * @throws IOException per gestire l'utilizzo del font personalizzato.
	 * @throws FontFormatException per gestire l'utilizzo del font personalizzato.
	 */
	private void initialize() throws FontFormatException, IOException {
		
		;
		
		
		this.setBounds(0, 0, 1280, 720);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1280, 720);
		this.add(lblNewLabel);
		
		
		this.partite = (matches.keySet().toArray());
		String stringa;
		for (Object x : this.partite) {
			stringa = matches.get(x).getP1().getUsername() + "  VS  " + matches.get(x).getP2().getUsername();
			this.vs.add(stringa);
		}
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1);
		this.setLayer(splitPane, 2);
		splitPane.setBounds(407, 255, 470, 307);
		this.add(splitPane);
		splitPane.setEnabled( false );
		
		File font_file = new File("Font/Kid_Games.ttf"); 
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		this.list = new JList(this.vs.toArray());
		this.list.setBounds(440, 544, 490, -361);
		this.list.setFont(font.deriveFont(Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(vs.size());
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		splitPane.setRightComponent(null);
		
		JButton gioca = new JButton("");
		gioca.setIcon(new ImageIcon("Images/gioca.png"));
		this.setLayer(gioca, 2);
		gioca.setBounds(482, 609, 304, 69);
		gioca.setBorderPainted(false); 
		gioca.setContentAreaFilled(false); 
		gioca.setFocusPainted(false); 
		gioca.setOpaque(false);
		gioca.setRolloverIcon(new ImageIcon("Images/gioca-over.png"));
		gioca.setPressedIcon(new ImageIcon("Images/gioca-pressed.png"));
		
		
		
		gioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = LoadMatch.this.list.getSelectedIndex();
				if ( index==-1) {
					JOptionPane.showMessageDialog(null, "Scegli una partita da caricare!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					String selezione = LoadMatch.this.vs.toArray()[index].toString().replace("  VS  ", "");
					Game g;
					try {
						g = new Game(handler.getMatch(selezione),LoadMatch.this.i);
						LoadMatch.this.i.addToCl(g, "g");
					LoadMatch.this.i.switchTo("g");
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		this.add(gioca);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Images/matchchoice.png"));
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
				Menu m = new Menu(LoadMatch.this.i);
				LoadMatch.this.i.addToCl(m, "menu");
				LoadMatch.this.i.switchTo("menu");
			}
		});
		
			
		
	}
}