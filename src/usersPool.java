import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class usersPool {

	private JFrame frame;
	private HashMap <String,Player> players = new HashMap <>(); 
	private JList list;
	private Object[] usernames;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usersPool window = new usersPool();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * gioca the application.
	 */
	public usersPool() {
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
	
	public void restart() throws FontFormatException, IOException  {
		this.frame.dispose();
		this.initialize();
		this.frame.setVisible(true);
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/icon.png"));
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1264, 699);
		frame.getContentPane().add(layeredPane);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Images/menu.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(0, 0, 1264, 688);
		layeredPane.add(lblNewLabel);
		
		
		this.usernames = (players.keySet().toArray());
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		layeredPane.setLayer(splitPane, 2);
		splitPane.setBounds(397, 255, 470, 307);
		layeredPane.add(splitPane);
		splitPane.setEnabled( false );
		
		this.list = new JList(usernames);
		this.list.setBounds(400, 544, 470, -361);
		this.list.setFont(new Font("Kid Games", Font.PLAIN, 21));
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)this.list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setLeftComponent(new JScrollPane(this.list));
		
		JList list2 = new JList(usernames);
		list2.setBounds(400, 544, 470, -361);
		list2.setFont(new Font("Kid Games", Font.PLAIN, 21));
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setVisibleRowCount(usernames.length);
		DefaultListCellRenderer renderer2 =  (DefaultListCellRenderer)list2.getCellRenderer();  
		renderer2.setHorizontalAlignment(JLabel.CENTER);
		splitPane.setRightComponent(new JScrollPane(list2));
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setIcon(new ImageIcon("Images/P1.png"));
		layeredPane.setLayer(lblNewLabel_4_2, 4);
		lblNewLabel_4_2.setBounds(383, 184, 239, 60);
		layeredPane.add(lblNewLabel_4_2);;
		
		
		
		
		JButton scegli = new JButton("");
		scegli.setIcon(new ImageIcon("Images/gioca.png"));
		layeredPane.setLayer(scegli, 2);
		scegli.setBounds(481, 597, 304, 69);
		scegli.setBorderPainted(false); 
		scegli.setContentAreaFilled(false); 
		scegli.setFocusPainted(false); 
		scegli.setOpaque(false);
		scegli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(players.get(usernames[usersPool.this.list.getSelectedIndex()])==players.get(usernames[list2.getSelectedIndex()])){
					JOptionPane.showMessageDialog(null, "Scegli due giocatori diversi!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					try {
						game g = new game(players.get(usernames[usersPool.this.list.getSelectedIndex()]),players.get(usernames[list2.getSelectedIndex()]));
						g.restart();
						usersPool.this.frame.dispose();
					} catch (FontFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblNewLabel_4_2_1 = new JLabel("");
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setIcon(new ImageIcon("Images/p2.png"));
		layeredPane.setLayer(lblNewLabel_4_2_1, 3);
		lblNewLabel_4_2_1.setBounds(636, 184, 231, 60);
		layeredPane.add(lblNewLabel_4_2_1);
		layeredPane.add(scegli);
		
		JButton backbutton = new JButton("");
		layeredPane.setLayer(backbutton, 4);
		backbutton.setIcon(new ImageIcon("Images/back.png"));
		backbutton.setBounds(10, 11, 50, 50);
		layeredPane.add(backbutton);
		backbutton.setBorderPainted(false); 
		backbutton.setContentAreaFilled(false); 
		backbutton.setFocusPainted(false); 
		backbutton.setOpaque(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersPool.this.frame.dispose();
				Menu m = new Menu();
				try {
					m.restart();
				} catch (FontFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
			
		
	}
}
