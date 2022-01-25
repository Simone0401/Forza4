import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

public class UsersPool extends JLayeredPane{

	private HashMap <String,Player> players = new HashMap <>(); 
	private JList list;
	private JList list2;
	private Object[] usernames;

	private Index i;
	
	private Handler handler;
	
	public void oldMatchReload(String selezione) throws FontFormatException, IOException {
		if(JOptionPane.showConfirmDialog(UsersPool.this.i.frame, "C'è una partita in sospeso fra di voi,volete riprenderla ?") == JOptionPane.YES_OPTION){
				System.out.println("ok");
				Game g = new Game(this.handler.getMatch(selezione), UsersPool.this.i);
				UsersPool.this.i.addToCl(g, "g");
				UsersPool.this.i.switchTo("g");
		

    	}
		else {
			
				Game g = new Game(players.get(usernames[UsersPool.this.list.getSelectedIndex()]),players.get(usernames[list2.getSelectedIndex()]),UsersPool.this.i);
				UsersPool.this.i.addToCl(g, "g");
				UsersPool.this.i.switchTo("g");
			
			
		}
    }
	
	

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public UsersPool(Index i) throws FontFormatException, IOException {
		this.handler = new JSONHandler();
		this.i = i;
		Player p;
		Map<String, Player> users = this.handler.getPlayers();
		for( String username : users.keySet()) {
			p = this.handler.getPlayer(username);
	
			this.players.put(p.getUsername(), p);
			
		}
		
		initialize();
	}
	

	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	private void initialize() throws FontFormatException, IOException {
		
		
		

		this.setBounds(0, 0, 1280, 720);		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1280, 720);
		this.add(lblNewLabel);
		
		
		this.usernames = (players.keySet().toArray());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		this.setLayer(splitPane, 2);
		splitPane.setBounds(397, 255, 470, 307);
		this.add(splitPane);
		splitPane.setEnabled( false );
		
		File font_file = new File("Font/Kid_Games.ttf"); 
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file); 
		
		this.list = new JList(usernames);
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(font.deriveFont(Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		
		list2 = new JList(usernames);
		list2.setBounds(400, 544, 470, -361);
		list2.setFont(font.deriveFont(Font.PLAIN, 21));
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer2 =  (DefaultListCellRenderer)list2.getCellRenderer();  
		renderer2.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setRightComponent(new JScrollPane(list2));
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setIcon(new ImageIcon("Images/P1.png"));
		this.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(383, 184, 239, 60);
		this.add(lblNewLabel_4_2);;
		
		
		
		
		JButton scegli = new JButton("");
		scegli.setIcon(new ImageIcon("Images/gioca.png"));
		this.setLayer(scegli, 2);
		scegli.setBounds(481, 597, 304, 69);
		scegli.setBorderPainted(false); 
		scegli.setContentAreaFilled(false); 
		scegli.setFocusPainted(false); 
		scegli.setOpaque(false);
		scegli.setRolloverIcon(new ImageIcon("Images/gioca-over.png"));
		scegli.setPressedIcon(new ImageIcon("Images/gioca-pressed.png"));
		scegli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UsersPool.this.list.getSelectedIndex()==-1 || list2.getSelectedIndex()==-1 ) {
					JOptionPane.showMessageDialog(null, "Scegli due giocatori !", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				else if(players.get(usernames[UsersPool.this.list.getSelectedIndex()])==players.get(usernames[list2.getSelectedIndex()])){
					JOptionPane.showMessageDialog(null, "Scegli due giocatori diversi!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(handler.checkMatch(usernames[UsersPool.this.list.getSelectedIndex()].toString() + usernames[list2.getSelectedIndex()].toString() )) {
						try {
							UsersPool.this.oldMatchReload(usernames[UsersPool.this.list.getSelectedIndex()].toString() + usernames[UsersPool.this.list2.getSelectedIndex()].toString() );
						} catch (FontFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(handler.checkMatch(usernames[list2.getSelectedIndex()].toString() + usernames[UsersPool.this.list.getSelectedIndex()].toString( ))){
						try {
							UsersPool.this.oldMatchReload(usernames[UsersPool.this.list2.getSelectedIndex()].toString() + usernames[UsersPool.this.list.getSelectedIndex()].toString( ));
						} catch (FontFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						try {
						Game g = new Game(players.get(usernames[UsersPool.this.list.getSelectedIndex()]),players.get(usernames[list2.getSelectedIndex()]), UsersPool.this.i);
						UsersPool.this.i.addToCl(g, "g");
						UsersPool.this.i.switchTo("g");
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			}
		});
		
		JLabel lblNewLabel_4_2_1 = new JLabel("");
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setIcon(new ImageIcon("Images/p2.png"));
		this.setLayer(lblNewLabel_4_2_1, 3);
		lblNewLabel_4_2_1.setBounds(636, 184, 231, 60);
		this.add(lblNewLabel_4_2_1);
		this.add(scegli);
		
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
				UsersPool.this.i.switchTo("menu");
				
			}
		});
		
			
		
	}
}
