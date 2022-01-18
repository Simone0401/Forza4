import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class editUser extends JLayeredPane{

	Index i;
	private HashMap <String,Player> players = new HashMap <>(); 
	private JList list;
	private Object[] usernames;

	

	/**
	 * Create the application.
	 */
	public editUser(Index i) {
		this.i = i;
		Player p;
		Map<String, Object> users = JSONHandler.getPlayers();
		for( String username : users.keySet()) {
			JSONObject playerJSON = JSONHandler.getPlayer(username);
			p = new Player((String) playerJSON.get("username"),
					 				 (int) (long) playerJSON.get("won"),
					 				 (int) (long) playerJSON.get("tied"),
					 				 (int) (long) playerJSON.get("lost"));
			this.players.put(p.getUsername(), p);
			
		}
		
		initialize();
	}
	



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setBounds(0, 0, 1264, 699);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menuPattern.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 684);
		this.add(lblNewLabel);
		
		
		this.usernames = (players.keySet().toArray());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1);
		this.setLayer(splitPane, 2);
		splitPane.setBounds(397, 255, 470, 307);
		this.add(splitPane);
		splitPane.setEnabled( false );
		
		this.list = new JList(usernames);
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(new Font("Kid Games", Font.PLAIN, 21));
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
		modifica.setIcon(new ImageIcon("Images/modifica-over.png"));
		modifica.setRolloverIcon(new ImageIcon("Images/modifica-over.png"));
		modifica.setPressedIcon(new ImageIcon("Images/modifica-pressed.png"));
		this.setLayer(modifica, 2);
		modifica.setBounds(482, 589, 304, 69);
		modifica.setBorderPainted(false); 
		modifica.setContentAreaFilled(false); 
		modifica.setFocusPainted(false); 
		modifica.setOpaque(false);
		this.i.frame.getRootPane().setDefaultButton(modifica);
		
		modifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					
					UserEdited ue = new UserEdited(players.get(usernames[editUser.this.list.getSelectedIndex()]),editUser.this.i);
					editUser.this.i.addToCl(ue, "ue");
					editUser.this.i.switchTo("ue");
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
				
				UsersModifier m = new UsersModifier(editUser.this.i);
				editUser.this.i.addToCl(m, "menu");
				editUser.this.i.switchTo("menu");
				
				}
		});
		
	}
	}
